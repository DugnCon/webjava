package main.java.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import main.java.dao.lockAccount;
import main.java.dao.userLoginAccount;
import main.java.model.lockaccount;
import main.java.model.userLog;

public class restoreAccountController {
	@FXML
	private TextArea explain;
	@FXML
	private Button home,borrower,payer,user,employees;
	@FXML
	private Button search,complete;
	@FXML
	private ImageView image;
	@FXML
	private TextField searchID,ID,username,password,createAc,fullname;
	@FXML
	private Label label;
	
	@FXML
	private void handleHome() {
		
	}
	
	@FXML
	private void handleBorrower() {
		
	}
	
	@FXML
	private void handlePayer() {
		
	}
	
	@FXML
	private void handleUser() {
		
	}
	
	@FXML
	private void handleEmployees() {
		
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
	private void handleRestore() {
		lockaccount lock = new lockaccount(ID.getText());
		int res = lockAccount.setNew().insertLock(lock);
		if(res > 0) {
			alertController.setNew().AlertBan("Đã mở khóa lại tài khoản thành công");
		}else {
			alertController.setNew().AlertBan("Chưa mở khóa thành công");
		}
	}
}
