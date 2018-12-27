package listeners;
import sprites.Ball;
import sprites.Block;
import sprites.Counter;
/**
 * @author David Abramov.
 * ScoreTrackingListener class implementation.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter curScore;
    /**
     * ScoreTrackingListener object constrctor.
     * @param ctr **Counter**
     */
    public ScoreTrackingListener(Counter ctr) {
        this.curScore = ctr;
    }
    /**
     * initiates the HitEvent.
     * @param beingHit **Block**
     * @param hitter **Ball**
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.curScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.curScore.increase(10);
        }
    }
}