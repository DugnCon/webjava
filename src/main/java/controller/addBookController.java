package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addBookController extends baseSceneController {
	@FXML
	private Button home,borrower,payer,user,employees,completed;
	@FXML
	private TextField nameBook,chapBook,publisher,releaseYear,nameAuthor,styleBook,bookCode,quantity;
	@FXML
	private void handleHome() {
		createScene(home,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}
	
	
	/**xử lý sự kiện mượn sách*/
	@FXML
	private void handleBorrower() {
		
	}
	
	/**xử lý sự kiện người trả*/
	@FXML
	private void handlePayer() {
		
	}
	
	/**xử lý sự kiện người dùng*/
	@FXML
	private void handleUser() {
		
	}
	
	/**xử lý sự kiện nhân viên*/
	@FXML
	private void handleEmployees() {
		
	}
	
	/**xử lý sự kiện thêm sách*/
	@FXML
	private void handleComplete() {
		
	}
}
