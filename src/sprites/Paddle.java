package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
/**
 * @author David Abramov
 * Implementation of the Paddle class.
 */
public class Paddle implements Collidable, Sprite {
    private final biuoop.KeyboardSensor keyboard;
    private Point upperLeft;
    private final double width;
    private final double height;
    private final java.awt.Color color; //radius of the moving Ball.
    private final double speed;
    private final double difference;    //right limit is a double because width is a double, depends on width.
    private final double xRlim;
    private final int xLlim = 1;
    private final int paddleUpperLines = 5;
    private final int ballSpeed = 420;    //to prevent unnecessary Line intersection at edges.
    private final double eps = 0.000000000001;
    /**
     * Paddle object constructor.
     * @param upperLeft **upper left Point of the Paddle**
     * @param w **width of the Paddle**
     * @param h **height of the Paddle**
     * @param c **color of the Paddle**
     * @param d **Balls radius**
     * @param k **KeyboardSensor for the Paddle**
     * @param s **speed of the paddle**
     */
    public Paddle(Point upperLeft, double w, double h, java.awt.Color c, double d, biuoop.KeyboardSensor k, double s) {
        this.upperLeft = upperLeft;
        this.width = w;
        this.height = h;
        this.color = c;
        this.difference = d;
        this.keyboard = k;
        this.speed = s;
        this.xRlim = 799 - w;
    }
    /**
     * moves the Paddle left.
     * @param dt **change in frames per small time unit**
     */
    public void moveLeft(double dt) {
        double upLx = this.upperLeft.getX() - (this.speed * dt);
        if (upLx <= xLlim) {    //if left limit approached, stay there.
            this.upperLeft = new Point(xLlim, this.upperLeft.getY());
        } else {
            this.upperLeft = new Point(this.upperLeft.getX() - (this.speed * dt), this.upperLeft.getY());
        }
    }
    /**
     * moves the Paddle right.
     * @param dt **change in frames per small time unit**
     */
    public void moveRight(double dt) {
        double upLx = this.upperLeft.getX() + (this.speed * dt);
        if (upLx >= xRlim) {    //if right limit approached, stay there.
            this.upperLeft = new Point(xRlim, this.upperLeft.getY());
        } else {
            this.upperLeft = new Point(this.upperLeft.getX() + (this.speed * dt), this.upperLeft.getY());
        }
    }
    /**
     * timePassed method for Paddle, responsible for moving it left or right.
     * @param dt **change in frames per small time unit**
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }
    /**
     * draws the current Rectangle on the inputed surface.
     * @param surface **surface which the current Rectangle will be drawn on**
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }
    /**
     * returns a Rectangle bigger by the radius of the moving Ball, for calculation purposes.
     * @return **Rectangle - representing the Paddle for calculation purposes**
     */
    public Rectangle getCollisionRectangle() {  //paddles collisiion rectangle is actually a Line, thus width = 0.
        Point recUpperLeft = new Point(upperLeft.getX() - difference, upperLeft.getY() - difference);
        return new Rectangle(recUpperLeft, width + (2 * difference),  0);
    }
    /**
     * generates an array of upper Line subLines.
     * @param start **start Point of the upper Line**
     * @param w **width of each Line (5 Lines)**
     * @return toRet **Line[] - array of 5 sub Lines*
     */
    private Line[] genUpperLine(Point start, double w) {
        Line[] toRet = new Line[paddleUpperLines];
        double yVal = start.getY();
        double leftEdge = start.getX();
        double widOfLine = w / paddleUpperLines;
        for (int i = 0; i < paddleUpperLines; i++) {
            toRet[i] = new Line(leftEdge + (widOfLine * (i + 1)), yVal, leftEdge + (widOfLine * (i + 2)) - eps, yVal);
        }
        return toRet;
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
        Line[] upperLine = null;
        Line[] recLines = this.getCollisionRectangle().genRecLines(); //Ball hits upper Line of the Rectangle.
        upperLine = genUpperLine(recLines[0].start(), this.getCollisionRectangle().getWidth());
        if (hitPoint.isIntersecting(upperLine[0])) { //intersection with 1nd part.
            return Velocity.fromAngleAndSpeed(150, ballSpeed); //axe system is reversed, thus 150 = 300.
        }   //intersection with 2nd part, axe system is reversed, thus 120 = 330.
        if (hitPoint.isIntersecting(upperLine[1])) {
            return Velocity.fromAngleAndSpeed(120, ballSpeed);
        }   //intersection with 4th part, axe system is reversed, thus 30 = 60;
        if (hitPoint.isIntersecting(upperLine[3])) {
            return Velocity.fromAngleAndSpeed(60, ballSpeed);
        }   //intersection with 5th part, axe system is reversed, thus 60 = 30.
        if (hitPoint.isIntersecting(upperLine[4])) {
            return Velocity.fromAngleAndSpeed(30, ballSpeed);
        }   //intersection with 3rd part, default behavior like a block.
        dy = -1 * dy;
        return new Velocity(dx, dy);
    }
    /**
     * adds Paddle to the game.
     * @param g **game to add Paddle to**
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removes Paddle from the game.
     * @param g **game to remove Paddle from**
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}