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
public class quanlyController extends baseSceneController {
	@FXML
	private Button home,borrower,payer,user,employees,addBook;
	
	@FXML
	private void handleHome() {
		createScene(home,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}
	
	@FXML
	private void handleBorrower() {
		
	}
	
	@FXML
	private void handlePayer() {
		
	}
	
	@FXML
	private void handleUser() {
		
	}
	
	@FXML
	private void handleEmployees() {
		
	}
	
	@FXML
	private void handleAddBook() {
		
	}
	
}
