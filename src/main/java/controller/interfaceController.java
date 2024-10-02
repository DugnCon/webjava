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

public class interfaceController {
	@FXML
	private Button signout,manageBook,manageBorrow,managePay,manageUser,manageEmployees;
	@FXML
	private void handleKhosach() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/quanlyView.fxml"));
			Scene newScene = new Scene(newRoot, 1200, 780);
	        newScene.getStylesheets().add(getClass().getResource("/main/sources/css/quanly.css").toExternalForm());
            // Lấy Stage hiện tại
	        Stage stage = (Stage) manageBook.getScene().getWindow();
	        stage.setScene(newScene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void handleMuonsach() {
		
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
	private void handleDangxuat() {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
			Scene newScene = new Scene(newRoot, 1200, 780);
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