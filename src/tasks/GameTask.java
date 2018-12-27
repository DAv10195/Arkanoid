package tasks;
import java.util.List;
import game.GameFlow;
import levels.LevelInformation;
/**
 * @author David Abramov.
 * GameTask class implementation.
 */
public class GameTask implements Task<Void> {
    private final GameFlow gf;
    private final List<LevelInformation> levels;
    /**
     * constructor for GameTask object.
     * @param g **GameFlow**
     * @param l **level List**
     */
    public GameTask(GameFlow g, List<LevelInformation> l) {
        this.gf = g;
        this.levels = l;
     }
     /**
      * runs Task.
      * @return **null**
      */
     public Void run() {
        this.gf.runLevels(this.levels);
        return null;
     }
}