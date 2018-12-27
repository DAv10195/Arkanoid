package tasks;
import animations.AnimationRunner;
import animations.Menu;
/**
 * @author David Abramov.
 * implementation of SubMenuTask class.
 */
public class SubMenuTask implements Task<Void> {
    private final AnimationRunner runner;
    private final Menu<Task<Void>> menu;
    /**
     * SubMenuTask object constructor.
     * @param r **AnimationRunner**
     * @param m **MenuAnimation**
     */
    public SubMenuTask(AnimationRunner r, Menu<Task<Void>> m) {
        this.runner = r;
        this.menu = m;
    }
    /**
     * runs Task.
     * @return **null, Void object**
     */
    public Void run() {
        this.runner.run(this.menu);
        Task<Void> task = this.menu.getStatus();
        task.run();
        return null;
    }
}