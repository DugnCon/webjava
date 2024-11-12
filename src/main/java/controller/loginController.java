package main.java.controller;

import java.io.IOException;

import main.java.controller.AppController;
import main.java.dao.loginAccount;
import main.java.dao.signUpAccount;
import main.java.model.author;
import main.java.model.authorLog;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import main.java.controller.transistionController;

public class loginController extends baseSceneController {
    @FXML
    private VBox layAll,backgroundLogin;
    @FXML
    private HBox layLog, laySign;
    @FXML
    private HBox HBOX1,HBOX2;
    @FXML
    private VBox VBOX1,VBOX2,VBOX3,VBOX4,VBOXCHILD1,VBOXCHILD2,VBOXCHILD3,VBOXCHILD4;
    @FXML
    private StackPane ST1,ST2;
    @FXML
    private TextField Username,userName,fullName;
    @FXML
    private PasswordField passWord,repeatPass,Password;
    @FXML
    private Label notifyLabel;
    @FXML
    private ImageView imageIcon;
    @FXML
    private Button signup1, login1,login2,signup2;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    
    @FXML
    private void initilized() {
    	VBOX3.setVisible(false);
    	VBOXCHILD3.setVisible(false);
    }
   
    @FXML
    private void handleButtonLogin1() {
		String username = Username.getText();
		String password = Password.getText();
		authorLog author = new authorLog(username, password);
		int res  = loginAccount.getInstance().insert(author);
		if(res > 0) {
			alertController.setNew().AlertComplete("Đăng nhập thành công");
			createScene(login1,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");	
		}else {
			alertController.setNew().AlertUnComplete("Đăng nhập không thành công");
			 createScene(signup1, "/main/sources/loginView.fxml", "/main/sources/css/login.css");
		}
    }
    
    @FXML
	protected void handleButtonSignUp1() {
        // Tải FXML cho cảnh mới
    	transistionController tran = new transistionController();
        tran.ComeRight(ST1);
        tran.ComeLeft(ST2);
        tran.hideWithFade(VBOX1);
    	tran.hideWithFade(VBOX2);
    	tran.UnhideWithFade(VBOX3);
    	tran.UnhideWithFade(VBOX4);
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
    private void handleButtonSignup2() {
    	String UserName = userName.getText();
        String PassWord = passWord.getText();
        String RepeatPassWord = repeatPass.getText();
        String FullName = fullName.getText();
		author Author = new author(UserName, PassWord, RepeatPassWord, FullName);
		int res = signUpAccount.getInstance().insert(Author);
		if(res > 0) {
			transistionController tran = new transistionController();
	    	tran.ComeLeft1(ST1);
	        tran.ComeRight1(ST2);
	        tran.hideWithFade(VBOX3);
	    	tran.hideWithFade(VBOX4);
	    	tran.UnhideWithFade(VBOX1);
	    	tran.UnhideWithFade(VBOX2);	
		}else {
			signUpAccount.getInstance().AlertUnComplete();
		}
    }
    
}
