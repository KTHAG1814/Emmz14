package KTH.stations.recycling;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class Sender extends Sprite {

    String sender = "img/sender.png";

    int dy;

    public Sender() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(sender));
        image = ii.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

        resetState();

    }

    public void move() {
        y += dy;
        if (x <= 2)
            x = 2;
        if (x >= Board.HEIGHT)
            x = 510;
        if (y <= Board.TOP)
            y = 0;
        if (y + heigth + 30>= Board.HEIGHT)
            y = Board.HEIGHT - heigth - 30 ;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
             dy = -2;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void resetState() {
        x = 550;
        y = 175;
    }
}