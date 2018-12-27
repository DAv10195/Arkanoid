package input;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
/**
 * @author David Abramov.
 * implementation of LinesGenerator class.
 */
public class LinesGenerator {
    /**
     * generates a List with all Lines from inputed file.
     * @param r **java.io.Reader**
     * @return **String List**
     */
    public ArrayList<String> get(Reader r) {
        BufferedReader br = null;
        ArrayList<String> toRet = new ArrayList<String>();
        try {
            br = new BufferedReader(r);
            String line = br.readLine();
            while (line != null) {  //checking if line read is relevant.
                if (!line.isEmpty()) {
                    char c = line.charAt(0);
                    if (c != '#' && c != '\t' && c != ' ' && c != '\n' && c != '\r') {
                        toRet.add(line);
                    }
                }
                line = br.readLine();
            }
            return toRet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * generates lines List from start and end indexes, from inputed lines array.
     * @param l **String List**
     * @param start **start index**
     * @param end **end index**
     * @return toRet **String List**
     */
    public ArrayList<String> getLevelLines(ArrayList<String> l, int start, int end) {
        ArrayList<String> toRet = new ArrayList<String>();
        if (!l.get(start).equals("START_LEVEL") || !l.get(end).equals("END_LEVEL")) {
            return null;
        }
        for (int i = start + 1; i < end; i++) {
            if (!l.get(i).isEmpty()) {
                char c = l.get(i).charAt(0);
                if (c != '#' && c != '\t' && c != ' ' && c != '\n' && c != '\r') {
                    toRet.add(l.get(i));
                }
            }
        }
        return toRet;
    }
    /**
     * generates lines List from start and end indexes, from inputed lines array.
     * @param l **String List**
     * @param start **start index**
     * @param end **end index**
     * @return toRet **String List**
     */
    public ArrayList<String> genBlockLines(ArrayList<String> l, int start, int end) {
        ArrayList<String> toRet = new ArrayList<String>();
        if (!l.get(start).equals("START_BLOCKS") || !l.get(end).equals("END_BLOCKS")) {
            return null;
        }
        for (int i = start + 1; i < end; i++) {
            char c = l.get(i).charAt(0);
            if (c != '#' && c != '\t' && c != ' ' && c != '\n' && c != '\r') {
                toRet.add(l.get(i));
            }
        }
        return toRet;
    }
    /**
     * generates a List with all odd numbered Lines from inputed file.
     * @param r **java.io.Reader**
     * @return toRet **String List**
     */
    public ArrayList<String> genOddLines(Reader r) {
        BufferedReader br = null;
        ArrayList<String> toRet = new ArrayList<String>();
        try {
            br = new BufferedReader(r);
            int i = 1;
            String line = br.readLine();
            while (line != null) {  //checking if line read is relevant.
                if (!line.isEmpty() && (i % 2 == 1)) {
                    char c = line.charAt(0);
                    if (c != '#' && c != '\t' && c != ' ' && c != '\n' && c != '\r') {
                        toRet.add(line);
                    }
                }
                i++;
                line = br.readLine();
            }
            return toRet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * generates a List with all even numbered Lines from inputed file.
     * @param r **java.io.Reader**
     * @return toRet **String List**
     */
    public ArrayList<String> genEvenLines(Reader r) {
        BufferedReader br = null;
        ArrayList<String> toRet = new ArrayList<String>();
        try {
            br = new BufferedReader(r);
            int i = 1;
            String line = br.readLine();
            while (line != null) {  //checking if line read is relevant.
                if (!line.isEmpty() && (i % 2 == 0)) {
                    char c = line.charAt(0);
                    if (c != '#' && c != '\t' && c != ' ' && c != '\n' && c != '\r') {
                        toRet.add(line);
                    }
                }
                i++;
                line = br.readLine();
            }
            return toRet;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}