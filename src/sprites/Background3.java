package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
/**
 * @author David Abramov.
 * Background3 class implementation.
 */
public class Background3 implements Sprite {
    private final int winX = 800;
    private final int winY = 600;
    /**
     * draws the Sprite object onto the screen.
     * @param d **DrawSurface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.getHSBColor(0.4f, 0.8f, 0.5f));
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(50, 400, 110, 200);
        d.setColor(java.awt.Color.LIGHT_GRAY);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(60 + (j * 20), 410 + (i * 38), 10, 28);
            }
        }
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(90, 330, 30, 70);
        d.setColor(java.awt.Color.GRAY);
        d.fillRectangle(100, 160, 10, 170);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(105, 148, 12);
        d.setColor(java.awt.Color.RED);
        d.fillCircle(105, 148, 8);
        d.setColor(java.awt.Color.WHITE);
        d.fillCircle(105, 148, 4);
    }
    /**
     * executes the behavior of the selected object over time.
     * @param dt **change in frames per small time unit**
     */
    public void timePassed(double dt) {
        //to be updated...
    }
    /**
     * adds the Sprite object to the inputed game.
     * @param g **game to add the Sprite to**
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}