package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.java.dao.signUpAccount;
import main.java.model.author;


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
    	 // Lấy giá trị từ các trường
        String userName = username.getText();
        String passWord = password.getText();
        String repeatPassWord = repeatPassword.getText();
		author Author = new author(userName, passWord, repeatPassWord);
		signUpAccount.getInstance().insert(Author);
        createScene(signup, "/main/sources/loginView.fxml", "/main/sources/css/login.css");
    }
}
