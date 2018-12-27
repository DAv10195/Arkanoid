package input;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import geometry.Point;
import sprites.Block;
/**
 * @author David Abramov.
 * implementation of BlocksDefinitionReader.
 */
public class BlocksDefinitionReader {
    /**
     * creates Block Factory from inputed File stream. if invalid input or fails, returns null.
     * @param reader **java.io.Reader**
     * @return toRet **Blocks Factory**
     */
    public static BlocksFromSymbolsFactory fromReader(Reader reader) {
        BlocksFromSymbolsFactory toRet = new BlocksFromSymbolsFactory();
        ColorsParser cp = new ColorsParser();
        ImageGetter ig = new ImageGetter();
        LinesGenerator lg = new LinesGenerator();
        ArrayList<String> l = lg.get(reader);
        if (l == null || l.isEmpty()) { return null; }
        Integer width = null, height = null, hitPts = null;
        java.awt.Color c = null, stroke = null;
        java.awt.Image m = null;
        String factoryKey = null;
        HashMap<String, String> strDefs = new HashMap<String, String>();
        HashMap<String, Integer> intDefs = new HashMap<String, Integer>();
        HashMap<Integer, java.awt.Color> colors = new HashMap<Integer, java.awt.Color>();
        HashMap<Integer, java.awt.Image> images = new HashMap<Integer, java.awt.Image>();
        int size = l.size();
        for (int i = 0; i < size; i++) { String str = l.get(i);  //dealing with defaults.
            if (str.startsWith("default")) { String[] arr = str.split(" ");
                for (int j = arr.length - 1; j >= 1; j--) { String[] defVal = arr[j].split(":");
                    try {
                        int num = Integer.parseInt(defVal[1]);
                        intDefs.put(defVal[0], num);
                    } catch (NumberFormatException e) { strDefs.put(defVal[0], defVal[1]); }
                }   //case we have a block definition.
            } else if (str.startsWith("bdef")) { String[] arr = str.split(" ");
                for (int j = arr.length - 1; j >= 1; j--) {
                    String[] s = arr[j].split(":");
                    if (s[0].equals("symbol")) {
                        if (s[1].length() != 1) { return null; }
                        factoryKey = s[1];
                    } else if (s[0].equals("width")) {
                        try { width = Integer.parseInt(s[1]);
                        } catch (NumberFormatException e) { return null; }
                    } else if (s[0].equals("height")) {
                        try { height = Integer.parseInt(s[1]);
                        } catch (NumberFormatException e) { return null; }
                    } else if (s[0].equals("hit_points")) {
                        try { hitPts = Integer.parseInt(s[1]);
                        } catch (NumberFormatException e) { return null; }
                    } else if (s[0].equals("stroke")) {
                        if (s[1].startsWith("color(")) {
                            String color = s[1].substring(6);
                            int len = color.length();
                            stroke = cp.colorFromStr(color.substring(0, len - 1));
                            if (stroke == null) { return null; }
                        } else { return null; }
                    } else if (s[0].equals("fill")) {
                        if (s[1].startsWith("color(")) {
                            String color = s[1].substring(6);
                            int len = color.length();
                            c = cp.colorFromStr(color.substring(0, len - 1));
                            if (c == null) { return null; }   //-1 means the standard fill option.
                            colors.put(-1, c);
                        } else if (s[1].startsWith("image(")) {
                            String image = s[1].substring(6);
                            int len = image.length();
                            m = ig.load(image.substring(0, len - 1));
                            if (m == null) { return null; }
                            images.put(-1, m);
                        } else { return null; }
                    } else if (s[0].startsWith("fill-")) {
                        String[] st = s[0].split("-");
                        try {
                            int key = Integer.parseInt(st[1]);
                            if (s[1].startsWith("color(")) {
                                String color = s[1].substring(6);
                                int len = color.length();
                                c = cp.colorFromStr(color.substring(0, len - 1));
                                if (c == null) { return null; }
                                colors.put(key, c);
                            } else if (s[1].startsWith("image(")) {
                                String image = s[1].substring(6);
                                int len = image.length();
                                m = ig.load(image.substring(0, len - 1));
                                if (m == null) { return null; }
                                images.put(key, m);
                            } else { return null; }
                        } catch (NumberFormatException e) { return null; }
                    } else { return null; }
                }   //checks for defaults.
                if (width == null) {
                    if (intDefs.containsKey("width")) { width = intDefs.get("width");
                    } else { return null; }
                }
                if (height == null) {
                    if (intDefs.containsKey("height")) { height = intDefs.get("height");
                    } else { return null; }
                }
                if (hitPts == null) {
                    if (intDefs.containsKey("hit_points")) { hitPts = intDefs.get("hit_points");
                    } else { return null; }
                }
                if (stroke == null) {
                    if (strDefs.containsKey("stroke")) {
                        String color = strDefs.get("stroke");
                        int len = color.length();
                        stroke = cp.colorFromStr(color.substring(6, len - 1));
                        if (stroke == null) { return null; }
                    }
                }
                if (!images.containsKey(-1) && !colors.containsKey(-1)) {
                    if (!images.containsKey(1) && !colors.containsKey(1)) {
                        if (strDefs.containsKey("fill")) {
                            String value = strDefs.get("fill");
                            java.awt.Color clr = cp.colorFromStr(value);
                            java.awt.Image img = ig.load(value);
                            if (clr != null) { colors.put(-1, clr);
                            } else if (img != null) { images.put(-1, img);
                            } else { return null; }
                        } else { return null; }
                    }
                }
                if (factoryKey == null) { return null; }
                final Integer fWidth = width;
                final Integer fHeight = height;
                final Integer fHitPts = hitPts;
                final java.awt.Color fStroke = stroke;
                final HashMap<Integer, java.awt.Color> fColors = colors;
                final HashMap<Integer, java.awt.Image> fImages = images;
                BlockCreator bc = genBlkCreator(fWidth, fHeight, fStroke, fHitPts, fColors, fImages);
                toRet.addBlkCreator(factoryKey, bc);    //reset all fields.
                width = null;
                height = null;
                stroke = null;
                hitPts = null;
                c = null;
                m = null;
                factoryKey = null;
                colors = new HashMap<Integer, java.awt.Color>();
                images = new HashMap<Integer, java.awt.Image>();    //spacer definition.
            } else if (str.startsWith("sdef")) { String[] arr = str.split(" ");
                for (int j = 1; j < arr.length; j++) { String[] s = arr[j].split(":");
                    if (s[0].equals("symbol")) {
                        if (s[1].length() != 1) { return null; }
                        factoryKey = s[1];
                    } else if (s[0].equals("width")) {
                        try {
                            int num = Integer.parseInt(s[1]);
                            toRet.addSpc(factoryKey, num);
                        } catch (NumberFormatException e) { return null; }
                    }
                }
                factoryKey = null;
            }
        }
        return toRet;
    }
    /**
     * generates BlockCreator.
     * @param w **width**
     * @param h **height**
     * @param s **stroke color**
     * @param hp **hit points**
     * @param clr **colors map**
     * @param img **images map**
     * @return bc **Block Creator**
     */
    private static BlockCreator genBlkCreator(Integer w, Integer h, java.awt.Color s, Integer hp,
            HashMap<Integer, java.awt.Color> clr, HashMap<Integer, java.awt.Image> img) {
        BlockCreator bc = new BlockCreator() {
            public Block create(int xpos, int ypos) {
                Point p = new Point(xpos, ypos);
                int curWidth = w.intValue();
                int curHeight = h.intValue();
                java.awt.Color curStroke = s;
                int curHitPts = hp.intValue();
                HashMap<Integer, java.awt.Color> curColors = clr;
                HashMap<Integer, java.awt.Image> curImages = img; //ball radius is 10.
                Block b = new Block(p, curWidth, curHeight, curStroke, 10, curHitPts);
                b.setMapClr(curColors);
                b.setMapImg(curImages);
                return b;
            }
        };
        return bc;
    }
}