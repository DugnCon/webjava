package main.java.triviaGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class TriviaGameLogic {
    private static final int numOfRowsInNode = 5;
    private static String firstAns;
    private static String secondAns;
    private static String thirdAns;
    private static String fourAns;
    private static String correctAns;
    private static String[] shuffleAns;
    private static LinkedList<Question> questionsAndAnswers;
    private static Question currNode;

    public TriviaGameLogic() {
        questionsAndAnswers = new LinkedList<Question>();
        shuffleAns = new String[4];
        currNode = null;
        firstAns = "";
        secondAns = "";
        thirdAns = "";
        fourAns = "";
        correctAns = "";
    }

    public static void setShuffleAns() {
        shuffleAns = new String[4];
    }

    public void reset() {
        questionsAndAnswers = new LinkedList<Question>();
        setShuffleAns();
        currNode = null;
        firstAns = "";
        secondAns = "";
        thirdAns = "";
        fourAns = "";
        correctAns = "";
        readFromFile();
        currentAnswers();
        shuffleTheAnswers();
    }

    public static String[] getShuffleAns() {
        return shuffleAns;
    }

    public static LinkedList<Question> getQuestionsAndAnswers() {
        return questionsAndAnswers;
    }

    public static LinkedList readFromFile() {
        int cnt = 0;
        boolean flag = false;
        InputStream inputStream = TriviaGameLogic.class.getResourceAsStream("/trivia.txt");
        Scanner input = new Scanner(inputStream);
        while (input.hasNextLine()) {
            String st = input.nextLine();

            if (cnt % numOfRowsInNode == 0) {
                currNode = new Question();
                currNode.setQuestion(st);
                flag = true;
            } else if (cnt % numOfRowsInNode == 1) {
                currNode.setFirstAns(st);
            } else if (cnt % numOfRowsInNode == 2) {
                currNode.setSecondAns(st);
            } else if (cnt % numOfRowsInNode == 3) {
                currNode.setThirdAns(st);
            } else if (cnt % numOfRowsInNode == 4) {
                currNode.setFourAns(st);
                flag = false;
            }
            cnt++;
            if ((cnt % 5 == 0) && (flag == false)) {
                questionsAndAnswers.add(currNode);
            }
        }
        input.close();
        return questionsAndAnswers;
    }

    public static String randomQuestion() {
        Collections.shuffle(questionsAndAnswers);
        String question = questionsAndAnswers.get(0).getQuestion();
        correctAns = questionsAndAnswers.get(0).getFirstAns();
        return question;
    }

    public static boolean correctAns(String ans) {
        return (correctAns.equals(ans));
    }

    public void currentAnswers() {
        this.firstAns = questionsAndAnswers.get(0).getFirstAns();
        this.secondAns = questionsAndAnswers.get(0).getSecondAns();
        this.thirdAns = questionsAndAnswers.get(0).getThirdAns();
        this.fourAns = questionsAndAnswers.get(0).getFourAns();
    }

    public static void shuffleTheAnswers() {
        shuffleAns[0] = firstAns;
        shuffleAns[1] = secondAns;
        shuffleAns[2] = thirdAns;
        shuffleAns[3] = fourAns;

        Random rand = new Random();
        for (int i = shuffleAns.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String s = shuffleAns[j];
            shuffleAns[j] = shuffleAns[i];
            shuffleAns[i] = s;
        }
    }

    public void remove() {
        questionsAndAnswers.remove(0);
    }

}
