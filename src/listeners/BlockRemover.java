package listeners;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author David Abramov.
 * BlockRemover class implementation.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;
    /**
     * BlockRemover object constructor.
     * @param g **game**
     * @param c **Counter**
     */
    public BlockRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.remainingBlocks = c;
    }
    /**
     * initiates the HitEvent.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
    }
}