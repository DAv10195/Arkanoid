package input;
import java.util.HashMap;
import sprites.Block;
/**
 * @author David Abramov.
 * implementation of BlocksFromSymbolsFactory class.
 */
public class BlocksFromSymbolsFactory {
    private final HashMap<String, Integer> spacerWidths = new HashMap<String, Integer>();
    private final HashMap<String, BlockCreator> blockCreators = new HashMap<String, BlockCreator>();
    /**
     * adds blockCreator to factory.
     * @param key **String**
     * @param bc **BlockCreator**
     */
    public void addBlkCreator(String key, BlockCreator bc) {
        this.blockCreators.put(key, bc);
    }
    /**
     * adds spacer to factory.
     * @param key **String**
     * @param n **Integer**
     */
    public void addSpc(String key, Integer n) {
        this.spacerWidths.put(key, n);
    }
    /**
     * checks if inputed key maps to space symbol.
     * @param s **String**
     * @return **boolean**
     */
    public boolean isSpaceSymbol(String s) {
        if (this.spacerWidths.containsKey(s)) {
            return true;
        }
        return false;
    }
    /**
     * checks if inputed key maps to Block symbol.
     * @param s **String**
     * @return **boolean**
     */
    public boolean isBlockSymbol(String s) {
        if (this.blockCreators.containsKey(s)) {
            return true;
        }
        return false;
    }
    /**
     * generates block from given BlockCreator symbol and Point(x,y).
     * @param s **String key**
     * @param xpos **X axe position**
     * @param ypos **Y axe position**
     * @return **Block**
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }
    /**
     * returns integer which is the space width mapped to inputed key.
     * @param s **String**
     * @return **integer**
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s).intValue();
     }
}