package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import main.java.model.add;
import main.java.dao.addbook;

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
		add Add = new add(bookCode.getText(),nameBook.getText(),chapBook.getText(),
				          nameAuthor.getText(),styleBook.getText(),publisher.getText(),
				          releaseYear.getText(), quantity.getText());
		int res  = addbook.setNewAdd().insert(Add);
		if (res > 0) {
			addbook.setNewAdd().AlertComplete();
			createScene(completed,"/main/java/controller/addBookController.java" , "/main/sources/css/addBook.css");
		}else {
			addbook.setNewAdd().AlertUnComplete();
			createScene(completed,"/main/java/controller/addBookController.java" , "/main/sources/css/addBook.css");
		}
	}
}
