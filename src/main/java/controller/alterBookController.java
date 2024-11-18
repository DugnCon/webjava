package main.java.controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.java.model.add;
import main.java.model.addNew;
import main.java.model.alter;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.userLog;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;
public class alterBookController extends baseSceneController {
	@FXML
	private Label label1,label;
	@FXML
	private ImageView image;
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
    
    private void addButtonZoomEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(e -> scaleIn.play()); 
        button.setOnMouseExited(e -> scaleOut.play()); 
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
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(borrower);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(Back);
        addButtonZoomEffect(user);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMEONALL1(search,searchCode,image);
        tran.COMELEFT2(modified,back);
        tran.COMELEFT(Back);
        tran.COMEUNDER2(tableBook);
        tran.COMEON(label1);
        tran.COMEON(label);
        tran.COMELEFT1(nameBook, nameAuthor, publisher, bookCode, chapBook, styleBook, releaseYear, quantity);
    }

    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }
    /**xử lý sự kiện mượn sách*/
    @FXML
    private void handleBorrower() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM borrower");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                borrowNew borrownew = new borrowNew(rs.getString(3), rs.getString(8), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                bookList.add(borrownew);
            }
            
            borrowBookController controller = (borrowBookController) createScene1(borrower, 
                "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
            controller.setBookList(bookList);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }
    
    /**xử lý sự kiện người trả*/
    @FXML
    private void handlePayer() {
    	createScene(payer, "/main/sources/returnBookView.fxml", "/main/sources/css/returnBook.css");
    }
    
    /**xử lý sự kiện người dùng*/
    @FXML
    private void handleUser() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<userLog> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                userLog userlog = new userLog(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5));
                bookList.add(userlog);
            }
            
            manageUserController controller = (manageUserController) createScene1(user, 
                "/main/sources/manageUserView.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    /**xử lý sự kiện nhân viên*/
    @FXML
    private void handleEmployees() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM request");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<requireNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                requireNew require = new requireNew(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4));
                bookList.add(require);
            }
            
            manageRequestController controller = (manageRequestController) createScene1(employees, 
                "/main/sources/manageRequest.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
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
			alertController.setNew().AlertComplete("Đã tìm thấy sách cần sửa");
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
            alertController.setNew().AlertComplete("Sửa sách thành công");
            clearFields();
        } else {
            alertController.setNew().AlertUnComplete("Sửa không thành công");
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
