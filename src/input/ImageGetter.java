package input;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
/**
 * @author David Abramov.
 * ImageGetter class implementation.
 */
public class ImageGetter {
    /**
     * loads Image from inputed file path.
     * @param s **File path**
     * @return img **java.awt.Image**
     */
    public java.awt.Image load(String s) {
        java.awt.Image img = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
            img = ImageIO.read(is);
            return img;
        } catch (IOException e) {
            return null;
        }
    }
}