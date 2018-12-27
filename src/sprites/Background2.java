package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * Background2 class implementation.
 */
public class Background2 implements Sprite {
    private final int winX = 800;
    private final int winY = 600;
    private int dif = 70;
    private final Point center = new Point(150, 150);
    /**
     * draws the Sprite object onto the screen.
     * @param d **DrawSurface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.getHSBColor(0.28f, 62f, 0.4f));
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif - 10;
        d.setColor(java.awt.Color.getHSBColor(0.3f, 62f, 0.9f));
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif - 10;
        d.setColor(java.awt.Color.YELLOW);
        d.fillCircle((int) center.getX(), (int) center.getY(), dif);
        dif = dif + 20;
        for (int i = 0; i < dif; i++) {
            d.drawLine((int) center.getX(), (int) center.getY(), i * 12, winY / 2);
        }
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