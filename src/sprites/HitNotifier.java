package sprites;
import listeners.HitListener;
/**
 * @author David Abramov.
 * HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * adds hl to listeners List.
     * @param hl **HitListener**
     */
    void addHitListener(HitListener hl);
    /**
     * removes hl to listeners List.
     * @param hl **HitListener**
     */
    void removeHitListener(HitListener hl);
}
