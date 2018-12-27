package game;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import animations.Animation;
import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.Menu;
import animations.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import input.LevelSetsMap;
import levels.LevelInformation;
import tasks.GameTask;
import tasks.QuitTask;
import tasks.ShowHiScoresTask;
import tasks.SubMenuTask;
import tasks.Task;
/**
 * @author David Abramov.
 * Ass6Game class, which runs the game.
 */
public class Ass6Game {
    /**
     * generates High Scores table.
     * @return **HighScoresAnimation**
     */
    private HighScoresAnimation hiScrGen() {
        int tableSize = 10;
        HighScoresTable h = null;
        File f = new File("highscores");
        try {   //case file already exists.
            if (f.isFile()) {
                h = HighScoresTable.loadFromFile(f);
                if (h.size() > 1) {   //case table loaded successfully.
                    return new HighScoresAnimation(h);
                }
            } else {
                f.createNewFile();
                h = new HighScoresTable(tableSize);
                h.save(f);
                return new HighScoresAnimation(h);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        h = new HighScoresTable(tableSize);
        return new HighScoresAnimation(h);
    }
    /**
     * runs the Game.
     * @param g **GUI**
     * @param r **AnimationRunner**
     * @param args **String array**
     * @param k **KeyboardSensor**
     */
    private void gameRunner(GUI g, AnimationRunner r, String[] args, KeyboardSensor k) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(k);
        menu.addSelection("q", "Quit", new QuitTask(g));
        Animation a = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, this.hiScrGen());
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(r, a));
        GameFlow game = new GameFlow(g, r, k);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(k);
        LevelSetsMap lsm = new LevelSetsMap(args[0]);
        if (!lsm.build()) {
            System.out.println("Error loading Files");
            g.close();
            return;
        }
        ArrayList<ArrayList<LevelInformation>> lists = lsm.getSets();
        ArrayList<String> strings = lsm.getToStrings();
        ArrayList<String> keys = lsm.getKeys();
        if (lists == null || strings == null || keys == null) {
            System.out.println("Error loading Files");
            g.close();
            return;
        }
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            subMenu.addSelection(keys.get(i), strings.get(i), new GameTask(game, lists.get(i)));
        }
        menu.addSelection("s", "Start Game", new SubMenuTask(r, subMenu));
        while (true) {
            r.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu = new MenuAnimation<Task<Void>>(k);
            menu.addSelection("q", "Quit", new QuitTask(g));
            a = new KeyPressStoppableAnimation(k, KeyboardSensor.SPACE_KEY, this.hiScrGen());
            menu.addSelection("h", "High Scores", new ShowHiScoresTask(r, a));
            game = new GameFlow(g, r, k);
            subMenu = new MenuAnimation<Task<Void>>(k);
            lsm = new LevelSetsMap(args[0]);
            if (!lsm.build()) {
                System.out.println("Error loading Files");
                g.close();
                return;
            }
            lists = lsm.getSets();
            strings = lsm.getToStrings();
            keys = lsm.getKeys();
            if (lists == null || strings == null || keys == null) {
                System.out.println("Error loading Files");
                g.close();
                return;
            }
            size = keys.size();
            for (int i = 0; i < size; i++) {
                subMenu.addSelection(keys.get(i), strings.get(i), new GameTask(game, lists.get(i)));
            }
            menu.addSelection("s", "Start Game", new SubMenuTask(r, subMenu));
        }
    }
    /**
     * main for Ass6Game class, which creates, initializes and runs the game.
     * @param args **array of Strings inputed to main from command prompt**
     */
    public static void main(String[] args) {
        String[] toLoad = null;
        if (args.length > 1) {
            System.out.println("Invalid passed arguments, should include none, or one path only!");
            return;
        }
        if (args.length == 1) {
            toLoad = args;
        } else {
            toLoad = new String[1];
            toLoad[0] = "level_sets.txt";
        }
        final int winX = 800;
        final int winY = 600;
        final int framesPerSecond = 60;
        GUI gui = new GUI("Arkanoid", winX, winY);
        biuoop.KeyboardSensor k = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, framesPerSecond);
        Ass6Game mainRunner = new Ass6Game();
        mainRunner.gameRunner(gui, runner, toLoad, k);
    }
}