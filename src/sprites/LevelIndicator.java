package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * LevelIndicator class implementation.
 */
public class LevelIndicator implements Sprite {
    private String name;
    private final Point loc = new Point(150, 25);
    private final int fontSize = 20;
    /**
     * LevelIndicator object constructor.
     * @param str **String**
     */
    public LevelIndicator(String str) {
        this.name = str;
    }
    /**
     * draws LevelIndicator on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        String str = "Level: ".concat(this.name);
        d.setColor(java.awt.Color.WHITE);
        d.drawText((int) loc.getX(), (int) loc.getY(), str, fontSize);
    }
    /**
     * adds LevelIndicator to game.
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