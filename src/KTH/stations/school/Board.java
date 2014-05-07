package KTH.stations.school;

import KTH.JButtonImg;
import KTH.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.TimerTask;
import java.util.Timer;

/**
 * This class extends JPanel. This is where all the classes are painted.
 *
 * @author EMMZ
 * @version 1.0
 */
public class Board extends JPanel {
    private JLayeredPane rootPanel;

    private JButtonImg currentPhrase;
    private int currentPhraseIndex;
    private ArrayList<Image> currenConImgArray;

    private Character goodBoy;
    private Character footBallBoy;
    private Character jumpRopeBoy;
    private Character bullyBoy;
    private Character lonelyBoy;
    private Ball football;
    private Ball handball;

    private Map map;
    private Schoolyard schoolyard;

    private Timer timer;
    /**
     * Creates a new board.
     *
     * @param rootPanel the main panel in the game
     * @param map the main frame of the game
     * @param schoolyard a schoolyard class
     */
    public Board(JLayeredPane rootPanel, Map map, Schoolyard schoolyard){
        this.rootPanel = rootPanel;
        this.map = map;
        this.schoolyard = schoolyard;

        goodBoy = new Character(900, 300, "img/goodboy.png");
        jumpRopeBoy = new Character(500, 500, "img/jumpRopeBoy.png");
        bullyBoy = new Character(400, 500, "img/bullyBoy.png");
        lonelyBoy = new Character(800, 500, "img/lonelyBoy.png");
        footBallBoy = new Character(500, 80, "img/footballBoy.png");

        football = new Ball(310, 50, "img/football.png");
        handball = new Ball(70, 460, "img/handball.png");

        currentPhraseIndex = 0;
        currentPhrase = null;
        currenConImgArray = null;

        setFocusable(true);
        setDoubleBuffered(true);
        setOpaque(false);
        setSize(1000, 680);
        setVisible(true);


        // Moves the ball until program is closed.
        timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run() {
                football.moveParabolic(310, 550);
                handball.moveParabolic(70, 175);
                repaint();
            }
        }, 0, 40);

        addKeyListener(new keyEvent());
    }

    /**
     * Paints characters and moving the balls
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(footBallBoy.getImage(), footBallBoy.getX(), footBallBoy.getY(), this);
        g2d.drawImage(goodBoy.getImage(), goodBoy.getX(), goodBoy.getY(), this);
        g2d.drawImage(jumpRopeBoy.getImage(), jumpRopeBoy.getX(), jumpRopeBoy.getY(), this);
        g2d.drawImage(bullyBoy.getImage(), bullyBoy.getX(), bullyBoy.getY(), this);
        g2d.drawImage(lonelyBoy.getImage(), lonelyBoy.getX(), lonelyBoy.getY(), this);

        g2d.drawImage(football.getImage(), football.getX(), football.getY(), this);
        g2d.drawImage(handball.getImage(), handball.getX(), handball.getY(), this);

        jumpRopeBoy.jump();

        // Checks if Good Boy (main character) is close to another character. If so
        // something happens.
        boolean nearJumpRopeBoy = goodBoy.findDirection(jumpRopeBoy.getX(), jumpRopeBoy.getY());
        boolean nearBullyBoy =  goodBoy.findDirection(bullyBoy.getX(), bullyBoy.getY());
        
        if ((nearJumpRopeBoy || nearBullyBoy) && !jumpRopeBoy.isMetCharacter()){
            jumpRopeBoy.setMetCharacter();
            currenConImgArray = newConArray(11, "img/conJumpRopeBoy/", "png");
            startConversation(jumpRopeBoy);
        } else if (jumpRopeBoy.isSpeakedWithCharacter()){
            jumpRopeBoy.changeImg("img/jumpRopeBoyHappy.png");
            bullyBoy.moveVertical(700);
        }

        // Checks if Good Boy (main character) is close to another character. If so
        // something happens.
        if (goodBoy.findDirection(lonelyBoy.getX(), lonelyBoy.getY()) && !lonelyBoy.isMetCharacter()){
            lonelyBoy.setMetCharacter();
            currenConImgArray = newConArray(6, "img/conLonelyBoy/", "png");
            startConversation(lonelyBoy);
        } else if (lonelyBoy.isSpeakedWithCharacter()){
            lonelyBoy.x = goodBoy.x + 100;
            lonelyBoy.y = goodBoy.y;
        }

        // Checks if Good Boy (main character) is close to another character. If so
        // something happens.
        if (goodBoy.findDirection(footBallBoy.getX(), footBallBoy.getY()) && !footBallBoy.isMetCharacter()){
            footBallBoy.setMetCharacter();
            currenConImgArray = newConArray(7, "img/conFootball/", "png");
            startConversation(footBallBoy);
        } else if (footBallBoy.isSpeakedWithCharacter()){
            footBallBoy.moveVertical(-60);
            footBallBoy.changeImg("img/footballBoySmall.png");

        }

        // Checks if Good Boy (main character) has talked to everyone, if so the game
        // is completed and gan be ended.
        if (footBallBoy.isSpeakedWithCharacter() && lonelyBoy.isSpeakedWithCharacter() && jumpRopeBoy.isSpeakedWithCharacter())
        {
            timer.cancel();
            setEndFrame();
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();

    }

    /**
     * Sets the end frame when the game is complete.
     */
    private void setEndFrame() {

        final JDialog dialog = new JDialog(schoolyard, "You Win!", true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                schoolyard.exit(map);
            }
        });

        Image bg = Toolkit.getDefaultToolkit().getImage(Schoolyard.class.getResource("img/end.png"));
        dialog.setSize(new Dimension(350, 250));
        dialog.getContentPane().add(new JLabel(new ImageIcon(bg)), "Center");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * This method takes pictures and saves them to an array list and makes
     * them to a class type Image.
     *
     * @param numPic how many pictures
     * @param folderPath the path to the images
     * @param format which format of the pictures
     * @return the array with the pictures
     */
    private ArrayList<Image> newConArray(int numPic, String folderPath, String format){
        ArrayList<Image> conArray = new ArrayList<Image>();
        for (int i = 0; i < numPic; i++) {
            String imgPath = folderPath + i + "." + format;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imgPath));
            conArray.add(ii.getImage());
        }
        return conArray;
    }

    /**
     * This method is called when a conversation between characters should start.
     *
     * @param character the character who is having the conversation with the main character
     */
    private void startConversation(final Character character){
        if (currentPhrase != null)
            rootPanel.remove(currentPhrase);

        currentPhrase = new JButtonImg(new ImageIcon(currenConImgArray.get(0)));
        currentPhrase.setBounds(250, 300, 529, 60);
        currentPhrase.setOpaque(false);
        currentPhrase.setContentAreaFilled(false);
        currentPhrase.setBorderPainted(false);
        rootPanel.add(currentPhrase);

        currentPhrase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPhraseIndex++;
                if (currentPhraseIndex < currenConImgArray.size()){
                    currentPhrase.changeImg(currenConImgArray.get(currentPhraseIndex));
                } else {
                    rootPanel.remove(currentPhrase);
                    currentPhraseIndex = 0;
                    character.setSpeakedWithCharacter();
                }
            }
        });
    }

    /**
     * This class changes the position of the main character. It is used
     * to make him movable.
     */
    class keyEvent extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                goodBoy.x += -10;
            }

            if (key == KeyEvent.VK_RIGHT) {
                goodBoy.x += 10;
            }

            if (key == KeyEvent.VK_UP) {
                goodBoy.y += -10;
            }

            if (key == KeyEvent.VK_DOWN) {
                goodBoy.y += 10;
            }
        }
    }


}