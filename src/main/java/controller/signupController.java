package main.java.controller;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class signupController {

    @FXML
    private VBox layAll;
    @FXML
    private HBox layLog, laySign;
    @FXML
    private TextField username, cardId;
    @FXML
    private PasswordField password, repeatPassword;
    @FXML
    private Label notifyLabel, user, pass, repeatPass;
    @FXML
    private ImageView imageIcon;
    @FXML
    private Button signup, login;
    @FXML
    private Stage stage;

    @FXML
    private void handleButtonLogin() {
        // Xử lý logic khi nhấn nút Login
    }

    @FXML
    private void handleButtonSignUp() {
        try {
            // Tải FXML cho cảnh mới
            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
            Scene newScene = new Scene(newRoot, 1200, 780);
            newScene.getStylesheets().add(getClass().getResource("/main/sources/css/login.css").toExternalForm());

            // Lấy Stage hiện tại
            Stage stage = (Stage) signup.getScene().getWindow();

            // Thêm TranslateTransition
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0), stage.getScene().getRoot());
            transition.setFromX(0);
            transition.setToX(-stage.getWidth()); // Di chuyển ra ngoài bên trái
            transition.setOnFinished(e -> {
                stage.setScene(newScene); // Đặt cảnh mới sau khi hoàn thành chuyển động
                TranslateTransition fadeIn = new TranslateTransition(Duration.seconds(0), newRoot);
                fadeIn.setFromX(stage.getWidth()); // Bắt đầu từ bên phải
                fadeIn.setToX(0); // Kết thúc tại vị trí ban đầu
                fadeIn.play(); // Chạy chuyển động fade in
            });
            
            // Bắt đầu chuyển động
            transition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}