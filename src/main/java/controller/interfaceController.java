package main.java.controller;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class interfaceController extends baseSceneController {
	@FXML
	private Button signout,manageBook,manageBorrow,managePay,manageUser,manageEmployees,interfaceUser;
	@FXML
	private void handleKhosach() {
		createScene(manageBook, "/main/sources/quanlyView.fxml" , "/main/sources/css/quanly.css");
	}
	@FXML
	private void handleMuonsach() {
		createScene(manageBorrow, "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
	}
	@FXML
	private void handleTrasach() {
		
	}
	@FXML
	private void handleNhanvien() {
		
	}
	@FXML
	private void handleNguoimuon() {
		
	}
	@FXML
	private void handleInterfaceuser() {
		createScene(interfaceUser,"/main/sources/interfaceUser.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleDangxuat() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
			Scene newScene = new Scene(newRoot, 1536, 790);
	        newScene.getStylesheets().add(getClass().getResource("/main/sources/css/login.css").toExternalForm());
            // Lấy Stage hiện tại
	        Stage stage = (Stage) signout.getScene().getWindow();
	        stage.setScene(newScene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}