package sprites;
import java.util.ArrayList;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
/**
 * @author David Abramov.
 * Implementation of the GameEnvironment class.
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;
    /**
     * constructor for the GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * add inputed Collidable to Collidables List.
     * @param c **Collidable**
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * remove inputed Collidable from Collidables List.
     * @param c **Collidable**
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * calculates the closest intersection Point of the current movement Line of the ball start Point, and
     * returns information about it via the CollisionInfo object(Point and collision object).
     * @param trajectory **movement line of the Ball**
     * @return **CollisionInfo - Point and object of collision, null if no collision is about to happen**
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> intersected = new ArrayList<Collidable>();
        Point start = trajectory.start();
        Collidable cToRet = null;
        Point pToRet = null;
        Point pToMatch = null;
        Rectangle rec = null;
        int size = this.collidables.size(); //minimizing List to only those objects that intersect with trajectory.
        for (int i = 0; i < size; i++) {
            rec = this.collidables.get(i).getCollisionRectangle();
            pToMatch = trajectory.closestIntersectionToStartOfLine(rec);
            if (pToMatch == null) {
                continue;
            }
            intersected.add(this.collidables.get(i));
        }
        if (intersected.isEmpty()) {    //case no collision is about to happen.
            return null;
        }
        int i = 0;
        pToRet = trajectory.closestIntersectionToStartOfLine(intersected.get(i).getCollisionRectangle());
        cToRet = intersected.get(i);
        size = intersected.size();
        for (i = 1; i < size; i++) {    //finding the closest one.
            pToMatch = trajectory.closestIntersectionToStartOfLine(intersected.get(i).getCollisionRectangle());
            if (start.distance(pToMatch) < start.distance(pToRet)) {
                pToRet = pToMatch;
                cToRet = intersected.get(i);
            }
        }
        return new CollisionInfo(pToRet, cToRet);
    }
    /**
     * answers if the obstacle List is empty or not.
     * @return **boolean - true or false**
     */
    public boolean isEmpty() {
        return this.collidables.isEmpty();
    }
}