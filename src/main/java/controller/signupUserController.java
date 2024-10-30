package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.dao.signUpAccount;
import main.java.dao.userAccount;
import main.java.model.user;

public class signupUserController extends baseSceneController {
	@FXML
	private Button signup,back;
	@FXML
	private TextField username,fullname;
	@FXML
	private PasswordField password,repeatpass;
	@FXML
	public void handleBack() {
		createScene(back,"/main/sources/loginUserView.fxml","/main/sources/css/login_signUser.css");
	}
	@FXML
	public void handleSignup() {
		user User =  new user(username.getText(),password.getText(), repeatpass.getText(), fullname.getText());
		int res = userAccount.setNew().insertSign(User);
		int res1 = userAccount.setNew().insertLog(User);
		if(res > 0 && res1 > 0) {
			signUpAccount.getInstance().AlertComplete();
			createScene(signup,"/main/sources/loginUserView.fxml","/main/sources/css/login_signUser.css");
		}else {
			signUpAccount.getInstance().AlertUnComplete();
		}
	}
}
