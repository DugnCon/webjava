package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginUserController extends baseSceneController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login,signup;
	@FXML
	private void handleLogin() {
		
	}
	@FXML
	private void handleSignup() {
		createScene(signup,"/main/sources/signupUserView.fxml","/main/sources/css/login_signUser.css");
	}
}
