package main.java.controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.java.model.addNew;
import main.java.dao.addbook;
import main.java.model.add;
import main.java.model.alter;

public class recordFormController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, record,search;
    @FXML
    private TextField bookCode,title,chapter,author,quantity;
    @FXML
    private TextField borrowerID,username,phone,borrowDate,returnDate,status;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, String> columnID, columnName, columnPhone, columnBorrow,columnReturn,columnCode;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
    
    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
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
	
	/**xử lý sự kiện kiểm tra xe sách có tồn tại*/
	@FXML
	private void handleSearch() {
		add Add = new add(bookCode.getText());
		String bookcode = addbook.setNewAdd().search(Add);
		if(!bookcode.isEmpty()) {
			addbook.setNewAdd().AlertComplete();
			ArrayList<alter> arr = addbook.setNewAdd().selectByCondition1(bookcode);
			title.setText(arr.get(0).getnameBook());
			chapter.setText(arr.get(0).getchapBook());
			author.setText(arr.get(0).getnameAuthor());
			quantity.setText(arr.get(0).getQuantity());
		}else {
			addbook.setNewAdd().AlertUnComplete();
			clearFields();
		}
	}
	
	/**xử lý sự kiện ghi phiếu người mượn*/
	@FXML
	private void handleRecordForm() {
		
	}
	
	private void clearFields() {
	       bookCode.clear();
	       title.clear();
	       chapter.clear();
	       author.clear();
	       quantity.clear();
	   }
}
