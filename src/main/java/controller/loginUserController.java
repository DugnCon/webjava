package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.java.dao.signUpAccount;
import main.java.dao.userAccount;
import main.java.dao.userLoginAccount;
import main.java.model.user;
import main.java.model.userLog;

public class loginUserController extends baseSceneController {
    @FXML
    private TextField username, fullname, userName;
    @FXML
    private PasswordField password, repeatpass, passWord;
    @FXML
    private Button login, signup1, signup2, back;
    @FXML
    private StackPane Pane1, Pane2;
    @FXML
    private VBox vbox1, vbox2, vbox3, vbox4;

    private transistionController transitionController = new transistionController();
    
    @FXML
    private void handleLogin() {
        userLog userlog = new userLog(userName.getText(), passWord.getText());
        int res = userLoginAccount.setNew().insertLog(userlog);
        if (res > 0) {
            signUpAccount.getInstance().AlertComplete();
            createScene(login, "/main/sources/interfaceUser.fxml", "/main/sources/css/interfaceUser.css");
        } else {
            signUpAccount.getInstance().AlertUnComplete();
        }
    }

    @FXML
    private void handleBack() {
        // Ẩn tất cả vbox ngoại trừ vbox1 khi trở lại
        vbox1.setVisible(true);
        vbox2.setVisible(true);
        transitionController.switchToLogin(vbox2, vbox1);
    }

    @FXML
    private void handleSignup1() {
        // Ẩn tất cả vbox ngoại trừ vbox3 khi đăng ký
        vbox1.setVisible(false);
        vbox2.setVisible(false);
        vbox3.setVisible(true);
        vbox4.setVisible(true);
        transitionController.switchToSignup(vbox3, vbox4);
    }

    @FXML
    private void handleSignup2() {
        user User = new user(username.getText(), password.getText(), repeatpass.getText(), fullname.getText());
        int res = userAccount.setNew().insertSign(User);
        int res1 = userAccount.setNew().insertLog(User);
        if (res > 0 && res1 > 0) {
            signUpAccount.getInstance().AlertComplete();
            transitionController.switchToLogin(Pane2, Pane1);
        } else {
            signUpAccount.getInstance().AlertUnComplete();
        }
    }
}
