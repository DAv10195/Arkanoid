package tasks;
/**
/**
 * @author David Abramov.
 * Task interface.
 * @param <T> **generic return value type**
 */
public interface Task<T> {
    /**
     * runs Task.
     * @return **chosen return value type**
     */
    T run();
}