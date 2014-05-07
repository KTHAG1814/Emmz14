package KTH.stations.recycling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public class Board extends JPanel{

    public static final int WIDTH = 550;
    public static final int HEIGHT = 400;
    public static final int TOP = 0;

    Timer timer;
    String message = "Du förlorade!";
    Item item;
    Sender sender;
    Receiver receivers[];

    boolean ingame = true;


    public Board() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                sender.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sender.keyPressed(e);
            }
        });

        receivers = new Receiver[12];
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                item.move();
                sender.move();
                checkCollision();
                repaint();
            }
        }, 1000, 10);

        setSize(Board.WIDTH, Board.HEIGHT);
        setFocusable(true);
        setDoubleBuffered(true);
        setOpaque(false);
        setVisible(true);

    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {

        item = new Item();
        sender = new Sender();


        int k = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                receivers[k] = new Receiver(j * 40 + 30, i * 50 + 50);
                k++;
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D)g;

        if (ingame) {
            g2d.drawImage(item.getImage(), item.getX(), item.getY(),
                    item.getWidth(), item.getHeight(), this);
            g2d.drawImage(sender.getImage(), sender.getX(), sender.getY(),
                    sender.getWidth(), sender.getHeight(), this);

            for (int i = 0; i < 12; i++) {
                if (!receivers[i].isDestroyed())
                    g2d.drawImage(receivers[i].getImage(), receivers[i].getX(),
                            receivers[i].getY(), receivers[i].getWidth(),
                            receivers[i].getHeight(), this);
            }
        } else {

            Font font = new Font("Verdana", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(font);

            g2d.setColor(Color.BLACK);
            g2d.setFont(font);
            g2d.drawString(message,
                    (WIDTH - metr.stringWidth(message)) / 2,
                    WIDTH / 2);
        }


        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void stopGame() {
        ingame = false;
        timer.cancel();
    }


    public void checkCollision() {

        if (item.getRect().getMaxX() > WIDTH) {
            stopGame();
        }


        for (int i = 0, j = 0; i < 12; i++) {
            if (receivers[i].isDestroyed()) {
                j++;
            }
            if (j == 12) {
                message = "Barnen i Afrika tackar dig för din hjälp!";
                stopGame();
            }
        }

        if ((item.getRect()).intersects(sender.getRect())) {
            item.switchItem(item.randPic());

            int senderLPos = (int)sender.getRect().getMinY();
            int itemLPos = (int)item.getRect().getMinY();
            int itemHeight = (int)item.getRect().getHeight();

            int first = senderLPos + 8;
            int second = senderLPos + 16;
            int third = senderLPos + 24;
            int fourth = senderLPos + 32;

            if(itemHeight >= HEIGHT){
                item.setXDir(1);
                item.setYDir(-1);
            }

            if (itemLPos < first) {
                item.setXDir(-1);
                item.setYDir(-1);
            }

            if (itemLPos >= first && itemLPos < second) {
                item.setXDir(-1);
                item.setYDir(-1 * item.getYDir());
            }

            if (itemLPos >= second && itemLPos < third) {
                item.setXDir(-1);
                item.setYDir(-1);
            }

            if (itemLPos >= third && itemLPos < fourth) {
                item.setXDir(-1);
                item.setYDir(-1 * item.getYDir());
            }

            if (itemLPos > fourth) {
                item.setXDir(-1);
                item.setYDir(1);
            }


        }



        for (int i = 0; i < 12; i++) {
            if ((item.getRect()).intersects(receivers[i].getRect())) {

                int itemLeft = (int)item.getRect().getMinX();
                int itemHeight = (int)item.getRect().getHeight();
                int itemWidth = (int)item.getRect().getWidth();
                int itemTop = (int)item.getRect().getMinY();

                Point pointRight =
                        new Point(itemLeft + itemWidth + 1, itemTop);
                Point pointLeft = new Point(itemLeft - 1, itemTop);
                Point pointTop = new Point(itemLeft, itemTop - 1);
                Point pointBottom =
                        new Point(itemLeft, itemTop + itemHeight + 1);

                if (!receivers[i].isDestroyed()) {
                    item.switchItem("img/itemback.png");
                    if (receivers[i].getRect().contains(pointRight)) {
                        item.setXDir(-1);
                    }

                    else if (receivers[i].getRect().contains(pointLeft)) {
                        item.setXDir(1);
                    }

                    if (receivers[i].getRect().contains(pointTop)) {
                        item.setYDir(1);
                    }

                    else if (receivers[i].getRect().contains(pointBottom)) {
                        item.setYDir(-1);
                    }

                    receivers[i].setDestroyed(true);
                }
            }
        }
    }
}