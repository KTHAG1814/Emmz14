package KTH.stations.school;

import KTH.Map;
import KTH.Station;
import javax.swing.*;
import java.awt.*;

/**
 * This class is a station and therefore inherits from the Station class.
 * It is the main window of the Station game.
 *
 * @author EMMZ
 * @version 1.0
 */
public class Schoolyard extends Station{
    private final int WIDTH = 1000;
    private final int HEIGHT = 680;
    private Timer timer;

    /**
     * Creates a new schoolyard.
     *
     * @param map the main frame of the game
     * @param frameTitle the title of this window
     * @throws InterruptedException
     */
    public Schoolyard(Map map, String frameTitle) throws InterruptedException {
        super(map, frameTitle);
    }

    /**
     * Creates a new frame for the schoolyard game.
     *
     * @param map the main frame of the game
     * @param frameTitle the title of this window
     */
    @Override
    protected void makeFrame(Map map, String frameTitle) {
        final JLayeredPane rootPanel = new JLayeredPane() {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(Schoolyard.class.getResource("img/school680x1000.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        rootPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(rootPanel);
        pack();

        Board board = new Board(rootPanel, map, this);
        rootPanel.add(board);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
