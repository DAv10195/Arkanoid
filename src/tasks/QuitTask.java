package tasks;
import biuoop.GUI;
/**
 * @author David Abramov.
 * implementation of QuitTask class.
 */
public class QuitTask implements Task<Void> {
    private final GUI gui;
    /**
     * constructor for QuitTask object.
     * @param g **GUI**
     */
    public QuitTask(GUI g) {
        this.gui = g;
     }
     /**
      * runs Task.
      * @return **null**
      */
     public Void run() {
        this.gui.close();
        return null;
     }
}