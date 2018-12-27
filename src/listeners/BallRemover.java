package listeners;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author David Abramov.
 * BallRemover class implementation.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;
    /**
     * BallRemover object constructor.
     * @param g **game**
     * @param c **Counter**
     */
    public BallRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.remainingBalls = c;
    }
    /**
     * initiates the HitEvent.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == -2) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
        }
    }
}