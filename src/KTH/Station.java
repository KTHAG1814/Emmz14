package KTH;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class is a super class that all the stations in the game shall inherit.
 * It makes sure that the right window is always displayed.
 *
 * @author EMMZ
 * @version 1.0
 */
public abstract class Station extends JFrame {

    private Map map;
    private String frameTitle;

    /**
     * This is a listener to the close button.
     * @param map the main window
     * @param frameTitle the title of the window
     */
    public Station(final Map map, String frameTitle) {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(map);
            }
        });

        this.map = map;
        this.frameTitle = frameTitle;

        map.setMapVisible(false);

    }

    /**
     * This method only calls the makeFrame method.
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        makeFrame(map, frameTitle);
    }

    // Guarantees that all the subclasses implements a makeFrame method.
    protected abstract void makeFrame(final Map map, String frameTitle);

    /**
     * It closes the newly opened window (station window)
     * and shows the main map window again.
     *
     * @param the map to be shown
     */
    public void exit(Map map){
        this.dispose();
        map.setMapVisible(true);
    }
}

