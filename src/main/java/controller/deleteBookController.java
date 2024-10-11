package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.dao.addbook;
import main.java.model.add;
import main.java.model.addNew;
import main.java.JDBC.JDBCSQL;

public class deleteBookController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, deleted;
    @FXML
    private TextField nameBook, chapBook, publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity,searchCode;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, Integer> columnID;
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
    	columnID.setCellValueFactory(new PropertyValueFactory<>("ID2"));
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        tableBook.setItems(bookList);
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
	@FXML
	private void handleDelete() {
	    try {
	        Connection con = JDBCSQL.getConnection();
	        // Truy vấn sách dựa trên bookCode
	        PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode = ?");
	        prsttm.setString(1, searchCode.getText());
	        ResultSet rs = prsttm.executeQuery();
	        if (rs.next()) {
	            // Nếu tìm thấy sách, tiến hành xóa
	            add Add = new add(searchCode.getText(), rs.getString(2), rs.getString(3), rs.getString(4), 
	                              rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
	            int res = addbook.setNewAdd().Delete(Add);

	            if (res > 0) {
	            	addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                            rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
	                // Xóa thành công, cập nhật danh sách hiển thị
	                bookList.add(AddNew);
	                addbook.setNewAdd().AlertComplete();
	                clearFields();
	            } else {
	                // Xóa không thành công
	                addbook.setNewAdd().AlertUnComplete();
	                //System.out.print("1");
	                clearFields();
	            }
	        } else {
	            // Nếu không tìm thấy sách, thông báo không có sách cần xóa
	            addbook.setNewAdd().AlertUnComplete();
	            //System.out.print("2");
	            clearFields();
	        }
	        
	        con.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

    private void clearFields() {
        searchCode.clear();
    }
}
