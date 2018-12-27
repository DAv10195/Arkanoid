package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * @author David Abramov.
 * AnimationRunner class implementation.
 */
public class AnimationRunner {
    private final GUI gui;
    private int framesPerSecond;
    private final double dt;
    private final biuoop.Sleeper sleeper;
    /**
     * AnimationRunner object constructor.
     * @param g **GUI**
     * @param fraPrSec **frames per second**
     */
    public AnimationRunner(GUI g, int fraPrSec) {
        this.gui = g;
        this.framesPerSecond = fraPrSec;
        this.dt = (double) 1 / fraPrSec;
        this.sleeper = new biuoop.Sleeper();
    }
    /**
     * runs inputed Animation.
     * @param animation **Animation**
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
           long startTime = System.currentTimeMillis(); //timing.
           DrawSurface d = this.gui.getDrawSurface();
           animation.doOneFrame(d, this.dt);
           this.gui.show(d);
           long usedTime = System.currentTimeMillis() - startTime;
           long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
           if (milliSecondLeftToSleep > 0) {
               this.sleeper.sleepFor(milliSecondLeftToSleep);
           }
       }
    }
    /**
     * sets FramesPerSecond.
     * @param sec **frames per second**
     */
    public void setFramesPerSecond(double sec) {
        this.framesPerSecond = (int) sec;
    }
    /**
     * getter for framesPerSecond field.
     * @return **integer**
     */
    public int getFps() {
        return this.framesPerSecond;
    }
}