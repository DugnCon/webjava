package main.java.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.model.add;
import main.java.model.addNew;
import main.java.model.alter;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;
public class alterBookController extends baseSceneController {
	@FXML
    private Button home, borrower, payer, user, employees, modified,search,back,Back;
    @FXML
    private TextField nameBook, chapBook, publisher, releaseYear, 
                      nameAuthor, styleBook, bookCode, quantity,
                      searchCode;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
    
    public void setBookList(ObservableList<addNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }
    
    @FXML
    private void handleBack() {
    	createScene(Back,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
    }
    
    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

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
	
	@FXML
	private void handleLoad() {
		try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<addNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
                bookList.add(AddNew);
            }
            
            alterBookController controller = (alterBookController) createScene1(back, 
            		"/main/sources/alterBookView.fxml", "/main/sources/css/alterBook.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
	}
	
	@FXML
	private void handleSearchBook() {
		add Add = new add(searchCode.getText(), nameBook.getText(), chapBook.getText(),
		        nameAuthor.getText(), styleBook.getText(), publisher.getText(),
		        releaseYear.getText(), quantity.getText());
		String bookcode = addbook.setNewAdd().search(Add);
		if(!bookcode.isEmpty()) {
			addbook.setNewAdd().AlertComplete();
			ArrayList<alter> arr = addbook.setNewAdd().selectByCondition(bookcode);
			nameBook.setText(arr.get(0).getnameBook());
			chapBook.setText(arr.get(0).getchapBook());
			nameAuthor.setText(arr.get(0).getnameAuthor());
			styleBook.setText(arr.get(0).getstyleBook());
			publisher.setText(arr.get(0).getPublisher());
			releaseYear.setText(arr.get(0).getreleaseYear());
			bookCode.setText(arr.get(0).getbookCode());
			quantity.setText(arr.get(0).getQuantity());
		}else {
			System.out.print("Không thể thấy mã cần tìm");
		}
		searchCode.clear();
	}
	
	/**xử lý sự kiện sửa đổi*/
	@FXML
	private void handleModified() {
		add Add = new add(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                releaseYear.getText(), quantity.getText());
		addNew AddNew = new addNew(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                releaseYear.getText(), quantity.getText());

        int res = addbook.setNewAdd().update(Add);
        if (res > 0) {
        	incomingBookList.add(AddNew);
            addbook.setNewAdd().AlertComplete();
            clearFields();
        } else {
            addbook.setNewAdd().AlertUnComplete();
        }
    }
	
   private void clearFields() {
       bookCode.clear();
       nameBook.clear();
       chapBook.clear();
       nameAuthor.clear();
       styleBook.clear();
       publisher.clear();
       releaseYear.clear();
       quantity.clear();
   }
}
