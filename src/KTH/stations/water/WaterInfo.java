package KTH.stations.water;

import KTH.Map;
import KTH.Station;

import java.awt.*;
import java.net.URL;


public class WaterInfo extends Station {

    public WaterInfo(Map map, String frameTitle) throws InterruptedException {
        super(map, frameTitle);
    }

    @Override
    protected void makeFrame(final Map map, String frameTitle) {
        /*setSize(640, 390);
        // ============== Default setting ==============
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);*/

        try {
            Desktop.getDesktop().browse(new URL("https://www.youtube.com/embed/HObp-lkcl4A?autoplay=1").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.setMapVisible(true);
    }

}
