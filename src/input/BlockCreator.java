package input;
import sprites.Block;
/**
 * @author David Abramov.
 * BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * creates a Block.
     * @param xpos **X axe position**
     * @param ypos **Y axe position**
     * @return **Block**
     */
    Block create(int xpos, int ypos);
}