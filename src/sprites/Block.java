package sprites;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
/**
 * @author David Abramov.
 * Implementation of the Block class, which implements the Collidable interface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final java.awt.Color strokeColor; //radius of the moving Ball.
    private final double difference;
    private int num;
    private final ArrayList<HitListener> hitListeners;
    private HashMap<Integer, Color> mapClr;
    private HashMap<Integer, Image> mapImg;
    /**
     * Block object constructor.
     * @param upperLeft **upper left Point of the Block**
     * @param width **width of the Block**
     * @param height **height of the Block**
     * @param color **color of the Block**
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.strokeColor = color;
        this.difference = 0;
        this.num = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.mapClr = new HashMap<Integer, Color>();
        this.mapImg = new HashMap<Integer, Image>();
    }
    /**
     * Block object constructor.
     * @param upperLeft **upper left Point of the Block**
     * @param width **width of the Block**
     * @param height **height of the Block**
     * @param color **color of the Blocks stroke**
     * @param difference **Balls radius**
     * @param n **start value of hit counter**
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color, double difference, int n) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.strokeColor = color;
        this.difference = difference;
        this.num = n;
        this.hitListeners = new ArrayList<HitListener>();
        this.mapClr = new HashMap<Integer, Color>();
        this.mapImg = new HashMap<Integer, Image>();
    }
    /**
     * draws the current Rectangle on the inputed surface.
     * @param surface **surface which the current Rectangle will be drawn on**
     */
    public void drawOn(DrawSurface surface) {
        if (this.mapClr.containsKey(this.num)) {
            surface.setColor(this.mapClr.get(this.num));
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        } else if (this.mapImg.containsKey(this.num)) {
            surface.drawImage((int) upperLeft.getX(), (int) upperLeft.getY(), this.mapImg.get(this.num));
        } else if (this.mapImg.containsKey(-1)) {   //-1 is default "fill" value.
            surface.drawImage((int) upperLeft.getX(), (int) upperLeft.getY(), this.mapImg.get(-1));
        } else {
            surface.setColor(this.mapClr.get(-1));
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }
        if (this.strokeColor != null) {
            surface.setColor(this.strokeColor);
            surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }
    }
    /**
     * returns a Rectangle bigger by the radius of the moving Ball, for calculation purposes.
     * @return **Rectangle - representing the Block for calculation purposes**
     */
    public Rectangle getCollisionRectangle() {
        Point recUpperLeft = new Point(upperLeft.getX() - difference, upperLeft.getY() - difference);
        return new Rectangle(recUpperLeft, width + (2 * difference), height + (2 * difference));
    }
    /**
     * notifies all listeners this Block has been Hit.
     * @param hitter **hitting Ball**
     */
    private void notifyHit(Ball hitter) {   //copy of listeners List, to avoid exceptions during runtime.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        int size = listeners.size();
        for (int i = 0; i < size; i++) {
           listeners.get(i).hitEvent(this, hitter);
        }
     }
    /**
     * adjust the Velocity of the moving Ball to the corresponding collision.
     * @param hitter **Ball**
     * @param collisionPoint **collision Point**
     * @param currentVelocity **current Velocity of the Ball**
     * @return **Velocity - new adjusted Velocity after collision**
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        Line hitPoint = new Line(collisionPoint, collisionPoint);
        Line[] recLines = this.getCollisionRectangle().genRecLines();   //upper/bottom collision.
        if (hitPoint.isIntersecting(recLines[0]) || hitPoint.isIntersecting(recLines[1])) {
            dy = -1 * dy;
        }  //Ball hits left or right lines of the Rectangle.
        if (hitPoint.isIntersecting(recLines[2]) || hitPoint.isIntersecting(recLines[3])) {
            dx = -1 * dx;
        }
        if (this.num > 0) {
            this.num--;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    /**
     * timePassed method for Block object, to be updated...
     * @param dt **change in frames per small time unit**
     */
    public void timePassed(double dt) {
        //to be updated...
    }
    /**
     * adds Block to the game.
     * @param g **game to add the Block to**
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removes Block to the game.
     * @param g **game to remove the Block from**
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    /**
     * adds hl to listeners List.
     * @param hl **HitListener**
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * removes hl to listeners List.
     * @param hl **HitListener**
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * returns hitPoints left.
     * @return **integer**
     */
    public int getHitPoints() {
        return this.num;
    }
    /**
     * adds image to image map.
     * @param key **integer**
     * @param img **java.awt.Image**
     */
    public void addImg(int key , Image img) {
        this.mapImg.put(key, img);
    }
    /**
     * adds color to color map.
     * @param key **integer**
     * @param clr **java.awt.Color**
     */
    public void addClr(int key, Color clr) {
        this.mapClr.put(key, clr);
    }
    /**
     * sets colors map to inputed one.
     * @param m **numbers to colors map**
     */
    public void setMapClr(HashMap<Integer, Color> m) {
        this.mapClr = m;
    }
    /**
     * sets images map to inputed one.
     * @param m **numbers to images map**
     */
    public void setMapImg(HashMap<Integer, Image> m) {
        this.mapImg = m;
    }
    /**
     * returns Blocks width.
     * @return **double**
     */
    public double getWidth() {
        return this.width;
    }
}