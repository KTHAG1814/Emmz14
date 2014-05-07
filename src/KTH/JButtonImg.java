package KTH;

import javax.swing.*;
import java.awt.*;

/**
 * This class inherits from the class JButton. But it has some
 * extra features.
 *
 * @author EMMZ
 * @version 1.0
 */
public class JButtonImg extends JButton {

    /**
     * Creates a button by calling the super class's constructor with a
     * given icon (background).
     * @param icon the icon (background)
     */
    public JButtonImg(Icon icon) {
        super(icon);
    }

    /**
     * This method is used to change the image of the icon.
     *
     * @param path the path to the image
     */
    public void changeImg(String path) {
        Icon icon = new ImageIcon(Map.class.getResource(path));
        // initialize
        init(null, icon);
    }

    /**
     * This method is used to change the image of the icon.
     * @param path the path to the image
     */
    public void changeImg(Image path) {
        Icon icon = new ImageIcon(path);
        // initialize
        init(null, icon);
    }
}
