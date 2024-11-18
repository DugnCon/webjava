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
import main.java.dao.addbook;
import main.java.dao.borrowbook;
import main.java.model.addNew;
import main.java.model.alter;
import main.java.model.borrow;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.userLog;
import main.java.model.Return;
import main.java.model.ReturnNew;
import main.java.model.add;
import main.java.dao.returnbook;
import main.java.dao.userLoginAccount;

public class returnBookController extends baseSceneController {
	@FXML
	private Label label,label1;
	@FXML
	private ImageView image;
	@FXML
    private Button home, borrower, payer, user, employees, record,search,searchID,back;
    @FXML
    private TextField bookCode,title,chapter,author,quantity;
    @FXML
    private TextField borrowerID,username,phone,borrowDate,returnDate,status,searchAccount,userID;
    @FXML
    private TableView<ReturnNew> tableBook; 
    @FXML
    private TableColumn<ReturnNew, String> columnID, columnName, columnPhone, columnBorrow,columnReturn,columnCode;
    @FXML
    private ObservableList<ReturnNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<ReturnNew> incomingBookList = FXCollections.observableArrayList();
    
    public void setBookList(ObservableList<ReturnNew> diffbook) {
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
        columnID.setCellValueFactory(new PropertyValueFactory<>("borrowerID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(borrower);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(user);

        tableBook.setItems(incomingBookList);
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMEONALL1(searchID,borrowerID,image);
        tran.COMEUNDER2(tableBook);
        tran.COMEON(label);
        tran.COMELEFT2(label1,record);
        
        ArrayList<Node> textfield = new ArrayList<Node>();
        textfield.add(userID);
        textfield.add(bookCode);
        textfield.add(borrowDate);
        textfield.add(returnDate);
        textfield.add(status);
        textfield.add(phone);
        textfield.add(username);
        tran.COMELEFTARRAY(textfield);
        
        tran.COMELEFT(back);
    }
    
    @FXML
    private void handleBack() {
    	createScene(back,"/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
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
    	createScene(payer, "/main/sources/quanlyView.fxml", "/main/sources/css/quanly.css");
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

	
	/**xử lý sự kiện tìm kiếm ID người dùng*/
	@FXML
	private void handleSearchID() {
		String check = borrowerID.getText();
		if(!check.isEmpty()) {
			alertController.setNew().AlertComplete("Trả sách thành công");
			ArrayList<borrow> arr = borrowbook.setNew().selectByCondition1(check);
			userID.setText(arr.get(0).getUserID());
			bookCode.setText(arr.get(0).getBookCode());
			borrowDate.setText(arr.get(0).getBorrowDate());
			returnDate.setText(arr.get(0).getReturnDate());
			status.setText(arr.get(0).getStatus());
			phone.setText(arr.get(0).getPhonenum());
			username.setText(arr.get(0).getUserName());
			arr.clear();
		}else {
			alertController.setNew().AlertUnComplete("Trả sách không thành công");
			borrowerID.clear();
		}
	}
	
	/**xử lý sự kiện ghi phiếu người mượn*/
	@FXML
	private void handleRecordForm() {
         String res = borrowerID.getText();
         if(!res.isEmpty()) {
        	 
        	 Return returnBook = new Return(userID.getText(), borrowerID.getText(),bookCode.getText(),returnDate.getText(), username.getText());
        	 int rs = returnbook.setNew().insert(returnBook);
        	 int rs1 = borrowbook.setNew().Delete(res);
        	 String Quantity = addbook.setNewAdd().search2(new add(bookCode.getText()));
        	 add Add = new add(Quantity, bookCode.getText());
        	 int check = addbook.setNewAdd().update3(Add);
        	 alertController.setNew().AlertComplete("Đã tìm thấy ID người mượn");
        	 if(rs > 0 && check > 0 && rs1 > 0) {
        		 ReturnNew returnNew = new ReturnNew(borrowerID.getText(),username.getText(), bookCode.getText(),returnDate.getText());
        		 alertController.setNew().AlertComplete("Đã trả thành công");
            	 incomingBookList.add(returnNew);
            	 clearFields();
        	 }else {
        		 alertController.setNew().AlertUnComplete("Chưa trả thành công");
        	 }
         }else {
        	 alertController.setNew().AlertComplete("Chưa tìm thấy ID người mượn");
         }
	}
	
	private void clearFields() {
	       bookCode.clear();
	       borrowerID.clear();
			username.clear();
			phone.clear();
			borrowDate.clear();
			returnDate.clear();
			status.clear();
	}
}
