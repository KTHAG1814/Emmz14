package KTH.stations.quiz;

import KTH.Map;
import KTH.Station;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used to create and display a quiz. It also keeps track of the
 * number of correct answers.
 *
 * @author EMMZ
 * @version 2014-05-03 (v.01)
 *
 */
public class QuizFrame extends Station {
    private final int WIDTH = 800;
    private final int HEIGHT = 680;

    // How many different answer alternatives there will be to each
    // question.
    private final int numberOfAnswers = 4;

    // How many questions there will be in the quiz.
    private final int numberOfQuestions = 9;

    // Keeps track of how many correct answers.
    private int numberOfCorrectAnswers;

    // Keeps track of which question should be displayed.
    private int currentQuestionIndex;

    // The quiz.
    private Quiz quiz;

    // Buttons to the answers.
    private ArrayList<JButton> answersButtons;

    // The current question that will be displayed.
    private JLabel question;


    public QuizFrame(Map map, String frameTitle) throws InterruptedException {
        super(map, frameTitle);
    }

    /**
     * Creates a new quiz with the given number of questions and answer
     * alternatives. Be sure to create the questions and answers here in the
     * constructor that you want to have in the quiz.
     */
    public void newQuiz(){
        quiz = new Quiz(numberOfQuestions, numberOfAnswers);
        currentQuestionIndex = 0;
        answersButtons = new ArrayList<JButton>();

        // Create answer buttons
        for (int i = 0; i < numberOfAnswers; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Serif", Font.BOLD, 14));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            answersButtons.add(button);
        }

        // Create a quiz. Remember to set the field constants if
        // changing the amount of questions or answers.

        quiz.addQuestion("Hur mycket kläder slängs varje år i Sverige?", 0);
        quiz.addAnswer("11 kilo per person", 0, false);
        quiz.addAnswer("2 kilo per person", 0, false);
        quiz.addAnswer("8 kilo per person", 0, true);
        quiz.addAnswer("100 kilo per person", 0, false);

        quiz.addQuestion("Hur mycket vatten behövs det för att odla 1 kilo bomull?", 1);
        quiz.addAnswer("10 liter", 1, false);
        quiz.addAnswer("100 liter", 1, false);
        quiz.addAnswer("1000 liter", 1, false);
        quiz.addAnswer("10 000 liter", 1, true);

        quiz.addQuestion("Vad hjälper växthuseffekten planeten med?", 2);
        quiz.addAnswer("Den kyler ner planeten", 2, false);
        quiz.addAnswer("Den släpper ut solstrålar", 2, false);
        quiz.addAnswer("Den gör planeten till en plats man kan leva på", 2, true);
        quiz.addAnswer("Den överhettar planeten", 2, false);

        quiz.addQuestion("Hur påverkar transporters utsläpp på miljön?", 3);
        quiz.addAnswer("Positivt, den bidrar till en varmare planet vilket är bra, då smälter isarna", 3, false);
        quiz.addAnswer("Den påverkar växthuseffekten med att öka temperaturen och smälter polarisarna", 3, true);
        quiz.addAnswer("Positivt, transporter är bra för att hålla vägarna med jämn trafik", 3, false);
        quiz.addAnswer("Negativt, transporter gör så planeten kyls ner på grund av utsläppen", 3, false);

        quiz.addQuestion("Hur mycket vatten går åt per person varje dag i Sverige?", 4);
        quiz.addAnswer("200 liter", 4, true);
        quiz.addAnswer("2000 liter", 4, false);
        quiz.addAnswer("20 liter", 4, false);
        quiz.addAnswer("2 liter", 4, false);

        quiz.addQuestion("Hur mycket vatten består vi av?", 5);
        quiz.addAnswer(" 35-45 %", 5, false);
        quiz.addAnswer("45-55 %", 5, false);
        quiz.addAnswer("55-65 %", 5, true);
        quiz.addAnswer("65-75 %", 5, false);

        quiz.addQuestion("Hur stor yta av jorden består av vatten?", 6);
        quiz.addAnswer(" 81 %", 6, false);
        quiz.addAnswer("71 %", 6, true);
        quiz.addAnswer("51 %", 6, false);
        quiz.addAnswer("91 %", 6, false);

        quiz.addQuestion("Hur många elever blir mobbade i Sverige?", 7);
        quiz.addAnswer("100 000", 7, false);
        quiz.addAnswer("20 000", 7, false);
        quiz.addAnswer("50 000", 7, true);
        quiz.addAnswer("10 000", 7, false);

        quiz.addQuestion("I vilka årskurser är mobbning vanligast?", 8);
        quiz.addAnswer("årskurs 1-3", 8, false);
        quiz.addAnswer("årskurs 3-6", 8, true);
        quiz.addAnswer("årskurs 6-9", 8, false);
        quiz.addAnswer("Gymnasiet", 8, false);

    }

    /**
     * Creates a new frame for the quiz.
     */
    @Override
    protected void makeFrame(final Map map, String frameTitle) {
        newQuiz();

        final JPanel rootPanel = new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(QuizFrame.class.getResource("img/quiz680x800.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        rootPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(rootPanel, BorderLayout.CENTER);
        pack();

        // Create a label for the question.
        question = new JLabel();
        question.setFont(new Font("Serif", Font.PLAIN, 24));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setBorder(new EmptyBorder(50, 100, 0, 100));
        rootPanel.add(question, BorderLayout.NORTH);

        // Create a panel for the answers.
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(4, 1, 30, 30));
        rootPanel.add(panel, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(100, 100, 100, 100));
        updateFrame();

        for (int i = 0; i < numberOfAnswers; i++) {
            final JButton currentButton = answersButtons.get(i);
            panel.add(currentButton, BorderLayout.CENTER);
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    incrementPoints(currentButton.getText());
                    if (currentQuestionIndex < numberOfQuestions - 1) {
                        // If there still are questions left.
                        currentQuestionIndex++;
                        updateFrame();
                    } else {
                        setEndFrame(map);
                    }
                }
            });
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Updates the displayed frame.
     */
    private void updateFrame() {
        // Displays the next question
        question.setText(quiz.getQuestions()[currentQuestionIndex]);
        Iterator<String> it = quiz.getAnswers()[currentQuestionIndex].keySet()
                .iterator();
        // Displays the new answer alternatives
        for (int i = 0; it.hasNext(); i++) {
            answersButtons.get(i).setText((String) it.next());
        }

    }

    /**
     * Sets the end frame when the quiz is complete.
     */
    private void setEndFrame(final Map map) {

        final JPanel rootPanel = new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(QuizFrame.class.getResource("img/end680x800.jpg"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        setFrameVisible(false);
        JFrame jframe = new JFrame();
        jframe.setPreferredSize(new Dimension(800, 680));
        jframe.setLayout(new BorderLayout());
        jframe.add(rootPanel);
        JLabel result = new JLabel("Du hade " + numberOfCorrectAnswers
                + " rätt av " + numberOfQuestions + ".");
        result.setFont(new Font("Serif", Font.PLAIN, 50));
        result.setHorizontalAlignment(SwingConstants.CENTER);
        rootPanel.add(result, BorderLayout.CENTER);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                map.setMapVisible(true);
                e.getWindow().dispose();
                dispose();
            }
        });

    }

    /**
     * Is used to increment number of correct answers if the given answer was
     * correct.
     *
     * @param answer
     *            the given answer to a question
     */
    private void incrementPoints(String answer) {
        if (quiz.correctAnswer(currentQuestionIndex, answer)) {
            numberOfCorrectAnswers++;
        }
    }

    /**
     * Is used to set the frame visible or not.
     *
     * @param b
     *            true if want to set visible, false if not
     */
    private void setFrameVisible(boolean b) {
        this.setVisible(b);
    }
}
