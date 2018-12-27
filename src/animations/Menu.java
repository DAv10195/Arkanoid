package animations;
/**
/**
 * @author David Abramov.
 * Menu interface.
 * @param <T> **generic Type**
 */
public interface Menu<T> extends Animation {
    /**
     * adds selection to Menu.
     * @param key **String**
     * @param message **String**
     * @param returnVal **returnVal**
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * gets current status of Menu.
     * @return **Menu object**
     */
    T getStatus();
    /**
     * adds sub menu to current menu.
     * @param key **String**
     * @param message **String**
     * @param subMenu **Menu**
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}