package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.JDBC.JDBCSQL;
import main.java.dao.borrowbook;
import main.java.model.addNew;
import main.java.model.borrow;
import main.java.model.borrowNew;

public class borrowBookController extends baseSceneController {
	@FXML
    private Button home, managebook, payer, user, employees, search,addNewBorrower;
    @FXML
    private TextField searchBorrowerID;
    @FXML
    private TableView<borrowNew> tableBook; 
    @FXML
    private TableColumn<borrowNew, String> columnCode, columnUser, columnBorrowDate, columnReturnDate, columnStatus;
    @FXML
    private ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<borrowNew> incomingBookList = FXCollections.observableArrayList();
    
    /*public void setBookList(ObservableList<borrowNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }*/
    
    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        columnReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableBook.setItems(bookList);
    }
    
    @FXML
    private void handleHome() {
    	 createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }
    /**xử lý sự kiện mượn sách*/
	@FXML
	private void handleManage() {
		createScene(managebook,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
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
	    String code = searchBorrowerID.getText();
	    if (!code.isEmpty()) {
	        ArrayList<borrow> arr = borrowbook.setNew().selectByCondition(code);
	        if (!arr.isEmpty()) {
	            borrowNew borrownew = new borrowNew(arr.get(0).getBookCode(),
	                              arr.get(0).getUserName(), arr.get(0).getBorrowDate(),
	                              arr.get(0).getReturnDate(), arr.get(0).getStatus());
	            this.bookList.add(borrownew);
	            tableBook.refresh();
	        }
	        arr.clear();
	    }
	}
	
	/**xử lý sự kiện thêm người mượn*/
	@FXML
	private void handleAdd() {
		try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM borrower");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                borrowNew borrownew = new borrowNew(rs.getString(1), rs.getString(8), rs.getString(3),
                        rs.getString(7), rs.getString(4), rs.getString(5));
                bookList.add(borrownew);
            }
            
            recordFormController controller = (recordFormController) createScene1(addNewBorrower, 
                "/main/sources/recordFormView.fxml", "/main/sources/css/recordForm.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
	}
}
