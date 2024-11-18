package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import main.java.model.add;
import main.java.model.addNew;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.userLog;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;

public class addBookController extends baseSceneController {
	@FXML
	private HBox hbox,HBOX;
	@FXML
	private Label label,label1,notify;
    @FXML
    private Button home, borrower, payer, user, employees, completed,back;
    @FXML
    private TextField nameBook, chapBook, publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
    
    public static addBookController setNew() {
    	return new addBookController();
    }
    
    public void setBookList(ObservableList<addNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }
    
    @FXML
    private void handleBack() {
    	createScene(back,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
    }
    
    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        tableBook.setItems(incomingBookList);

        applyHoverEffect(home);
        applyHoverEffect(borrower);
        applyHoverEffect(payer);
        applyHoverEffect(user);
        applyHoverEffect(employees);
        applyHoverEffect(completed);
        applyHoverEffect(back);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMELEFT2(label,completed);
        tran.COMELEFT(back);
        tran.COMEUNDER2(tableBook);
        tran.COMEON(label1);
        tran.COMELEFT1(nameBook, nameAuthor, publisher, bookCode, chapBook, styleBook, releaseYear, quantity);
        
    }

    private void applyHoverEffect(Button button) {
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
    private void handleComplete() {
        if (isInputValid()) {
            add Add = new add(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                    nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                    releaseYear.getText(), quantity.getText());
            addNew AddNew = new addNew(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                    nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                    releaseYear.getText(), quantity.getText());

            int res = addbook.setNewAdd().insert1(Add);
            if (res > 0) {
            	incomingBookList.add(AddNew);
                alertController.setNew().AlertComplete("Thêm sách thành công");
                clearFields();
            } else {
                alertController.setNew().AlertUnComplete("Thêm sách không thành công");
            }
        }
    }

    private boolean isInputValid() {
        return !bookCode.getText().isEmpty() && !nameBook.getText().isEmpty();
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