package main.java.controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.java.dao.lockAccount;
import main.java.dao.signUpAccount;
import main.java.dao.userAccount;
import main.java.dao.userLoginAccount;
import main.java.model.lockaccount;
import main.java.model.user;
import main.java.model.userLog;

public class loginUserController extends baseSceneController {
	@FXML
    private HBox HBOX1,HBOX2;
    @FXML
    private VBox VBOX1,VBOX2,VBOX3,VBOX4,VBOXCHILD1,VBOXCHILD2,VBOXCHILD3,VBOXCHILD4;
    @FXML
    private StackPane ST1,ST2;
    @FXML
    private TextField username, fullname, userName;
    @FXML
    private PasswordField password, repeatpass, passWord;
    @FXML
    private Button login1, signup1, signup2, login2;
    @FXML
    private StackPane Pane1, Pane2;
    @FXML
    private VBox vbox1, vbox2, vbox3, vbox4;
    
	@FXML
    private void handleButtonLogin1() {
        userLog userlog = new userLog(username.getText(), password.getText());
        int res = userLoginAccount.setNew().insertLog(userlog);
        ArrayList<lockaccount> arr = lockAccount.setNew().selectByCondition(username.getText());
        if(arr.isEmpty()) {
        	alertController.setNew().AlertComplete("Đăng nhập thành công");
            createScene(login1, "/main/sources/interfaceUser.fxml", "/main/sources/css/interfaceUser.css");
        }else {
        	String isUsername = arr.get(0).getUsername();
            String notify = arr.get(0).getExplain();
            arr.clear();
            if(res < 1) {
            	alertController.setNew().AlertComplete("Sai mật khẩu");
            } else if (res > 0 && (!username.getText().equals(isUsername) || isUsername.isEmpty())) {
            	alertController.setNew().AlertComplete("Đăng nhập thành công");
                createScene(login1, "/main/sources/interfaceUser.fxml", "/main/sources/css/interfaceUser.css");
            } else {
            	alertController.setNew().AlertComplete(notify);
            }
        }
    }

    @FXML
    private void handleButtonLogin2() {
    	transistionController tran = new transistionController();
    	tran.ComeRight1(ST2);
        tran.ComeLeft1(ST1);
        tran.hideWithFade(VBOX3);
    	tran.hideWithFade(VBOX4);
    	tran.UnhideWithFade(VBOX1);
    	tran.UnhideWithFade(VBOX2);
    }

    @FXML
    private void handleButtonSignUp1() {
    	transistionController tran = new transistionController();
        tran.ComeRight(ST1);
        tran.ComeLeft(ST2);
        tran.hideWithFade(VBOX1);
    	tran.hideWithFade(VBOX2);
    	tran.UnhideWithFade(VBOX3);
    	tran.UnhideWithFade(VBOX4);
    }

    @FXML
    private void handleButtonSignup2() {
    	user User = new user(userName.getText(), passWord.getText(), repeatpass.getText(), fullname.getText());
        int res = userAccount.setNew().insertSign(User);
        int res1 = userAccount.setNew().insertLog(User);
        if (res > 0 && res1 > 0) {
            signUpAccount.getInstance().AlertComplete();
            transistionController tran = new transistionController();
	    	tran.ComeLeft1(ST1);
	        tran.ComeRight1(ST2);
	        tran.hideWithFade(VBOX3);
	    	tran.hideWithFade(VBOX4);
	    	tran.UnhideWithFade(VBOX1);
	    	tran.UnhideWithFade(VBOX2);	
        } else {
            signUpAccount.getInstance().AlertUnComplete();
        }
    }
    
    public void clear() {
    	userName.clear();
    	passWord.clear();
    	repeatpass.clear();
    	fullname.clear();
    }
}
