package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.dao.signUpAccount;
import main.java.dao.userLoginAccount;
import main.java.model.userLog;

public class loginUserController extends baseSceneController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login,signup;
	@FXML
	private void handleLogin() {
		userLog userlog = new userLog(username.getText(), password.getText());
		int res = userLoginAccount.setNew().insert(userlog);
		if(res > 0) {
			signUpAccount.getInstance().AlertComplete();
			createScene(login, "/main/sources/interfaceUser.fxml","/main/sources/css/interfaceUser.css");
		}else {
			signUpAccount.getInstance().AlertUnComplete();
		}
	}
	@FXML
	private void handleSignup() {
		createScene(signup,"/main/sources/signupUserView.fxml","/main/sources/css/login_signUser.css");
	}
}
