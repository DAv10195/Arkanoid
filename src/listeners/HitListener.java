package listeners;
import sprites.Ball;
import sprites.Block;
/**
 * @author David Abramov.
 * HitListener interface.
 */
public interface HitListener {
    /**
     * initiates the HitEvent.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    void hitEvent(Block beingHit, Ball hitter);
}
