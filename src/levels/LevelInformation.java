package levels;
import java.util.List;

import geometry.Point;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;
/**
 * @author David Abramov.
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * returns number of Balls.
     * @return **integer**
     */
    int numberOfBalls();
    /**
     * returns a List of initial Ball Velocities.
     * @return **List**
     */
    List<Velocity> initialBallVelocities();
    /**
     * returns a List of initial Ball locations.
     * @return **List**
     */
    List<Point> ballLocations();
    /**
     * returns Paddle speed.
     * @return **integer*
     */
    int paddleSpeed();
    /**
     * returns Paddle width.
     * @return **integer**
     */
    int paddleWidth();
    /**
     * returns the Level name.
     * @return **String**
     */
    String levelName();
    /**
     * returns the levels background.
     * @return **Sprite**
     */
    Sprite getBackground();
    /**
     * returns a List of Blocks.
     * @return **List**
     */
    List<Block> blocks();
    /**
     * returns the number of Blocks to be removed to pass the Level.
     * @return **integer**
     */
    int numberOfBlocksToRemove();
}