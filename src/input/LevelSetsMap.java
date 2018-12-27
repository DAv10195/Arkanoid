package input;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import levels.LevelInformation;
/**
 * @author David Abramov.
 * LevelSetsMap class implementation.
 */
public class LevelSetsMap {
    private final HashMap<String, ArrayList<LevelInformation>> sets;
    private final HashMap<String, String> toStrings;
    private final String path;
    /**
     * constructor for LevelSetsMap object.
     * @param s **File path**
     */
    public LevelSetsMap(String s) {
        this.sets = new HashMap<String, ArrayList<LevelInformation>>();
        this.toStrings = new HashMap<String, String>();
        this.path = s;
    }
    /**
     * builds relevant maps from member File path.
     * @return **true if successful, false otherwise**
     */
    public boolean build() {
        LinesGenerator lg = new LinesGenerator();
        LevelSpecificationReader lr = new LevelSpecificationReader();
        InputStreamReader r1 = null, r2 = null;
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.path);
        if (is == null) {
            return false;
        }
        r1 = new InputStreamReader(is);
        ArrayList<String> odd = lg.genOddLines(r1);
        is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.path);
        r1 = new InputStreamReader(is);
        ArrayList<String> even = lg.genEvenLines(r1);
        if (odd == null || even == null || odd.size() != even.size()) {
            return false;
        }
        int size = odd.size();
        for (int i = 0; i < size; i++) {
            String str1 = odd.get(i);
            String[] s = str1.split(":");
            this.toStrings.put(s[0], s[1]);
            String str2 = even.get(i);
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(str2);
            if (is == null) {
                return false;
            }
            r2 = new InputStreamReader(is);
            ArrayList<LevelInformation> l = (ArrayList<LevelInformation>) lr.fromReader(r2);
            this.sets.put(s[0], l);
            if (l == null) {
                this.sets.clear();
                this.toStrings.clear();
                return false;
            }
        }
        try {
            if (r1 != null) {
                r1.close();
            }
            if (r2 != null) {
                r2.close();
            }
            if (is != null) {
                is.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
    /**
     * getter for sets map, null if empty.
     * @return **sets map**
     */
    public ArrayList<ArrayList<LevelInformation>> getSets() {
        if (this.sets.isEmpty()) {
            return null;
        }
        ArrayList<String> keys = this.getKeys();
        if (keys == null) {
            return null;
        }
        ArrayList<ArrayList<LevelInformation>> toRet = new ArrayList<ArrayList<LevelInformation>>();
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            toRet.add(this.sets.get(keys.get(i)));
        }
        return toRet;
    }
    /**
     * getter for toStrings map, null if empty.
     * @return **toStrings map**
     */
    public ArrayList<String> getToStrings() {
        if (this.toStrings.isEmpty()) {
            return null;
        }
        ArrayList<String> keys = this.getKeys();
        if (keys == null) {
            return null;
        }
        ArrayList<String> toRet = new ArrayList<String>();
        int size = keys.size();
        for (int i = 0; i < size; i++) {
            toRet.add(this.toStrings.get(keys.get(i)));
        }
        return toRet;
    }
    /**
     * returns List of Keys.
     * @return **String List**
     */
    public ArrayList<String> getKeys() {
        if (this.toStrings.isEmpty()) {
            return null;
        }
        Set<String> s = this.toStrings.keySet();
        ArrayList<String> toRet = new ArrayList<String>();
        toRet.addAll(s);
        return toRet;
    }
}