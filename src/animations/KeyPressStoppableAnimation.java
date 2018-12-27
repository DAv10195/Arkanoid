package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author David Abramov.
 * KeyPressStoppableAnimation class implementation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean isAlreadyPressed;
    private boolean stop;
    private final Animation animation;
    private final biuoop.KeyboardSensor k;
    private final String endKey;
    /**
     * constructor for KeyPressStoppableAnimation object.
     * @param sensor **KeyboardSensor**
     * @param key **String**
     * @param a **Animation**
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation a) {
        this.k = sensor;
        this.endKey = key;
        this.animation = a;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * puts one frame on surface.
     * @param d **surface**
     * @param dt **change in frames per small time unit**
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.k.isPressed(this.endKey)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}