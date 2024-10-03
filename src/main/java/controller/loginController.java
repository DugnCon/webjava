package main.java.controller;

import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

public class loginController extends baseSceneController {
    @FXML
    private VBox layAll;
    @FXML
    private HBox layLog, laySign;
    @FXML
    private TextField username, cardId;
    @FXML
    private PasswordField password;
    @FXML
    private Label notifyLabel;
    @FXML
    private ImageView imageIcon;
    @FXML
    private Button signup, login;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    @FXML
    private void handleButtonLogin() {
    	createScene(login,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
    }
    
    @FXML
	protected void handleButtonSignUp() {
        // Tải FXML cho cảnh mới
        createScene(signup, "/main/sources/signupView.fxml", "/main/sources/css/signup.css");
    }



    
}
