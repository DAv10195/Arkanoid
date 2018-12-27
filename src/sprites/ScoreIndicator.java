package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * ScoreIndicator class implementation.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;
    private final Point loc = new Point(475, 25);
    private final int fontSize = 20;
    /**
     * ScoreIndicator object constructor.
     * @param ctr **Counter**
     */
    public ScoreIndicator(Counter ctr) {
        this.score = ctr;
    }
    /**
     * draws ScoreIndicator on surface.
     * @param d **surface to draw on**
     */
    public void drawOn(DrawSurface d) {
        String str = "Score: ".concat(String.valueOf(score.getValue()));
        d.setColor(java.awt.Color.WHITE);
        d.drawText((int) loc.getX(), (int) loc.getY(), str, fontSize);
    }
    /**
     * adds ScoreIndicator to game.
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