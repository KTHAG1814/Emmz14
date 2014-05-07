package KTH;

import KTH.stations.packing.Packing;
import KTH.stations.quiz.QuizFrame;
import KTH.stations.recycling.Recycling;
import KTH.stations.school.Schoolyard;
import KTH.stations.water.WaterInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * This is the main frame of the game. It is used to navigate to all
 * other station and games. It keeps track of all the other stations and
 * classes.
 *
 * @author EMMZ
 * @version 1.0
 */
public class Map extends JFrame{

    private JButtonImg currentSlide;
    private ArrayList<Image> currenInfoImgArray;
    private int currentSlideIndex;

    /**
     * Calls the method makeFrame.
     */
    public Map() {
        makeFrame();
    }

    /**
     * Runs the whole game/program.
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Map game = new Map();
            }
        });

    }

    /**
     * Creates a new frame and buttons with listeners so that it is possible
     * to navigate to other stations. Every station has a default red button
     * that is changed to green if it is visited.
     */
    private void makeFrame() {
        // ============== Background image ==============
        final JLayeredPane rootPanel = new JLayeredPane() {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(Map.class.getResource("img/map950x650.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        add(rootPanel);
        // ============== Default setting ===============
        setTitle("Emmz!");
        setPreferredSize( new Dimension(950, 650));
        pack();

        // ============== Recycling Station 1 ==============
        final JButtonImg recyclingButton = createStationButton("img/redButton.png", 680, 350);
        recyclingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recyclingButton.changeImg("img/greenButton.png");

                try {
                    Recycling recycling = new Recycling(Map.this, "Återanvändning");
                    startInfoFrame(2, "img/recyclingInfo/", "png", recycling);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rootPanel.add(recyclingButton);

        // ============== Packing Station 2 ==============
        final JButtonImg packingButton = createStationButton("img/redButton.png", 590, 190);
        packingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packingButton.changeImg("img/greenButton.png");
                try {
                    Packing packing = new Packing(Map.this, "Packing");
                    startInfoFrame(2, "img/packingInfo/", "png", packing);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rootPanel.add(packingButton);

        // ============== Fishing Station 3 ==============
        final JButtonImg fishingButton = createStationButton("img/redButton.png", 300, 250);
        fishingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fishingButton.changeImg("img/greenButton.png");
                setMapVisible(false);
                try {
                    WaterInfo waterInfo = new WaterInfo(Map.this, "Vårt vatten");
                    startInfoFrame(2, "img/waterInfo/", "png", waterInfo);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rootPanel.add(fishingButton);

        // ============== School Station 4 ==============
        final JButtonImg schoolButton = createStationButton("img/redButton.png", 350, 550);
        schoolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schoolButton.changeImg("img/greenButton.png");
                try {
                    Schoolyard schoolyard = new Schoolyard(Map.this, "Schoolyard");
                    startInfoFrame(2, "img/schoolInfo/", "png", schoolyard);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rootPanel.add(schoolButton);

        // ============== Quiz Station 5 ==============
        final JButtonImg quizButton = createStationButton("img/redButton.png", 500, 380);
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizButton.changeImg("img/greenButton.png");
                try {
                    QuizFrame quiz = new QuizFrame(Map.this, "Quiz!");
                    startInfoFrame(1, "img/quizInfo/", "png", quiz);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rootPanel.add(quizButton);

        // ============== Default setting ==============
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Sets the end frame when the game is complete.
     */
    private void startInfoFrame(int numPic, String folderPath, String format, Station station) {

        JDialog dialog = new JDialog(this, " ", true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                setMapVisible(true);
            }
        });

        currenInfoImgArray = newSlidesArray(numPic, folderPath, format);
        startSilde(station, dialog);

        dialog.setSize(new Dimension(550, 400));
        dialog.getContentPane().add(currentSlide);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Updates the information slide with new text. This method also
     * starts the game for the station after the last information slide,
     * if the station has a game.
     *
     * @param station the game station that has been clicked
     * @param dialog a window for the information
     */
    private void startSilde(final Station station, final JDialog dialog){
        currentSlide = new JButtonImg(new ImageIcon(currenInfoImgArray.get(currentSlideIndex)));
        currentSlide.setBounds(0, 0, 550, 400);
        currentSlide.setOpaque(false);
        currentSlide.setContentAreaFilled(false);
        currentSlide.setBorderPainted(false);

        currentSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSlideIndex++;
                if (currentSlideIndex < currenInfoImgArray.size()){
                    currentSlide.changeImg(currenInfoImgArray.get(currentSlideIndex));
                } else {
                    try {
                        currentSlideIndex = 0;
                        dialog.dispose();
                        // This is where the game is started, if the station has a game.
                        station.start();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
    }

    /**
     * This method takes pictures and saves them to an array. It converts them to the
     * class type Image.
     *
     * @param numPic how many pictures
     * @param folderPath the path to the folder where the pictures can be found
     * @param format format of the pictures in the map
     *
     * @return an array with the pictures in Image type
     */
    private ArrayList<Image> newSlidesArray(int numPic, String folderPath, String format){
        ArrayList<Image> slideArray = new ArrayList<Image>();
        for (int i = 0; i < numPic; i++) {
            String imgPath = folderPath + i + "." + format;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(imgPath));
            slideArray.add(ii.getImage());
        }
        return slideArray;
    }

    /**
     * Sets the main window visible.
     *
     * @param b true if the window should be displayed, false if not
     */
    public void setMapVisible(Boolean b) {
        setVisible(b);
    }

    /**
     * Creates a new button for a station with a background and position.
     *
     * @param stationStatusPath
     * @param x the x position of the button
     * @param y the y position of the button
     *
     * @return the button that was just created
     */
    private JButtonImg createStationButton(String stationStatusPath, int x, int y) {

        ImageIcon icon = new ImageIcon(Map.class.getResource(stationStatusPath));
        JButtonImg button = new JButtonImg(icon);

        button.setBounds(x, y, 40, 40);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }

}

