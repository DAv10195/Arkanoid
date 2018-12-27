package geometry;
import java.util.ArrayList;
/**
 * @author David Abramov.
 * Implementation of the Rectangle class.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final int recLines = 4;
    private final double eps = 0.0001;
    /**
     * constructor for the Rectangle object.
     * @param upperLeft **upper left Point of the Rectangle**
     * @param width **width of the Rectangle**
     * @param height **height of the Rectangle**
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * getter for width field of the Rectangle.
     * @return **width of the Rectangle**
     */
    public double getWidth() {
        return width;
    }
    /**
     * getter for height field of the Rectangle.
     * @return **height of the Rectangle**
     */
    public double getHeight() {
        return height;
    }
    /**
     * getter for upper left field Point field of the Rectangle.
     * @return **upper left Point field of the Rectangle**
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * generates an array of 4 lines representing the inputed Rectangle.
     * @return **array of Lines representing the Rectangle**
     */
    public Line[] genRecLines() {
        Line[] lines = new Line[recLines];
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        //0 - upper, 1 - bottom, 2 - left, 3 - right.
        lines[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperRight.getX(), upperRight.getY());
        lines[1] = new Line(bottomLeft.getX(), bottomLeft.getY(), bottomRight.getX(), bottomRight.getY());
        lines[2] = new Line(upperLeft.getX(), upperLeft.getY() + eps, bottomLeft.getX(), bottomLeft.getY() - eps);
        lines[3] = new Line(upperRight.getX(), upperRight.getY() + eps, bottomRight.getX(), bottomRight.getY() - eps);
        return lines;
    }
    /**
     * checks if a Point is included in a Point List.
     * @param l **ArrayList of Points**
     * @param p **Point**
     * @return **boolean - true if Point is included in the List, false if not**
     */
    private boolean ifInList(ArrayList<Point> l, Point p) {
        int size = l.size();
        for (int i = 0; i < size; i++) {
            if (p.equals(l.get(i))) {
                return true;
            }
        }
        return false;
    }
    /**
     * returns an ArrayList (possibly empty) of intersection Points with the inputed Line.
     * @param line **inputed line**
     * @return interPoints **ArrayList of intersection Points with inputed Line**
     */
    @SuppressWarnings("rawtypes")
    public java.util.List intersectionPoints(Line line) {
        ArrayList<Point> interPoints = new ArrayList<Point>();
        Point interPoint = null;
        Line[] lines = this.genRecLines();
        for (int i = 0; i < recLines; i++) {
            interPoint = line.intersectionWith(lines[i]);
            if (interPoint != null && !this.ifInList(interPoints, interPoint)) {
                interPoints.add(interPoint);
            }
        }
        return interPoints;
    }
}