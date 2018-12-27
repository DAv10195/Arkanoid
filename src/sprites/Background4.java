package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
/**
 * @author David Abramov.
 * Background3 class implementation.
 */
public class Background4 implements Sprite {
    private final int winX = 800;
    private final int winY = 600;
    /**
     * draws the Sprite object onto the screen.
     * @param d **DrawSurface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.getHSBColor(0.6f, 0.9f, 0.8f));
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < 9; i++) {
            d.drawLine(130 + (i * 8), 400, 50 + (i * 10), winY);
        }
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillCircle(110, 390, 30);
        d.fillCircle(125, 420, 32);
        d.setColor(java.awt.Color.GRAY);
        d.fillCircle(160, 385, 35);
        d.fillCircle(210, 400, 35);
        d.fillCircle(175, 430, 28);
        d.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < 9; i++) {
            d.drawLine(580 + (i * 8), 500, 530 + (i * 10), winY);
        }
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillCircle(560, 490, 30);
        d.fillCircle(575, 520, 32);
        d.setColor(java.awt.Color.GRAY);
        d.fillCircle(610, 485, 35);
        d.fillCircle(660, 500, 35);
        d.fillCircle(625, 530, 28);
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