package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Point;
import geometry.Velocity;
import sprites.Background2;
import sprites.Block;
import sprites.Sprite;
/**
 * @author David Abramov.
 * Wide Easy Level implementation.
 */
public class WideEasy implements LevelInformation {
    private final int speed = 420;
    private final int winX = 800;
    private final int winY = 600;
    private final int padWidth = 500;
    private final int numBalls = 8;
    private final int numBlocks = 15;
    private final int radius = 10;
    private final int blkW = 53;
    private final int blkH = 30;
    private final int angle = 90;
    private final String name = "Wide Easy";
    private final Point blkStart = new Point(2, 300);
    private final int ballChange = 35;
    private final Point loc1 = new Point(winX / 2 - 200, winY - 100);
    private final Point loc2 = new Point(winX / 2 - 200 + (3 * ballChange) + 250, winY - 100);
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
        for (int i = 3; i >= 0; i--) {
            l.add(Velocity.fromAngleAndSpeed(angle + 15 * i, (double) speed));
        }
        for (int i = 3; i >= 0; i--) {
            l.add(Velocity.fromAngleAndSpeed(angle - 15 * i, (double) speed));
        }
        return l;
    }
    /**
     * returns a List of initial Ball locations.
     * @return **List**
     */
    public List<Point> ballLocations() {
        ArrayList<Point> l = new ArrayList<Point>();
        int dif = 5;
        for (int i = 0; i < numBalls / 2; i++) {
            Point p = new Point(loc1.getX() + ballChange * i, loc1.getY() - ballChange * i + dif * i);
            l.add(p);
        }
        for (int i = 0; i < numBalls / 2; i++) {
            Point p = new Point(loc2.getX() - ballChange * i, loc2.getY() - ballChange * i + dif * i);
            l.add(p);
        }
        return l;
    }
    /**
     * returns Paddle speed.
     * @return **integer*
     */
    public int paddleSpeed() {
        return speed - 240;
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
        return new Background2();
    }
    /**
     * returns a List of Blocks.
     * @return **List**
     */
    public List<Block> blocks() {
        ArrayList<Block> l = new ArrayList<Block>();
        int i = 0;
        for (; i < 2; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.RED);
            l.add(block);
        }
        for (; i < 4; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.ORANGE);
            l.add(block);
        }
        for (; i < 6; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.YELLOW);
            l.add(block);
        }
        for (; i < 9; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.GREEN);
            l.add(block);
        }
        for (; i < 11; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, java.awt.Color.BLUE);
            l.add(block);
        }
        for (; i < 13; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.PINK);
            l.add(block);
        }
        for (; i < numBlocks; i++) {
            Point curStart = new Point(blkStart.getX()  + blkW * i, blkStart.getY());
            Block block = new Block(curStart, blkW, blkH, Color.BLACK, radius, 1);
            block.addClr(-1, Color.CYAN);
            l.add(block);
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