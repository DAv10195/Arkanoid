package sprites;
import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 * @author David Abramov.
 * Implementation of the SpriteCollection class.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;
    /**
     * constructor for the SpriteCollection object.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }
    /**
     * adds the inputed Sprite object to the collection.
     * @param s **Sprite to add**
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * removes the inputed Sprite object from the collection.
     * @param s **Sprite to remove**
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * calls the timePassed method for all sprites in the collection, thus executing their behavior.
     * @param dt **change in frames per small time unit**
     */
    public void notifyAllTimePassed(double dt) { //copy of sprites List, to avoid problems during runtime.
        ArrayList<Sprite> sprCpy = new ArrayList<Sprite>(this.sprites);
        int size = sprCpy.size();
        for (int i = 0; i < size; i++) {
            sprCpy.get(i).timePassed(dt);
        }
    }
    /**
     * draws all sprites in the collection onto the surface.
     * @param d **surface to draw sprites on**
     */
    public void drawAllOn(DrawSurface d) {
        int size = this.sprites.size();
        for (int i = 0; i < size; i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}