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
import main.java.model.addNew;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;
import main.java.dao.borrowbook;
import main.java.dao.userAccount;
import main.java.dao.userLoginAccount;
import main.java.model.add;
import main.java.model.alter;
import main.java.model.borrow;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.user;
import main.java.model.userLog;

public class recordFormController extends baseSceneController {
	@FXML
	private Label label1,label2;
	@FXML
	private ImageView image1,image2;
	@FXML
    private Button home, borrower, payer, user, employees, record,search,searchID,back;
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
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phonenum"));
        columnBorrow.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        columnReturn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(borrower);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(user);

        tableBook.setItems(incomingBookList);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMEONALL1(search,bookCode,image1);
        tran.COMEONALL1(searchID,userID,image2);
        tran.COMEUNDER2(tableBook);
        tran.COMEUNDER2(record);
        
        ArrayList<Node> textfield1 = new ArrayList<Node>();
        textfield1.add(title);
        textfield1.add(chapter);
        textfield1.add(author);
        textfield1.add(quantity);
        tran.COMELEFTARRAY(textfield1);
        tran.COMEON(label1);
        
        ArrayList<Node> textfield2 = new ArrayList<Node>();
        textfield2.add(borrowerID);
        textfield2.add(username);
        textfield2.add(phone);
        textfield2.add(borrowDate);
        textfield2.add(returnDate);
        tran.COMELEFTARRAY(textfield2);
        tran.COMEON(label2);
        
        tran.COMELEFT(back);
    }
    
    @FXML
    private void handleBack() {
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
            
            borrowBookController controller = (borrowBookController) createScene1(back, 
                "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
            controller.setBookList(bookList);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }
    /**xử lý sự kiện mượn sách*/
    @FXML
	private void handleBorrower() {
		createScene(borrower,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
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
            
            manageUserController controller = (manageUserController) createScene1(employees, 
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
	
	/**xử lý sự kiện kiểm tra xe sách có tồn tại*/
	@FXML
	private void handleSearch() {
		add Add = new add(bookCode.getText());
		String bookcode = addbook.setNewAdd().search(Add);
		if(!bookcode.isEmpty()) {
			alertController.setNew().AlertComplete("Đã tìm thấy mã sách thành công");
			ArrayList<alter> arr = addbook.setNewAdd().selectByCondition1(bookcode);
			title.setText(arr.get(0).getnameBook());
			chapter.setText(arr.get(0).getchapBook());
			author.setText(arr.get(0).getnameAuthor());
			quantity.setText(arr.get(0).getQuantity());
		}else {
			alertController.setNew().AlertComplete("Đã tìm thấy mã sách không thành công");
			clearFields();
		}
	}
	
	/**xử lý sự kiện ghi phiếu người mượn*/
	@FXML
	private void handleRecordForm() {
         String res = bookCode.getText();
         if(!res.isEmpty()) {
        	 
        	 /**Cần sửa đoạn này*/
        	 borrow Borrow = new borrow(borrowerID.getText(), "19",bookCode.getText()
                     ,borrowDate.getText(), returnDate.getText(), username.getText() ,"Đang mượn", phone.getText());
        	 
        	 int rs = borrowbook.setNew().insert(Borrow);
        	 /********************/
        	 add Add = new add(quantity.getText(), res);
        	 int check = addbook.setNewAdd().update2(Add);
        	 int Quantity = Integer.parseInt(quantity.getText());
        	 if(rs > 0 && check > 0 && Quantity >= 1) {
        		 borrowNew BorrowNew = new borrowNew(borrowerID.getText(),bookCode.getText(), username.getText(), 
                         borrowDate.getText(), returnDate.getText(), phone.getText());
        		 alertController.setNew().AlertComplete("Ghi nhận thành công");
            	 incomingBookList.add(BorrowNew);
            	 clearFields();
            	 clearFields1();
        	 }else {
        		 alertController.setNew().AlertUnComplete("Ghi nhạn không thành công");
        	 }
         }else {
        	 alertController.setNew().AlertUnComplete("Không tìm thấy mã sách");
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
