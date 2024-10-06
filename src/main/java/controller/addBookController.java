package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addBookController extends baseSceneController {
	@FXML
	private Button home,borrower,payer,user,employees,completed;
	@FXML
	private TextField nameBook,chapBook,publisher,releaseYear,nameAuthor,styleBook;
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
	private void handleComplete() {
		
	}
}
