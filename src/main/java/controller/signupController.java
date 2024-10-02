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

public class signupController extends baseSceneController {

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
        // Tải FXML cho cảnh mới
		createScene(signup,"/main/sources/loginView.fxml","/main/sources/css/login.css");
    }

}