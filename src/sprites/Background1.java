package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
/**
 * @author David Abramov.
 * Background1 class implementation.
 */
public class Background1 implements Sprite {
    private final int winX = 800;
    private final int winY = 600;
    private final int dif = 30;
    private final int radius = 80;
    private final Point center = new Point(winX / 2, winY / 3);
    /**
     * draws the Sprite object onto the screen.
     * @param d **DrawSurface to draw on**
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.BLUE);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius + dif);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius + dif * 2);
        d.drawLine(winX / 2, 50, winX / 2, 350);
        d.drawLine(250, winY / 3, 550, winY / 3);
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