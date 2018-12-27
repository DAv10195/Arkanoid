package geometry;
/**
 * @author David Abramov
 * Implementation of the Velocity class.
 */
public class Velocity {
    private final double dx;
    private final double dy;
    /**
     * Velocity object constructor.
     * @param dx **change in X axe**
     * @param dy **change in Y axe**
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Getter for dx field.
     * @return **current dx**
     */
    public double getDx() {
        return dx;
    }
    /**
     * Getter for dy field.
     * @return **current dy**
     */
    public double getDy() {
        return dy;
    }
    /**
     * applies velocity to Point, thus returning the new Point after the "move" according to Velocity.
     * @param p **current Point the object is at**
     * @return **Point - new Point the object "moved" to**
     * @param dt **change in frames per small time unit**
     */
    public Point applyToPoint(Point p, double dt) {
        Point newP = new Point(p.getX() + (dx * dt), p.getY() + (dy * dt));
        return newP;
    }
    /**
     * static method which allows setting Velocity from angle and speed.
     * @param angle **angle which we want the Ball to move in**
     * @param speed **speed in which we want the Ball to move at**
     * @return **Velocity - new Velocity according to inputed angle and speed**
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {  //our Y axe is reversed, thus dy = -sinx.
        double dy = Math.sin(Math.toRadians(-1 * angle)) * speed;
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
}