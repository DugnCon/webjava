package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class interfaceUserController extends baseSceneController {
	@FXML
	private Button home,introduce,suprise,service,contact;
	@FXML
	private void handleHome() {
		createScene(home,"/main/sources/interfaceUser.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleIntro() {
		createScene(introduce,"/main/sources/interfaceUser_1.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleSuprise() {
		
	}
	@FXML
	private void handleService() {
		
	}
	@FXML
	private void handleContact() {
		
	}
}
