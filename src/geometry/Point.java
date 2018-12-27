package geometry;
/**
 * @author David Abramov.
 * Implementation of the Point class.
 */
public class Point {    //each Point has a X and a Y constant values.
    private final double x;
    private final double y;
    /**
     * Point object constructor.
     * @param x **X value for the constructed Point**
     * @param y **Y value for the constructed Point**
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * returns the distance between the current Point and another Point.
     * @param other **Point which distance will be measured to**
     * @return  **double - the distance between both Points**
     */
    public double distance(Point other) {   //square power of difference between X and Y values.
        double xDifSqr = (this.x - other.x) * (this.x - other.x);
        double yDifSqr = (this.y - other.y) * (this.y - other.y);   //square root of the sum.
        return Math.sqrt((xDifSqr + yDifSqr));
    }
    /**
     * compares X and Y values of both points. If at least one differs, returns false, otherwise true.
     * @param other **Point which will be compared to the current Point**
     * @return  **boolean - true if equals, false otherwise**
     */
    public boolean equals(Point other) {
        if (this.x != other.x || this.y != other.y) {
            return false;
        }
        return true;
    }
    /**
     * toString method which prints the Point.
     * @return **String - contains X and Y values of current Point**
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    /**
     * Getter for the X field of the current Point.
     * @return x  **double - X value of current Point**
     */
    public double getX() {
        return x;
    }
    /**
     * Getter for the Y field of the current Point.
     * @return y  **double - Y value of current Point**
     */
    public double getY() {
        return y;
    }
}