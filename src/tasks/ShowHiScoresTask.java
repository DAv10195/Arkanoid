package tasks;
import animations.Animation;
import animations.AnimationRunner;
/**
 * @author David Abramov.
 * ShowHiScoresTask class implementation.
 */
public class ShowHiScoresTask implements Task<Void> {
    private final AnimationRunner runner;
    private final Animation highScoresAnimation;
    /**
     * constructor for ShowHiScoresTask object.
     * @param runner **AnimationRunner**
     * @param a **HighScoresAnimation**
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation a) {
        this.runner = runner;
        this.highScoresAnimation = a;
     }
     /**
      * runs highScoreAnimation.
      * @return **null**
      */
     public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
     }
}