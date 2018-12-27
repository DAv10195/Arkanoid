package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import geometry.Point;
import geometry.Velocity;
import sprites.Background1;
import sprites.Block;
import sprites.Sprite;
/**
 * @author David Abramov.
 * Direct Hit Level implementation.
 */
public class DirectHit implements LevelInformation {
    private final int speed = 420;
    private final Velocity ballVelocity = Velocity.fromAngleAndSpeed(90, (double) speed);
    private final int winX = 800;
    private final int winY = 600;
    private final int padWidth = 100;
    private final int numBalls = 1;
    private final int radius = 10;
    private final int blkDim = 40;
    private final String name = "Direct Hit";
    private final Point loc = new Point(winX / 2, winY - 100);
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
        l.add(ballVelocity);
        return l;
    }
    /**
     * returns a List of initial Ball locations.
     * @return **List**
     */
    public List<Point> ballLocations() {
        ArrayList<Point> l = new ArrayList<Point>();
        l.add(loc);
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
        return new Background1();
    }
    /**
     * returns a List of Blocks.
     * @return **List**
     */
    public List<Block> blocks() {
        ArrayList<Block> l = new ArrayList<Block>();
        Block b = new Block(new Point(380, 180), blkDim, blkDim, Color.BLACK, radius, 1);
        b.addClr(-1, Color.RED);
        l.add(b);
        return l;
    }
    /**
     * returns the number of Blocks to be removed to pass the Level.
     * @return **integer**
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}