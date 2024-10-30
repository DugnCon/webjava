package main.java.controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.model.addNew;
import main.java.dao.addbook;
import main.java.dao.borrowbook;
import main.java.dao.userAccount;
import main.java.dao.userLoginAccount;
import main.java.model.add;
import main.java.model.alter;
import main.java.model.borrow;
import main.java.model.borrowNew;
import main.java.model.user;
import main.java.model.userLog;

public class recordFormController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, record,search,searchID;
    @FXML
    private TextField bookCode,title,chapter,author,quantity;
    @FXML
    private TextField borrowerID,username,phone,borrowDate,returnDate,status,searchAccount,userID;
    @FXML
    private TableView<borrowNew> tableBook; 
    @FXML
    private TableColumn<borrowNew, String> columnID, columnName, columnPhone, columnBorrow,columnReturn,columnCode;
    @FXML
    private ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<borrowNew> incomingBookList = FXCollections.observableArrayList();
    
    public void setBookList(ObservableList<borrowNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }
    
    @FXML
    private void initialize() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("borrowerID"));
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phonenum"));
        columnBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        columnReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        tableBook.setItems(incomingBookList);
    }
    
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
	
	/**xử lý sự kiện tìm kiếm ID người dùng*/
	@FXML
	private void handleSearchID() {
		String check = userID.getText();
		if(!check.isEmpty()) {
			addbook.setNewAdd().AlertComplete();
			ArrayList<userLog> arr = userLoginAccount.setNew().selectByCondition(check);
			username.setText(arr.get(0).getFullname());
			arr.clear();
		}else {
			addbook.setNewAdd().AlertComplete();
			userID.clear();
		}
	}
	
	/**xử lý sự kiện ghi phiếu người mượn*/
	@FXML
	private void handleRecordForm() {
         String res = bookCode.getText();
         if(!res.isEmpty()) {
        	 
        	 /**Cần sửa đoạn này*/
        	 borrow Borrow = new borrow(borrowerID.getText(), userID.getText(),bookCode.getText()
                     ,borrowDate.getText(), returnDate.getText(), username.getText() ,"Đang mượn", phone.getText());
        	 
        	 int rs = borrowbook.setNew().insert(Borrow);
        	 /********************/
        	 
        	 add Add = new add(quantity.getText(), res);
        	 int check = addbook.setNewAdd().update2(Add);
        	 
        	 if(rs > 0 && check > 0) {
        		 borrowNew BorrowNew = new borrowNew(borrowerID.getText(),bookCode.getText(), username.getText(), 
                         borrowDate.getText(), returnDate.getText(), phone.getText());
            	 borrowbook.setNew().AlertComplete();
            	 incomingBookList.add(BorrowNew);
            	 clearFields();
            	 clearFields1();
        	 }else {
        		 borrowbook.setNew().AlertUnComplete();
        	 }
         }else {
        	 borrowbook.setNew().AlertUnComplete();
         }
	}
	
	private void clearFields() {
	       bookCode.clear();
	       title.clear();
	       chapter.clear();
	       author.clear();
	       quantity.clear();
	}
	private void clearFields1() {
		borrowerID.clear();
		username.clear();
		phone.clear();
		borrowDate.clear();
		returnDate.clear();
	}
}
