package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.java.JDBC.JDBCSQL;
import main.java.dao.borrowbook;
import main.java.model.addNew;
import main.java.model.borrowNew;

public class borrowBookController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, search,addNewBorrower;
    @FXML
    private TextField searchBorrowerID;
    @FXML
    private TableView<borrowNew> tableBook; 
    @FXML
    private TableColumn<borrowNew, String> columnCode, columnTitle, columnAuthor, columnYear,columnDayBorrow;
    @FXML
    private ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<borrowNew> incomingBookList = FXCollections.observableArrayList();
    
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
		String code = search.getText();
		if(!code.isEmpty()) {
			/*B1: Tìm kiếm mã trong sql vaò hàm arraylist<borrow>*/
			/*B2: Đưa dữ liệu vao nơi giống addNew*/
			/*B3: Thiết lập các column lấy giá trị từ cái giống addNew*/
			/*B4: Kết thúc*/
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
