package geometry;
import java.util.ArrayList;
/**
 * @author David Abramov.
 * Implementation of the Line class, which uses the Point class.
 */
public class Line {             //x1 and y1 are coordinates of start Point of the Line.
    private final double x1;
    private final double y1;    //x2 and y2 are coordinates of the end Point of the Line.
    private final double x2;
    private final double y2;
    /**
     * constructor which derives corresponding coordinates from Point objects as arguments.
     * @param start **start Point of the Line**
     * @param end   **end Point of the Line**
     */
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }
    /**
     * Line object constructor for coordinates sent as arguments without creating a Point object.
     * @param x1    **X value of Line starting Point**
     * @param y1    **Y value of Line starting Point**
     * @param x2    **X value of Line ending Point**
     * @param y2    **Y value of Line ending Point**
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    /**
     * length of current Line method.
     * @return  **double - length of current Line, using Point class distance method**
     */
    public double length() {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        return start.distance(end);
    }
    /**
     * method calculating the middle Point of current Line.
     * @return mid  **middle point of current Line**
     */
    public Point middle() {     //middle Point of two points is half of sum X values and Y values.
        double xMid = (x1 + x2) / 2;
        double yMid = (y1 + y2) / 2;
        Point mid = new Point(xMid, yMid);
        return mid;
    }
    /**
     * returns starting Point of the current Line.
     * @return start  **starting Point of the current Line**
     */
    public Point start() {
        Point start = new Point(x1, y1);
        return start;
    }
    /**
     * returns ending Point of the current Line.
     * @return end  **ending Point of the current Line**
     */
    public Point end() {
        Point end = new Point(x2, y2);
        return end;
    }
    /**
     * creates start and end Points for current and other line, and compares end and start Points using the
     * equals method of Point class. end and start Points will be each be compared to end and start Points of
     * the other line. in case one Point isn't the start or end of the other, Lines are not equal.
     * @param other **another Line, which the current Line will be compared to**
     * @return  **boolean - true if equals, false otherwise**
     */
    public boolean equals(Line other) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        Point startOther = new Point(other.x1, other.y1);
        Point endOther = new Point(other.x2, other.y2);
        if ((start.equals(startOther) || start.equals(endOther)) && (end.equals(endOther) || end.equals(startOther))) {
            return true;
        }
        return false;
    }
    /**
     * toString which prints the start and end Points of the current Line.
     * @return  **String - X and Y values of start and end Points**
     */
    public String toString() {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        return start.toString() + " to " + end.toString();
    }
    /**
     * calculates and returns the slope of the current Line.
     * @return  **double - slope of the current Line**
     */
    private double slope() {
        return (y2 - y1) / (x2 - x1);
    }
    /**
     * forms both Lines equations, and finds intersection Point. in case one of the Lines isn't a function,
     * it is represented with the equation x = a (a in R).
     * @param other **the Line which the current one will be intersected with**
     * @return intersection **Point object, representing the intersection Point of both Lines**
     */
    private Point infInter(Line other) {
        double retX = 0, retY = 0;      //case both Lines aren't functions (They collide or never intersect).
        if (x1 == x2 && other.x1 == other.x2) { //special case, when Lines intersect at edge of segment.
            if (x1 == other.x1) {
                if (y1 == other.y1 || y1 == other.y2) {
                    Point intersection = new Point(x1, y1);
                    return intersection;
                }
                if (y2 == other.y1 || y2 == other.y2) {
                    Point intersection = new Point(x1, y2);
                    return intersection;
                }
                if (x1 == x2 && y1 == y2) { //case "this" Line is a Point.
                    Point intersection = new Point(x1, y1);
                    return intersection;
                }
                if (other.x1 == other.x2 && other.y1 == other.y2) { //case "other" Line is a Point.
                    Point intersection = new Point(other.x1, other.y1);
                    return intersection;
                }
            }
            return null;
        }                       //next two blocks deal with case that only one Line is a function.
        if (x1 == x2 && other.x1 != other.x2) {
            retX = x1;                  //Linear equation: y - y0 = m(x - x0) = y = mx + y0 -mx0, b is y0 -mx0.
            double m1 = other.slope();
            double b1 = other.y1 - (m1 * other.x1);
            retY = (m1 * retX) + b1;
            Point intersection = new Point(retX, retY);
            return intersection;
        }                       //case the 'other' Line isn't a function.
        if (x1 != x2 && other.x1 == other.x2) {
            retX = other.x1;
            double m1 = this.slope();
            double b1 = this.y1 - (m1 * this.x1);
            retY = (m1 * retX) + b1;
            Point intersection = new Point(retX, retY);
            return intersection;
        }
        if (this.slope() == other.slope()) {    //equal slopes = none or infinite amount of intersection Points.
            if (y1 == y2 && other.y1 == other.y2) { //special case, when Lines intersect at edge of segment.
                if (y1 == other.y1) {
                    if (x1 == other.x1 || x1 == other.x2) {
                        Point intersection = new Point(x1, y1);
                        return intersection;
                    }
                    if (x2 == other.x1 || x2 == other.x2) {
                        Point intersection = new Point(x2, y1);
                        return intersection;
                    }
                }
            return null;
            }
        }                      //case both Lines are functions.
            double m1 = this.slope();
            double m2 = other.slope();
            double b1 = this.y1 - (m1 * this.x1);
            double b2 = other.y1 - (m2 * other.x1);
            retX = (b2 - b1) / (m1 - m2);
            retY = (m1 * retX) + b1;
            Point intersection = new Point(retX, retY);
            return intersection;
    }
    /**
     * checks if Point inputed is within the current Line or not (valid only for the intersection Point,
     * and thus private). Because of the behavior of linear functions, its enough only to check if the intersection
     * Point is between the X values of both Lines.
     * @param p **Point to check**
     * @return  **boolean - true if Point in line, false otherwise**
     */
    private boolean ifInLine(Point p) {
        double pXval = p.getX(), pYval = p.getY(); //case Line isn't a function we'll check Y values of the Point.
        if (x1 == x2 && pXval == x1) {
            double minY = Math.min(y1, y2), maxY = Math.max(y1, y2);
            if (pYval >= minY && pYval <= maxY) {
                return true;
            }
            return false;
        }       //case both Lines are functions, the intersection Point must be between both Lines X values.
        double minX = Math.min(x1, x2), maxX = Math.max(x1, x2);
        if (pXval >= minX && pXval <= maxX) {
            return true;
        }
        return false;
    }
    /**
     * calculates and returns the intersection Point between two Lines.
     * @param other **the Line which will be intersected with the current Line**
     * @return intersection **the intersection Point**
     */
    public Point intersectionWith(Line other) {
        Point intersection = this.infInter(other);
        if (intersection != null && this.ifInLine(intersection) && other.ifInLine(intersection)) {
            return intersection;
        }
        return null;
    }
    /**
     * checks if two Lines intersect.
     * @param other **the Line we check intersection with**
     * @return  **boolean - true if Lines intersect, otherwise false**
     */
    public boolean isIntersecting(Line other) { //if interSectionWith returns null, there's no intersection point.
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
    /**
     * returns closest intersection (with inputed Rectangle) Point to start Point.
     * @param rect **inputed Rectangle**
     * @return **Point - closest intersection Point to start Point**
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        @SuppressWarnings("unchecked")
        ArrayList<Point> l = (ArrayList<Point>) rect.intersectionPoints(this);
        if (l.isEmpty()) {  //case Line doesn't intersect Rectangle.
            return null;
        }
        int size = l.size(), i = 0;
        Point toRet = l.get(i);
        Point start = new Point(x1, y1);
        for (i = 1; i < size; i++) {
            if (start.distance(l.get(i)) < start.distance(toRet)) {
                toRet = l.get(i);
            }
        }
        return toRet;
    }
}