package input;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Velocity;
import levels.LevelInformation;
import sprites.Block;
import sprites.Sprite;
/**
 * @author David Abramov.
 * LevelSpecificationReader class implementation.
 */
public class LevelSpecificationReader {
    private final int winX = 800;
    private final int winY = 600;
    private final int radius = 10;
    /**
     * generates list of Levels from inputed stream.
     * @param reader **java.io.Reader**
     * @return **level List**
     */
    public List<LevelInformation> fromReader(Reader reader) {
        ArrayList<LevelInformation> toRet = new ArrayList<LevelInformation>();
        LinesGenerator lg = new LinesGenerator();
        ArrayList<String> lines = lg.get(reader);
        if (lines == null || lines.isEmpty()) {
            return null;
        }
        if (!lines.get(0).equals("START_LEVEL")) {
            return null;
        }
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            int start = i;
            while (i < size && !lines.get(i).equals("END_LEVEL")) {
                i++;
            }   //last line of file reached without reading "END_LEVEL".
            if (i == size) {
                return null;
            }
            ArrayList<String> levelLines = lg.getLevelLines(lines, start, i);
            if (levelLines == null || levelLines.isEmpty()) {
                return null;
            }
            LevelInformation level = this.levelGenerator(levelLines);
            if (level != null) {
                toRet.add(level);
            }
        }
        return toRet;
    }
    /**
     * generates level from inputed lines List.
     * @param l **lines List**
     * @return **LevelInformation**
     */
    private LevelInformation levelGenerator(ArrayList<String> l) {
        Integer padSpeed = null, padWidth = null, blkX = null, blkY = null, rowHeight = null, numBlk = null;
        ArrayList<Velocity> vel = new ArrayList<Velocity>();
        ArrayList<Block> blk = new ArrayList<Block>();
        java.awt.Image m = null;
        java.awt.Color c = null;
        Sprite background = null;
        String name = null;
        BlocksFromSymbolsFactory fact = null;
        ColorsParser cp = new ColorsParser();
        ImageGetter ig = new ImageGetter();
        int size = l.size();
        for (int i = 0; i < size; i++) {
            String str = l.get(i);
            if (str.startsWith("level_name")) {
                String[] arr = str.split(":");
                name = arr[1];
            } else if (str.startsWith("ball_velocities")) {
                String[] arr = str.split(":");
                String[] s = arr[1].split(" ");
                for (int j = 0; j < s.length; j++) {
                    String[] velocity = s[j].split(",");
                    try {
                        int angle = Integer.parseInt(velocity[0]);
                        int speed = Integer.parseInt(velocity[1]);
                        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                        vel.add(v);
                    } catch (NumberFormatException e) { return null; }
                }
            } else if (str.startsWith("background")) {
                String[] arr = str.split(":");
                String back = arr[1];
                if (back.startsWith("color(")) {
                    String color = back.substring(6);
                    int len = color.length();
                    c = cp.colorFromStr(color.substring(0, len - 1));
                    if (c == null) { return null; }
                } else if (back.startsWith("image(")) {
                    String image = back.substring(6);
                    int len = image.length();
                    m = ig.load(image.substring(0, len - 1));
                    if (m == null) { return null; }
                } else { return null; }
                if (c != null) {
                    final java.awt.Color fc = c;
                    background = new Sprite() {
                        public void drawOn(DrawSurface d) {
                            d.setColor(fc);
                            d.fillRectangle(0, 0, winX, winY);
                        }
                        public void timePassed(double dt) { /*to be updated...*/ }
                        public void addToGame(GameLevel g) { g.addSprite(this); }
                    };
                }
                if (m != null) {
                    final java.awt.Image fm = m;
                    background = new Sprite() {
                        public void drawOn(DrawSurface d) { d.drawImage(0, 0, fm); }
                        public void timePassed(double dt) { /*to be updated...*/ }
                        public void addToGame(GameLevel g) { g.addSprite(this); }
                    };
                }
            } else if (str.startsWith("paddle_speed")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    padSpeed = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("paddle_width")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    padWidth = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("blocks_start_x")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    blkX = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("blocks_start_y")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    blkY = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("row_height")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    rowHeight = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("num_blocks")) {
                String[] arr = str.split(":");
                String num = arr[1];
                try {
                    numBlk = Integer.parseInt(num);
                } catch (NumberFormatException e) { return null; }
            } else if (str.startsWith("block_definitions")) {
                String[] arr = str.split(":");
                String path = arr[1];
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                InputStreamReader r = new InputStreamReader(is);
                fact = BlocksDefinitionReader.fromReader(r);
                if (fact == null) { return null; }
            } else if (str.equals("START_BLOCKS")) {
                int k = i + 1;  //blocks description comes at the end, thus we assume all needed data exists.
                while (k < size && !l.get(k).equals("END_BLOCKS")) {
                    k++;
                }
                if (k == size) { return null; }
                LinesGenerator lg = new LinesGenerator();
                ArrayList<String> blkLines = lg.genBlockLines(l, i, k);
                if (blkLines == null || blkX == null || rowHeight == null || blkY == null || fact == null) {
                    return null;
                }
                int height = rowHeight.intValue();
                int x = blkX.intValue();
                int y = blkY.intValue();
                blk =  this.genBlocks(blkLines, height, x, y, fact);
                break;
            } else { return null; }
        }   //checking if missing data.
        if (padSpeed == null) { return null; }
        if (padWidth == null) { return null; }
        if (blkX == null) { return null; }
        if (blkY == null) { return null; }
        if (rowHeight == null) { return null; }
        if (numBlk == null) { return null; }
        if (background == null) { return null; }
        if (name == null) { return null; }
        if (blk == null) { return null; }
        final Integer fPadSpeed = padSpeed;
        final Integer fPadWidth = padWidth;
        final Integer fNumBlk = numBlk;
        final String fName = name;
        final Sprite fBackground = background;
        final ArrayList<Block> fBlk = blk;
        final ArrayList<Velocity> fVel = vel;
        LevelInformation level = this.genLevel(fPadSpeed, fPadWidth, fNumBlk, fName, fBackground, fBlk, fVel);
        return level;
    }
    /**
     * generates Block List.
     * @param l **String List**
     * @param rowHeight **integer**
     * @param blkX **integer**
     * @param blkY **integer**
     * @param fact **BlocksFromSymbolsFactory**
     * @return toRet **Block List**
     */
    private ArrayList<Block> genBlocks(ArrayList<String> l, int rowHeight, int blkX, int blkY,
            BlocksFromSymbolsFactory fact) {
        ArrayList<Block> toRet = new ArrayList<Block>();
        int size = l.size();
        int ypos = blkY;
        for (int i = 0; i < size; i++) {
            ypos = ypos + (i * rowHeight);
            String line = l.get(i);
            int xpos = blkX;
            for (int j = 0; j < line.length(); j++) {
                Character c = line.charAt(j);
                if (fact.isSpaceSymbol(c.toString())) {
                    xpos = fact.getSpaceWidth(c.toString());
                } else if (fact.isBlockSymbol(c.toString())) {
                    Block b = fact.getBlock(c.toString(), xpos, ypos);
                    xpos = xpos + (int) b.getWidth();
                    toRet.add(b);
                } else {
                    return null;
                }
            }
            xpos = blkX;
            ypos = blkY;
        }
        return toRet;
    }
    /**
     * generates level.
     * @param ps **paddle speed**
     * @param pw **paddle width**
     * @param nb **number of blocks**
     * @param n **name**
     * @param bg **background**
     * @param blk **Block List**
     * @param vel **Velocity List**
     * @return level **LevelInformation**
     */
    private LevelInformation genLevel(Integer ps, Integer pw, Integer nb, String n, Sprite bg,
            ArrayList<Block> blk, ArrayList<Velocity> vel) {
        LevelInformation level = new LevelInformation() {
            public int numberOfBalls() { return vel.size(); }
            public List<Velocity> initialBallVelocities() { return vel; }
            public List<Point> ballLocations() {
                ArrayList<Point> l = new ArrayList<Point>();
                Point p = new Point(winX / 2,  winY - (radius * 3) - 15);
                int size = vel.size();
                for (int i = 0; i < size; i++) {
                    l.add(p);
                }
                return l;
            }
            public int paddleSpeed() { return ps.intValue(); }
            public int paddleWidth() { return pw.intValue(); }
            public String levelName() { return n; }
            public Sprite getBackground() { return bg; }
            public List<Block> blocks() { return blk; }
            public int numberOfBlocksToRemove() { return nb.intValue(); }
        };
        return level;
    }
}