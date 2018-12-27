package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
/**
 * @author David Abramov
 * Implementation of the Ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private final double radius;
    private final java.awt.Color color;
    private Velocity v;
    private final int xMax;
    private final int xMin;
    private final int yMax;
    private final int yMin;
    private final GameEnvironment env;  //value used to move Ball "almost" to collision Point.
    private final double eps = 1.000000000001;
    /**
     * Ball object constructor.
     * @param center **center Point of the Ball**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     */
    public Ball(Point center, double r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = 0;
        this.xMin = 0;
        this.yMax = 0;
        this.yMin = 0;
        this.env = null;
    }
    /**
     * Ball object constructor.
     * @param x **X value of the Ball center Point**
     * @param y **Y value of the Balls center Point**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     */
    public Ball(double x, double y, double r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = 0;
        this.xMin = 0;
        this.yMax = 0;
        this.yMin = 0;
        this.env = null;
    }
    /**
     * Ball object constructor.
     * @param center **center Point of the Ball**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     * @param xMax **X axe border**
     * @param yMax **Y axe border**
     */
    public Ball(Point center, double r, java.awt.Color color, int xMax, int yMax) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = xMax;
        this.xMin = 0;
        this.yMax = yMax;
        this.yMin = 0;
        this.env = null;
    }
    /**
     * Ball object constructor.
     * @param x **X value of the Ball center Point**
     * @param y **Y value of the Balls center Point**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     * @param xMax **X axe border**
     * @param yMax **Y axe border**
     */
    public Ball(double x, double y, double r, java.awt.Color color, int xMax, int yMax) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = xMax;
        this.xMin = 0;
        this.yMax = yMax;
        this.yMin = 0;
        this.env = null;
    }
    /**
     * Ball object constructor.
     * @param center **center Point of the Ball**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     * @param xMax **X axe border**
     * @param xMin **X axe minimal bound**
     * @param yMax **Y axe border**
     * @param yMin **Y axe minimal bound**
     */
    public Ball(Point center, double r, java.awt.Color color, int xMax, int xMin, int yMax, int yMin) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        this.env = null;
    }
    /**
     * Ball object constructor.
     * @param x **X value of the Ball center Point**
     * @param y **Y value of the Balls center Point**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     * @param env **Balls GameEnvironment**
     */
    public Ball(double x, double y, double r, java.awt.Color color, GameEnvironment env) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = 0;
        this.xMin = 0;
        this.yMax = 0;
        this.yMin = 0;
        this.env = env;
    }
    /**
     * Ball object constructor.
     * @param center **center Point of the Ball**
     * @param r **radius of the Balls inner circle**
     * @param color **color of the Ball**
     * @param env **Balls GameEnvironment**
     */
    public Ball(Point center, double r, java.awt.Color color, GameEnvironment env) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.xMax = 0;
        this.xMin = 0;
        this.yMax = 0;
        this.yMin = 0;
        this.env = env;
    }
    /**
     * Getter for the X value of the current Balls center Point.
     * @return  **integer - X value of the center Point**
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * Getter for the Y value of the current Balls center Point.
     * @return  **integer - Y value of the center Point**
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * Getter for the current Balls inner circle radius.
     * @return  **integer - radius of the Balls inner circle**
     */
    public int getSize() {
        return (int) radius;
    }
    /**
     * Getter for the current Balls color.
     * @return  **java.awt.Color - color of the current Ball**
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * draws the current Ball on the inputed surface.
     * @param surface **surface which the current Ball will be drawn on**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) radius);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), (int) radius);
    }
    /**
     * Setter for Velocity field.
     * @param toSet **Velocity to be set for the current Ball**
     */
    public void setVelocity(Velocity toSet) {
        this.v = toSet;
    }
    /**
     * Setter for Velocity field (with inputed Velocity arguments).
     * @param dx **change in X axe**
     * @param dy **change in Y axe**
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Getter for Velocity field.
     * @return v **Velocity of current Ball**
     */
    public Velocity getVelocity() {
        return v;
    }
    /**
     * method responsible for "moving" the Ball, checking if Ball hits boundaries or
     * objects and responding by changing theBalls direction in the correct way. In case no boundaries
     * or objects we're specified for the Ball, it will bounce out of the window.
     * @param dt **change in frames per small time unit**
     */
    public void moveOneStep(double dt) {
        int flag = 0;
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        double pXval = this.getX(), pYval = this.getY(), r = this.getSize();
        if (xMax > xMin && yMax > yMin) {   //case boundaries were passed to Ball constructor.
            if (pXval + r + dx > xMax) { //case Ball approaches X axe right boundary.
                pXval = xMax - r;
                this.setVelocity(-1 * dx, dy);
                flag++;
            }
            if (pXval - r + dx < xMin) {       //case Ball approaches X axe left boundary.
                pXval = yMin + r;
                this.setVelocity(-1 * dx, dy);
                flag++;
            }
            if (pYval + r + dy > yMax) { //case Ball approaches Y axe bottom boundary.
                pYval = yMax - r;
                this.setVelocity(dx, -1 * dy);
                 flag++;
            }
            if (pYval - r + dy < yMin) {       //case Ball approaches Y axe upper boundary.
                pYval = yMin + r;
                this.setVelocity(dx, -1 * dy);
                flag++;
            }                               //case velocity has been changed in cases Ball approaches boundaries.
            if (flag > 0) {
                this.center = new Point(pXval, pYval);
            } else {                          //case boundaries were'nt approached.
                this.center = this.getVelocity().applyToPoint(this.center, dt);
                }
        } else if (env != null && !this.env.isEmpty()) {    //case Ball has GameEnvirornment.
            Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center, dt));
            CollisionInfo collis = this.env.getClosestCollision(trajectory);
            if (collis == null) { //no collision.
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            } else {    //"epsilon" doubles to move the Ball "almost" to collision Point.
                double epsilonX = 0;
                double epsilonY = 0;
                Point collisionPoint = collis.collisionPoint();
                if (this.center.getX() <= collisionPoint.getX()) {  //"left or right" collision.
                    epsilonX = -1 * eps;
                } else {
                    epsilonX = eps;
                }
                if (this.center.getY() <= collisionPoint.getY()) {  //"upper or bottom" collision.
                    epsilonY = -1 * eps;
                } else {
                    epsilonY = eps;
                }   //moving the Ball and adjusting the Balls Velocity to the "hit".
                Velocity newVel = collis.collisionObject().hit(this, collisionPoint, this.v);
                this.setVelocity(newVel);
                this.center = new Point(collisionPoint.getX() + epsilonX, collisionPoint.getY() + epsilonY);
            }
        } else {  //case boundaries weren't passed to Ball constructor.
            this.center = this.getVelocity().applyToPoint(this.center, dt);
            }
    }
    /**
     * calls the Balls behavior function, moveOneStep.
     * @param dt **change in frames per small time unit**
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
    /**
     * adds Ball to the game.
     * @param g **game to add Ball to**
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * removes Ball from the game.
     * @param g **game to remove Ball from**
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}