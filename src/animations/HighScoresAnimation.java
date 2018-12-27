package animations;
import java.util.List;
import biuoop.DrawSurface;
import game.HighScoresTable;
import game.ScoreInfo;
import geometry.Point;
/**
 * @author David Abramov.
 * HighScoresAnimation class implementation.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private final HighScoresTable h;
    private final int dif = 25;
    private final Point toDraw1 = new Point(10, 30);
    private final Point toDraw2 = new Point(150, 100);
    private final Point toDraw3 = new Point(550, 100);
    private final Point toDraw4 = new Point(150, 130);
    private final Point toDraw5 = new Point(550, 130);
    private final Point toDraw6 = new Point(230, 580);
    private final int fontSize1 = 30;
    private final int fontSize2 = 20;
    private final int winX = 800;
    private final int winY = 600;
    /**
     * EndScreen object constructor.
     * @param scores **HighScoresTable**
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.stop = false;
        this.h = scores;
     }
    /**
     * puts one frame on surface.
     * @param d **surface**
     * @param dt **change in frames per small time unit**
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.DARK_GRAY);
        d.fillRectangle(0, 0, winX, winY);
        d.setColor(java.awt.Color.CYAN);
        d.drawText((int) toDraw1.getX(), (int) toDraw1.getY(), "High Scores", fontSize1);
        d.setColor(java.awt.Color.WHITE);
        d.drawText((int) toDraw2.getX(), (int) toDraw2.getY(), "Player Name", fontSize2);
        d.drawText((int) toDraw3.getX(), (int) toDraw3.getY(), "Score", fontSize2);
        double lineY = toDraw3.getY() + 5;
        d.drawLine((int) toDraw2.getX(), (int) lineY, (int) toDraw3.getX() + 50, (int) lineY);
        List<ScoreInfo> l = this.h.getHighScores();
        int size = l.size();
        for (int i = 0; i < size; i++) {
            ScoreInfo inf = l.get(i);
            d.drawText((int) toDraw4.getX(), (int) (toDraw4.getY() + (dif * i)), inf.getName(), fontSize2);
            String score = String.valueOf(inf.getScore());
            d.drawText((int) toDraw5.getX(), (int) (toDraw5.getY() + (dif * i)), score, fontSize2);
        }
        d.setColor(java.awt.Color.CYAN);
        d.drawText((int) toDraw6.getX(), (int) toDraw6.getY(), "press space to continue", fontSize1);
     }
    /**
     * stops Animation.
     * @return **boolean**
     */
    public boolean shouldStop() {
        return this.stop;
    }
}