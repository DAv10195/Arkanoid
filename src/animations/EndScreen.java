package animations;
import biuoop.DrawSurface;
import geometry.Point;
import sprites.Counter;
/**
 * @author David Abramov.
 * EndScreen class implementation.
 */
public class EndScreen implements Animation {
    private final boolean winOrLose;
    private final Counter score;
    private boolean stop;
    private final Point toDraw1 = new Point(200, 300);
    private final Point toDraw2 = new Point(200, 400);
    private final int fontSize = 30;
    private final int winX = 800;
    private final int winY = 600;
    /**
     * EndScreen object constructor.
     * @param b **boolean**
     * @param s **score Counter**
     */
    public EndScreen(boolean b, Counter s) {
        this.stop = false;
        this.winOrLose = b;
        this.score = s;
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
        if (this.winOrLose) {
            String str = "You Win! Your score is ".concat(String.valueOf(this.score.getValue()));
            d.drawText((int) toDraw1.getX(), (int) toDraw1.getY(), str, fontSize);
        } else {
            String str = "Game Over. Your score is ".concat(String.valueOf(this.score.getValue()));
            d.drawText((int) toDraw1.getX(), (int) toDraw1.getY(), str, fontSize);
        }
        d.drawText((int) toDraw2.getX(), (int) toDraw2.getY(), "press space to continue", fontSize);
     }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}