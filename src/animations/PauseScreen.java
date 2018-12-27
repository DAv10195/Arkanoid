package animations;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * PauseScreen class implementation.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    private final Point toDraw = new Point(180, 300);
    private final int fontSize = 30;
    private final int winX = 800;
    private final int winY = 600;
    /**
     * PauseScreen object constructor.
     */
    public PauseScreen() {
        this.stop = false;
     }
    /**
     * puts one frame on surface.
     * @param d **surface**
     * @param dt **change in frames per small time unit**
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.WHITE);
        d.drawText((int) toDraw.getX(), (int) toDraw.getY(), "Paused - press space to continue", fontSize);
     }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}