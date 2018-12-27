package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Point;
import geometry.Velocity;
import sprites.Background4;
import sprites.Block;
import sprites.Sprite;
/**
 * @author David Abramov.
 * Final Four Level implementation.
 */
public class FinalFour implements LevelInformation {
    private final int speed = 420;
    private final int winX = 800;
    private final int winY = 600;
    private final int padWidth = 150;
    private final int numBalls = 3;
    private final int numBlocks = 105;
    private final int radius = 10;
    private final int blkW = 53;
    private final int blkH = 30;
    private final int angle = 90;
    private final String name = "Final Four";
    private Point blkStart = new Point(2, 75);
    private final Point loc = new Point(winX / 2, winY - 150);
    private final Point loc1 = new Point(winX / 2 - 100, winY - 100);
    private final Point loc2 = new Point(winX / 2 + 100, winY - 100);
    /**
     * returns number of Balls.
     * @return **integer**
     */
    public int numberOfBalls() {
        return numBalls;
    }
    /**
     * returns a List of initial Ball Velocities.
     * @return **List**
     */
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> l = new ArrayList<Velocity>();
        l.add(Velocity.fromAngleAndSpeed(angle + 25, (double) speed));
        l.add(Velocity.fromAngleAndSpeed(angle, (double) speed));
        l.add(Velocity.fromAngleAndSpeed(angle - 25, (double) speed));
        return l;
    }
    /**
     * returns a List of initial Ball locations.
     * @return **List**
     */
    public List<Point> ballLocations() {
        ArrayList<Point> l = new ArrayList<Point>();
        l.add(loc1);
        l.add(loc);
        l.add(loc2);
        return l;
    }
    /**
     * returns Paddle speed.
     * @return **integer*
     */
    public int paddleSpeed() {
        return speed;
    }
    /**
     * returns Paddle width.
     * @return **integer**
     */
    public int paddleWidth() {
        return padWidth;
    }
    /**
     * returns the Level name.
     * @return **String**
     */
    public String levelName() {
        return name;
    }
    /**
     * returns the levels background.
     * @return **Sprite**
     */
    public Sprite getBackground() {
        return new Background4();
    }
    /**
     * returns a List of Blocks.
     * @return **List**
     */
    public List<Block> blocks() {
        ArrayList<Block> l = new ArrayList<Block>();
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 2);
            b.addClr(-1, Color.GRAY);
            b.addClr(2, Color.BLACK);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.RED);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.YELLOW);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.GREEN);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.WHITE);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.PINK);
            l.add(b);
        }
        blkStart = new Point(blkStart.getX(), blkStart.getY() + blkH);
        for (int i = 0; i < numBlocks / 7; i++) {
            Point p = new Point(blkStart.getX() + blkW * i, blkStart.getY());
            Block b = new Block(p, blkW, blkH, Color.BLACK, radius, 1);
            b.addClr(-1, Color.CYAN);
            l.add(b);
        }
        return l;
    }
    /**
     * returns the number of Blocks to be removed to pass the Level.
     * @return **integer**
     */
    public int numberOfBlocksToRemove() {
        return numBlocks;
    }
}