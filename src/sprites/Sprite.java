package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
/**
 * @author David Abramov.
 * Sprite interface.
 */
public interface Sprite {
    /**
     * draws the Sprite object onto the screen.
     * @param d **DrawSurface to draw on**
     */
    void drawOn(DrawSurface d);
    /**
     * executes the behavior of the selected object over time.
     * @param dt **change in frames per small time unit**
     */
    void timePassed(double dt);
    /**
     * adds the Sprite object to the inputed game.
     * @param g **game to add the Sprite to**
     */
    void addToGame(GameLevel g);
}