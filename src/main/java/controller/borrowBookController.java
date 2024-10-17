package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.java.model.addNew;

public class borrowBookController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, search,addNewBorrower;
    @FXML
    private TextField searchBorrowerID;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear,columnDayBorrow;
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
	
	/**xử lý sự kiện tì người mượn*/
	@FXML
	private void handleSearchBorrower() {
		
	}
	
	/**xử lý sự kiện thêm người mượn*/
	@FXML
	private void handleAdd() {
		createScene(addNewBorrower,"/main/sources/recordFormView.fxml","/main/sources/css/recordForm.css");
	}
}
