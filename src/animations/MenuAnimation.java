package animations;
import java.util.ArrayList;
import java.util.HashMap;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
/**
 * @author David Abramov.
 * MenuAnimation class implementation.
 * @param <T> **generic type to be selected**
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private final biuoop.KeyboardSensor k;
    private final HashMap<String, T> taskMap;
    private final HashMap<String, String> names;
    private final int winX = 800;
    private final int winY = 600;
    private final int dif = 25;
    private final int fontSize1 = 30;
    private final int fontSize2 = 20;
    private final Point toDraw1 = new Point(320, 100);
    private final Point toDraw2 = new Point(320, 270);
    private final Point toDraw3 = new Point(370, 270);
    private String statusKey = "";
    /**
     * MenuAnimation object constructor.
     * @param sensor **KeyboardSensor**
     */
    public MenuAnimation(KeyboardSensor sensor) {
        this.stop = false;
        this.k = sensor;
        this.taskMap = new HashMap<String, T>();
        this.names = new HashMap<String, String>();
    }
    /**
     * puts one frame on surface.
     * @param d **surface**
     * @param dt **change in frames per small time unit**
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.CYAN);
        d.drawText((int) toDraw1.getX(), (int) toDraw1.getY(), "Arkanoid", fontSize1);
        d.setColor(java.awt.Color.WHITE);
        ArrayList<String> l = new ArrayList<String>(this.names.keySet());
        int size = l.size();
        for (int i = 0; i < size; i++) {
            String str = "(" + l.get(i) + ")";
            d.drawText((int) toDraw2.getX(), (int) toDraw2.getY() + (dif * i), str, fontSize2);
            d.drawText((int) toDraw3.getX(), (int) toDraw3.getY() + (dif * i), this.names.get(l.get(i)), fontSize2);
        }
        for (int i = 0; i < size; i++) {
            if (this.k.isPressed(l.get(i)) || this.k.isPressed(l.get(i).toUpperCase())) {
                this.statusKey = l.get(i);
                this.stop = true;
                break;
            }
        }
    }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * adds selection to Menu.
     * @param key **String**
     * @param message **String**
     * @param returnVal **generic return value**
     */
    public void addSelection(String key, String message, T returnVal) {
        this.taskMap.put(key, returnVal);
        this.names.put(key, message);
    }
    /**
     * getter for current status of menu.
     * @return T **Generic return value**
     */
    public T getStatus() {
        if (!this.statusKey.equals("")) {
            return this.taskMap.get(this.statusKey);
        }
        return null;
    }
    /**
     * adds sub menu to current menu.
     * @param key **String**
     * @param message **String**
     * @param subMenu **Menu**
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        //to be updated...
    }
}