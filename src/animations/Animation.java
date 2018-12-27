package animations;
import biuoop.DrawSurface;
/**
 * @author David Abramov.
 * Animation inteface.
 */
public interface Animation {
   /**
    * puts one frame on surface.
    * @param d **surface**
    * @param dt **change in frames per small time unit**
    */
   void doOneFrame(DrawSurface d, double dt);
   /**
    * stops Animation.
    * @return **boolean**
    */
   boolean shouldStop();
}