package game;
import java.io.Serializable;
/**
 * @author David Abramov.
 * Implementation of ScoreInfo class.
 */
public class ScoreInfo implements Serializable, Comparable<ScoreInfo> {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int score;
    /**
     * constructor for ScoreInfo object.
     * @param n **Name**
     * @param s **Score**
     */
    public ScoreInfo(String n, int s) {
        this.name = n;
        this.score = s;
    }
    /**
     * returns Name.
     * @return **String**
     */
    public String getName() {
        return this.name;
    }
    /**
     * returns Score.
     * @return **integer**
     */
    public int getScore() {
        return this.score;
    }
    /**
     * compares current ScoreInfo to inputed one.
     * @param inf **ScoreInfo**
     * @return **integer**
     */
    public int compareTo(ScoreInfo inf) {
        if (this.getScore() > inf.getScore()) {
            return 1;
        }
        if (this.getScore() < inf.getScore()) {
            return -1;
        }
        return 0;
    }
}