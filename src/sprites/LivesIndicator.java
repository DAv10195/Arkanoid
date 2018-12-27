package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * LivesIndicator class implementation.
 */
public class LivesIndicator implements Sprite {
    private final Counter lives;
    private final Point loc = new Point(15, 25);
    private final int fontSize = 20;
    /**
     * LivesIndicator object constructor.
     * @param ctr **Counter**
     */
    public LivesIndicator(Counter ctr) {
        this.lives = ctr;
    }
    /**
     * draws LivesIndicator on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        String str = "Lives: ".concat(String.valueOf(lives.getValue()));
        d.setColor(java.awt.Color.WHITE);
        d.drawText((int) loc.getX(), (int) loc.getY(), str, fontSize);
    }
    /**
     * adds LivesIndicator to game.
     * @param g **game**
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * initiates objects "behavior".
     * @param dt **change in frames per small time unit**
     */
    public void timePassed(double dt) {
        //to be updated...
    }
}