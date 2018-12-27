package game;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import animations.Animation;
import animations.AnimationRunner;
import animations.EndScreen;
import animations.GameLevel;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import sprites.Counter;
/**
 * @author David Abramov.
 * GameFlow class implementation.
 */
public class GameFlow {
    private final GUI gui;
    private final AnimationRunner ar;
    private final biuoop.KeyboardSensor ks;
    private final String space = KeyboardSensor.SPACE_KEY;
    private boolean winOrLoose;
    private final Counter score;
    private final Counter lives;
    private final int tableSize = 10; //default table, for cases no high scores file is present.
    private HighScoresTable h;
    private final int winScreen = 0;
    private final int loseScreen = 1;
    private final int tableScreen = 2;
    /**
     * GameFlow object constructor.
     * @param g **GUI**
     * @param r **AnimatonRunner**
     * @param k **KeyboardSensor**
     */
    public GameFlow(GUI g, AnimationRunner r, biuoop.KeyboardSensor k) {
        this.gui = g;
        this.ar = r;
        this.ks = k;
        this.winOrLoose = true;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.h = new HighScoresTable(tableSize);
    }
    /**
     * generates list of KeyPressable Animations.
     * @return l **Animations List**
     */
    private ArrayList<Animation> genKeyAnimations() {
        ArrayList<Animation> l = new ArrayList<Animation>();
        l.add(new EndScreen(true, this.score));
        l.add(new EndScreen(false, this.score));
        l.add(new HighScoresAnimation(this.h));
        return l;
    }
    /**
     * Level runner.
     * @param levels **levels List**
     */
    public void runLevels(List<LevelInformation> levels) {
        File f = new File("highscores");
        try {   //case file already exists.
            if (f.isFile()) {
                HighScoresTable tmp = HighScoresTable.loadFromFile(f);
                if (tmp.size() > 1) {   //case table loaded successfully.
                    this.h = tmp;
                }
            } else {
                f.createNewFile();
                this.h.save(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Animation> animationsList = this.genKeyAnimations();
        int size = levels.size();
        for (int i = 0; i < size; i++) {
            LevelInformation info = levels.get(i);
            GameLevel l = new GameLevel(this.ar, info, this.ks, this.lives, this.score);
            l.initialize();
            while (l.getNumBlk() != 0 && this.lives.getValue() > 0) {
                l.playOneTurn();
            }
            if (this.lives.getValue() == 0) {
                this.winOrLoose = false;
                break;
            }
            this.score.increase(100);
        }
        if (this.winOrLoose) {
            this.ar.run(new KeyPressStoppableAnimation(this.ks, space, animationsList.get(winScreen)));
        } else {
            this.ar.run(new KeyPressStoppableAnimation(this.ks, space, animationsList.get(loseScreen)));
        }
        if (this.h.getRank(this.score.getValue()) <= this.h.size()) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            this.h.add(new ScoreInfo(name, this.score.getValue()));
        }
        this.ar.run(new KeyPressStoppableAnimation(this.ks, space, animationsList.get(tableScreen)));
        try {
            this.h.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}