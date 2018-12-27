package game;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author David Abramov.
 * Implementation of HighScoresTable class.
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int maxSize;
    private List<ScoreInfo> scores; //ScoreInfo Comparator.
    private final transient Comparator<ScoreInfo> comp = new Comparator<ScoreInfo>() {
        public int compare(ScoreInfo s1, ScoreInfo s2) {
            if (s1.getScore() > s2.getScore()) {
                return 1;
            }
            if (s1.getScore() < s2.getScore()) {
                return -1;
            }
            return 0;
        }
    };
    /**
     * HighScoresTable object constructor.
     * @param s **max size**
     */
    public HighScoresTable(int s) {
        this.maxSize = s;
        this.scores = new ArrayList<ScoreInfo>(s);
    }
    /**
     * adds high score to table.
     * @param score **ScoreInfo**
     */
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank <= this.maxSize) {
            this.scores.add(rank - 1, score);
            if (this.scores.size() > this.maxSize) {
                this.scores.remove(this.scores.size() - 1);
            }
        }
    }
    /**
     * returns table max size.
     * @return **integer**
     */
    public int size() {
        return this.maxSize;
    }
    /**
     * returns Scores List.
     * @return **Score List**
     */
    public List<ScoreInfo> getHighScores() {
        if (this.scores != null) {
            Collections.sort(this.scores, Collections.reverseOrder(comp));
        }
        return this.scores;
    }
    /**
     * return rank of inputed score.
     * @param score **integer**
     * @return **integer**
     */
    public int getRank(int score) { //copies scores list, adds inputed score, sorts the copied list.
        ArrayList<ScoreInfo> scoreCpy = new ArrayList<ScoreInfo>(this.maxSize + 1);
        scoreCpy.addAll(this.scores);
        ScoreInfo s = new ScoreInfo("", score);
        scoreCpy.add(s);    //ScoreInfo Comparator.
        Collections.sort(scoreCpy, Collections.reverseOrder(comp));    //finding the rank of inputed score.
        int size = scoreCpy.size();
        int rank = 0;
        for (int i = 0; i < size; i++) {
            if (scoreCpy.get(i).getScore() == score) {
                rank = i + 1;
                break;
            }
        }
        return rank;
    }
    /**
     * clears table.
     */
    public void clear() {
        this.scores.clear();
    }
    /**
     * increases max size.
     * @param size **integer**
     */
    public void increaseSize(int size) {
        if (size > this.maxSize) {
            this.maxSize = size;
            ArrayList<ScoreInfo> l = new ArrayList<ScoreInfo>(size);
            l.addAll(this.scores);
            this.scores = l;
        }
    }
    /**
     * loads table from inputed file.
     * @param filename **File**
     * @throws IOException **I/O Exception**
     */
    public void load(File filename) throws IOException {
        FileInputStream is = null;
        ObjectInputStream objStream = null;
        try {
            is = new FileInputStream(filename);
            objStream = new ObjectInputStream(is);
            HighScoresTable t = (HighScoresTable) objStream.readObject();
            this.scores = t.getHighScores();
            this.maxSize = t.size();
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
    /**
     * saves table to inputed file.
     * @param filename **File**
     * @throws IOException **I/O Exception**
     */
    public void save(File filename) throws IOException {
        FileOutputStream os = null;
        ObjectOutputStream objStream = null;
        try {
            os = new FileOutputStream(filename);
            objStream = new ObjectOutputStream(os);
            objStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objStream != null) {
                try {
                    objStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * returns new table generated from inputed File.
     * @param filename **File**
     * @return **HighScoresTable
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            HighScoresTable t = new HighScoresTable(1);
            t.load(filename);
            return t;
        } catch (IOException e) {
            return new HighScoresTable(1);
        }
    }
}