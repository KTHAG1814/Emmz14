package KTH.stations.recycling;

import javax.swing.*;
import java.util.Random;


public class Item extends Sprite {

    private int xdir;
    private int ydir;
    private String[] items;
    private Random r;

    //protected String ball = balls[r.nextInt(balls.length)];

    public Item() {

        xdir = -1;
        ydir = 0;

        r = new Random();
        items = new String[]{"img/item.png","img/item2.png","img/item3.png","img/item4.png","img/item5.png"};
        ImageIcon ii = new ImageIcon(this.getClass().getResource(randPic()));

        image = ii.getImage();

        width = image.getWidth(null);
        heigth = image.getHeight(null);

       resetState();
    }

    public String randPic(){
        return items[r.nextInt(items.length)];
    }

    public void move()
    {
        x += xdir;
        y += ydir;

        if (y == 350) {
            setYDir(-1);

        }

        if (x == 0) {
            setXDir(1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    public void resetState()
    {
        x = 490;
        y = 150;
    }

    public void switchItem(String back){
        ImageIcon iii = new ImageIcon(this.getClass().getResource(back));
        image = iii.getImage();
    }

    public void setXDir(int x)
    {
        xdir = x;
    }

    public void setYDir(int y)
    {
        ydir = y;
    }

    public int getYDir()
    {
        return ydir;
    }
}