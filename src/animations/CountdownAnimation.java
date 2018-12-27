package animations;
import biuoop.DrawSurface;
import geometry.Point;
import sprites.Counter;
import sprites.SpriteCollection;
/**
 * @author David Abramov.
 * implementation of CountdownAnimation class.
 */
public class CountdownAnimation implements Animation {
    private final Point toDraw = new Point(400, 400);
    private final int fontSize = 50;
    private final double numOfSeconds;
    private final Counter ctr;
    private final SpriteCollection sprites;
    private final int thugLife = (int) toDraw.getX() - 130;
    private boolean running = true;
    /**
     * CountdownAnimation object constructor.
     * @param numOfSec **number of seconds to sleep**
     * @param countFrom **integer to count from**
     * @param gameScreen **Sprite Collection to draw**
     */
    public CountdownAnimation(double numOfSec, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSec;
        this.ctr = new Counter(countFrom);
        this.sprites = gameScreen;
     }
    /**
     * puts one frame on surface.
     * @param d **surface**
     * @param dt **change in frames per small time unit**
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        d.setColor(java.awt.Color.WHITE);
        if (this.ctr.getValue() == 0) {
            d.drawText(thugLife, (int) toDraw.getY(), "THUGLIFE!", fontSize);
            this.running = false;
        } else {
            d.drawText((int) toDraw.getX(), (int) toDraw.getY(), String.valueOf(this.ctr.getValue()), fontSize);
            this.ctr.decrease(1);
        }
     }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * getter for numOfSeconds.
     * @return **double**
     */
    public double getSec() {
        return this.numOfSeconds;
    }
}