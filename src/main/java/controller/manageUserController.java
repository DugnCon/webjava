package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.dao.lockAccount;
import main.java.dao.userLoginAccount;
import main.java.model.borrowNew;
import main.java.model.lockaccount;
import main.java.model.requireNew;
import main.java.model.userLog;

public class manageUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label;
	@FXML
	private ImageView image1,image2,image;
	@FXML
    private Button home, borrower, payer, user, employees, record,search,searchID,back;
	@FXML
	private Button logaccount,restoreaccount;
    @FXML
    private TextField bookCode,title,chapter,author,quantity,searchUserID;
    @FXML
    private TextField borrowerID,username,phone,borrowDate,returnDate,status,searchAccount,userID;
    @FXML
    private TableView<userLog> tableBook; 
    @FXML
    private TableColumn<userLog, String> columnID, columnUser, columnPassword, columnCreateAcc,columnFullname;
    @FXML
    private ObservableList<userLog> bookList = FXCollections.observableArrayList();
    
    private ObservableList<userLog> incomingBookList = FXCollections.observableArrayList();
    
    public void setBookList(ObservableList<userLog> diffbook) {
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
    private void initialize() {
    	columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnCreateAcc.setCellValueFactory(new PropertyValueFactory<>("createAc"));
        columnFullname.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
        tableBook.setItems(incomingBookList);
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(user);
        addButtonZoomEffect(borrower);

        tableBook.setItems(bookList);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMEONALL1(search,searchUserID,image);
        tran.COMEUNDER2(tableBook);
        tran.COMERIGHT(label);
        
        ArrayList<Node> button = new ArrayList<Node>();
        button.add(logaccount);
        button.add(restoreaccount);
        tran.COMEONARRAY(button);
    }
    
    @FXML
    private void handleBack() {
    	createScene(back,"/main/sources/borrowBookView.fxml","/main/sources/css/borrowBook.css");
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
		createScene(user,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
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
	private void handleSearchUser() {
	    String username = searchUserID.getText();
	    if (!username.isEmpty()) {
	        userLog log = userLoginAccount.setNew().selectByUser(username);
	        if (log != null) {
	            incomingBookList.clear(); 
	            incomingBookList.add(log);
	            tableBook.setItems(incomingBookList); 
	            alertController.setNew().AlertComplete("Đã tìm thấy thành công");
	        } else {
	            alertController.setNew().AlertUnComplete("Không thể tìm thấy người dùng");
	        }
	    }
	}
	
	@FXML
	private void handleLog() {
		createScene(logaccount,"/main/sources/lockAccountView.fxml","/main/sources/css/lockAccount.css");
	}
	
	@FXML
	private void handleRestore() {
		createScene(restoreaccount,"/main/sources/restoreAccountView.fxml","/main/sources/css/lockAccount.css");
	}
}
