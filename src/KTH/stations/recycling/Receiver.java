package KTH.stations.recycling;

import javax.swing.ImageIcon;


public class Receiver extends Sprite {

    String receiver = "img/receiver.png";

    boolean destroyed;


    public Receiver(int x, int y) {
        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(receiver));
        image = ii.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

        destroyed = false;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }

}