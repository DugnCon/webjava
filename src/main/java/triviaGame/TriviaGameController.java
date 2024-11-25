package main.java.triviaGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.Optional;

public class TriviaGameController {
    @FXML
    private ToggleGroup choice;

    @FXML
    private RadioButton firstAnswer;

    @FXML
    private RadioButton secondAnswer;

    @FXML
    private RadioButton thirdAnswer;

    @FXML
    private RadioButton fourAnswer;
    @FXML
    private Text question;

    @FXML
    private Label firstAns;

    @FXML
    private Label secondAns;

    @FXML
    private Label fourAns;

    @FXML
    private Label thirdAns;

    @FXML
    private Label score;

    private static TriviaGameLogic triviaGame;
    private int scoringResults;

    @FXML
    // khoi tao cac mang
    void initialize() {
        triviaGame = new TriviaGameLogic();
        triviaGame.readFromFile();
        question.setText(triviaGame.randomQuestion());
        triviaGame.currentAnswers();
        triviaGame.shuffleTheAnswers();
        setText();
    }

    @FXML
    //kiem tra xem dap n nao dc tron vâ doi chieu voi ket qua
    void answerSelected(ActionEvent event) {
        if (firstAnswer.isSelected()) {
            String ans = triviaGame.getShuffleAns()[0];
            answerAndScore(ans);
        }
        if (secondAnswer.isSelected()) {
            String ans = triviaGame.getShuffleAns()[1];
            answerAndScore(ans);

        } else if (thirdAnswer.isSelected()) {
            String ans = triviaGame.getShuffleAns()[2];
            answerAndScore(ans);

        } else if (fourAnswer.isSelected()) {
            String ans = triviaGame.getShuffleAns()[3];
            answerAndScore(ans);
        }
    }

    //neu tra loi dung + 10 d
    //sai thi - 5d
    private void answerAndScore(String ans) {
        if (triviaGame.correctAns(ans)) {
            scoringResults += 10;
            score.setText(String.valueOf(scoringResults));
            getNextQuestion();

        } else {
            scoringResults -= 5;
            score.setText(String.valueOf(scoringResults));
            getNextQuestion();
        }
    }
    //ket thuc game
    @FXML
    void endGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("The game is over. " + "Your score is:  " + scoringResults);
        alert.setContentText("Press OK to start a new game");
        Optional<ButtonType> option = alert.showAndWait();
        //bam ok thi tp tuc chs
        if (option.get() == ButtonType.OK)
            setNewGame();
        //bam cancel thi huy thoat ra ben ngoai
        else if (option.get() == null || option.get() == ButtonType.CANCEL)
            System.exit(0);
    }

    //truyen canh sang cau hoi tiep theo
    public void getNextQuestion() {
        triviaGame.remove();
        if (triviaGame.getQuestionsAndAnswers().size() == 0)
            endGame();//neu het câu hoi thi kthuc game

        //trao cau goi va trao cau tra loi
        triviaGame.setShuffleAns();
        question.setText(triviaGame.randomQuestion());
        triviaGame.currentAnswers();
        triviaGame.shuffleTheAnswers();
        setText();
        setSelected();
    }

    public void setText() {
        firstAns.setText(triviaGame.getShuffleAns()[0]);
        secondAns.setText(triviaGame.getShuffleAns()[1]);
        thirdAns.setText(triviaGame.getShuffleAns()[2]);
        fourAns.setText(triviaGame.getShuffleAns()[3]);
    }

    public void setSelected() {
        firstAnswer.setSelected(false);
        secondAnswer.setSelected(false);
        thirdAnswer.setSelected(false);
        fourAnswer.setSelected(false);
    }

    public void setNewGame() {
        triviaGame.reset();
        question.setText(triviaGame.randomQuestion());
        scoringResults = 0;
        score.setText("");
        setText();
    }
}
