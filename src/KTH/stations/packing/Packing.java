package KTH.stations.packing;

import KTH.Map;
import KTH.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Packing extends Station {

    private final int WIDTH = 600;
    private final int HEIGHT = 680;

    public Packing(Map map, String frameTitle) throws InterruptedException {
        super(map, frameTitle);
    }

    @Override
    protected void makeFrame(Map map, String frameTitle) {
        final JLayeredPane rootPanel = new JLayeredPane() {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(Packing.class.getResource("img/packing-bg600x680.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        rootPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setTitle(frameTitle);
        add(rootPanel);
        pack();

        PackingBoard board = new PackingBoard();
        board.setOpaque(false);
        rootPanel.add(board);

        addKeyListener(new TAdapter());

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keycode = e.getKeyCode();

            switch (keycode) {

                case KeyEvent.VK_DOWN:
                    System.out.println("Down");
                    break;
                case KeyEvent.VK_UP:
                    System.out.println("Up");
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("Left");
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Right");
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println("Space");
                    break;
            }

        }
    }

}
