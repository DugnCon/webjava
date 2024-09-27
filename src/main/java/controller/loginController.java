package main.java.controller;

import java.io.IOException;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class loginController {
    @FXML
    private VBox layAll;
    @FXML
    private HBox layLog, laySign;
    @FXML
    private TextField username, cardId;
    @FXML
    private PasswordField password;
    @FXML
    private Label notifyLabel;
    @FXML
    private ImageView imageIcon;
    @FXML
    private Button signup, login;
    @FXML
    private Stage stage;

    @FXML
    private void handleButtonLogin() {
    	try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/interfaceView.fxml"));
			Scene newScene = new Scene(newRoot, 1200, 780);
	        newScene.getStylesheets().add(getClass().getResource("/main/sources/css/interface.css").toExternalForm());
            // Lấy Stage hiện tại
	        Stage stage = (Stage) login.getScene().getWindow();
	        stage.setScene(newScene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    private void handleButtonSignUp() {
        try {
            // Tải FXML cho cảnh mới
            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/signupView.fxml"));
            Scene newScene = new Scene(newRoot, 1200, 780);
            newScene.getStylesheets().add(getClass().getResource("/main/sources/css/signup.css").toExternalForm());

            // Lấy Stage hiện tại
            Stage stage = (Stage) signup.getScene().getWindow();

            // Thêm TranslateTransition
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0), stage.getScene().getRoot());
            transition.setFromX(0);
            transition.setToX(stage.getWidth()); // Di chuyển ra ngoài bên phải
            transition.setOnFinished(e -> {
                stage.setScene(newScene); // Đặt cảnh mới sau khi hoàn thành chuyển động
                TranslateTransition fadeIn = new TranslateTransition(Duration.seconds(0), newRoot);
                fadeIn.setFromX(-stage.getWidth()); // Bắt đầu từ bên trái
                fadeIn.setToX(0); // Kết thúc tại vị trí ban đầu
                fadeIn.play(); // Chạy chuyển động fade in
            });
            
            // Bắt đầu chuyển động
            transition.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
}
