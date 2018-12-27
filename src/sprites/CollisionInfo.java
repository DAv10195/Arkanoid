package sprites;
import geometry.Point;
/**
 * @author David Abramov
 * Implementation of the CollisionInfo class.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    /**
     * constructor for CollisionInfo object.
     * @param p **collision Point**
     * @param o **collision object**
     */
    public CollisionInfo(Point p, Collidable o) {
        this.collisionPoint = p;
        this.collisionObject = o;
    }
    /**
     * getter for collision Point field.
     * @return **Point - collision Point**
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * getter for collision object field.
     * @return **Collidable - collision object**
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}