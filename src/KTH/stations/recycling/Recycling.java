package KTH.stations.recycling;

import KTH.Map;
import KTH.Station;

import javax.swing.*;
import java.awt.*;

public class Recycling extends Station {

    public Recycling(Map map, String frameTitle) throws InterruptedException {
        super(map, frameTitle);
    }

    @Override
    protected void makeFrame(Map map, String frameTitle) {
        setTitle(frameTitle);
        JLayeredPane rootPanel = new JLayeredPane() {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(Recycling.class.getResource("img/bg.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        rootPanel.setPreferredSize(new Dimension(Board.WIDTH, Board.HEIGHT));
        add(rootPanel);
        pack();

        Board board = new Board();
        rootPanel.add(board);

        setIgnoreRepaint(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
