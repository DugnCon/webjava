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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.dao.lockAccount;
import main.java.dao.userLoginAccount;
import main.java.model.lockaccount;
import main.java.model.requireNew;
import main.java.model.userLog;

public class lockAccountController extends baseSceneController {
	@FXML
	private TextArea explain;
	@FXML
	private Button home,borrower,payer,user,employees;
	@FXML
	private Button search,complete,back;
	@FXML
	private ImageView image;
	@FXML
	private TextField searchID,ID,username,password,createAc,fullname;
	@FXML
	private Label label,label1;
	
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
	private void initialize() {
		applyHoverEffect(home);
        applyHoverEffect(borrower);
        applyHoverEffect(payer);
        applyHoverEffect(user);
        applyHoverEffect(employees);
        applyHoverEffect(complete);
        applyHoverEffect(back);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMELEFT(back);
        tran.COMEON(label1);
        
        ArrayList<Node> type = new ArrayList<Node>();
        type.add(complete);
        type.add(explain);
		type.add(fullname);
		type.add(createAc);
		type.add(password);
		type.add(username);
		type.add(ID);
		type.add(label);
		type.add(search);
		type.add(searchID);
		type.add(image);
		tran.COMEONARRAY2(type);
	}
	
	@FXML
	private void handleBack() {
		try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<userLog> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                userLog userlog = new userLog(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5));
                bookList.add(userlog);
            }
            
            manageUserController controller = (manageUserController) createScene1(back, 
                "/main/sources/manageUserView.fxml", "/main/sources/css/manageUser.css");
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
		createScene(borrower,"/main/sources/borrowBookView.fxml","/main/sources/css/borrowBook.css");
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
	private void handleSearchID() {
		String id = searchID.getText();
		if(!id.isEmpty()) {
			ArrayList<userLog> arr = userLoginAccount.setNew().selectByCondition1(id);
			ID.setText(arr.get(0).getID());
			username.setText(arr.get(0).getUsername());
			password.setText(arr.get(0).getPassword());
			createAc.setText(arr.get(0).getCreateAc());
			fullname.setText(arr.get(0).getFullname());
			alertController.setNew().AlertComplete("Đã tìm thấy tài khoản");
		}else {
			alertController.setNew().AlertUnComplete("Không tìm thấy tài khoản");
		}
	}
	
	@FXML
	private void handleLock() {
		lockaccount lock = new lockaccount(ID.getText(), username.getText(), password.getText(), fullname.getText(), explain.getText());
		int res = lockAccount.setNew().insertLock(lock);
		if(res > 0) {
			alertController.setNew().AlertBan("Đã khóa tài khoản thành công");
		}else {
			alertController.setNew().AlertBan("Chưa khóa thành công");
		}
	}
}
