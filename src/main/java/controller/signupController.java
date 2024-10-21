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
import main.java.dao.UserAccountInterface;
import main.java.dao.signUpAccount;
import main.java.dao.userAccount;
import main.java.model.author;
import main.java.model.user;

public class signupController extends baseSceneController {

    @FXML
    private VBox layAll;
    @FXML
    private HBox layLog, laySign;
    @FXML
    private TextField username, cardId,fullname;
    @FXML
    private PasswordField password, repeatPassword;
    @FXML
    private Label notifyLabel, user, pass, repeatPass;
    @FXML
    private ImageView imageIcon;
    @FXML
    private Button signup, login, back;
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
        String fullName = fullname.getText();
		author Author = new author(userName, passWord, repeatPassWord, fullName);
		int res = signUpAccount.getInstance().insert(Author);
		if(res > 0) {
			user User = new user(userName, passWord, fullName);
			int rs = userAccount.setNew().insert(User);
			if(rs > 0) {
				signUpAccount.getInstance().AlertComplete();
				createScene(signup, "/main/sources/loginView.fxml", "/main/sources/css/login.css");	
			}
		}else {
			signUpAccount.getInstance().AlertUnComplete();
		}
    }
	
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/loginView.fxml","/main/sources/css/login.css");
	}
}
