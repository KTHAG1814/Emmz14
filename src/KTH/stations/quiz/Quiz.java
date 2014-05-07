package KTH.stations.quiz;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to set up a quiz. To connect questions with answers.
 * 
 * @author EMMZ
 * @version 2014-05-03 (v.01)
 * 
 */
public class Quiz {

	// Keeps track of how many questions there are in the quiz
	private int numberOfQuestions;

	// An array that holds the questions
	private String[] questions;

	// Different answers to the questions, if it is the
	// correct answer the value of boolean is true otherwise
	// it is false.
	private Map<String, Boolean>[] answers;

	/**
	 * Creates new lists to the field variables.
	 * 
	 * @param numberOfQuestions
	 *            how many questions there will be in the quiz
	 * @param numberOfAnswers
	 *            how many answers there will be to each question
	 */
	public Quiz(int numberOfQuestions, int numberOfAnswers) {
		this.numberOfQuestions = numberOfQuestions;
		questions = new String[numberOfQuestions];

		@SuppressWarnings("unchecked")
		Map<String, Boolean>[] a = new HashMap[100];
		answers = a;
	}

	/**
	 * Adds an answer to a specific question. Keep in mind that if you want to
	 * add an answer to question number n on you have to put it in index n-1.
	 * 
	 * @param questionNumberIndex
	 *            to which answer the question belongs.
	 * @param answer
	 *            the answer
	 * @param correct
	 *            true if it is an correct answer, otherwise false.
	 */
	public void addAnswer(String answer, int questionNumberIndex,
			boolean correct) {
		if (answers[questionNumberIndex] == null) {
			answers[questionNumberIndex] = new HashMap<String, Boolean>();
		}

		answers[questionNumberIndex].put(answer, correct);
	}

	/**
	 * Adds a question to the quiz. Keep in mind that you have to keep track of
	 * which number the question is and add it to index n-1.
	 * 
	 * @param question
	 *            the question to be added
	 * @param questionNumberIndex
	 *            the index number of the question
	 */
	public void addQuestion(String question, int questionNumberIndex) {
		questions[questionNumberIndex] = question;
	}

	/**
	 * A get method for the questions.
	 * 
	 * @return the questions
	 */
	public String[] getQuestions() {
		return questions;
	}

	/**
	 * A get method for the answers.
	 * 
	 * @return the answers
	 */
	public Map<String, Boolean>[] getAnswers() {
		return answers;
	}

	/**
	 * Returns how many questions there are in the quiz.
	 * 
	 * @return number of questions.
	 */
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	/**
	 * Checks if an answer is correct or not.
	 * 
	 * @param questionIndex
	 *            index to which question the answer belongs
	 * @param answer
	 *            the given answer
	 * @return true if it was the right answer, false if not
	 */
	public boolean correctAnswer(int questionIndex, String answer) {
		return answers[questionIndex].get(answer);
	}

}
