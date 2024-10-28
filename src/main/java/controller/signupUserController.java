package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
		
	}
}
