package main.java.controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.java.controller.transistionController;

public class interfaceUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3;
	@FXML
	private void initialize() {
		transistionController tran = new transistionController();
		ArrayList<Node> img_1 = new ArrayList<Node>();
		img_1.add(img1);
		img_1.add(img2);
		img_1.add(img3);
		img_1.add(img4);
		
		ArrayList<Node> node = new ArrayList<Node>();
		node.add(home);
		node.add(introduce);
		node.add(suprise);
		node.add(service);
		node.add(contact);
		node.add(back);
		Collections.reverse(node);
		
		ArrayList<Node> vbox = new ArrayList<Node>();
		vbox.add(tuto1);
		vbox.add(tuto2);
		vbox.add(tuto3);
		vbox.add(tuto4);
		
		ArrayList<Node> label = new ArrayList<Node>();
		label.add(label1);
		label.add(label2);
		label.add(label3);
		label.add(label4);
		
		ArrayList<Node> img_2 =  new ArrayList<Node>();
		img_2.add(img5);
		img_2.add(img6);
		img_2.add(img7);
		img_2.add(img8);
		img_2.add(img9);
		img_2.add(img10);
		img_2.add(img11);
		
		ArrayList<Node> button = new ArrayList<Node>();
		button.add(bt1);
		button.add(bt2);
		button.add(bt3);
		
		tran.COMELEFTARRAY(vbox);
		tran.COMERIGHT3(node);
		tran.COMERIGHT3(label);
		tran.COMEONARRAY(img_1);
		tran.COMERIGHT3(img_2);
		tran.COMERIGHT3(button);
	}
	@FXML
	private void handleHome() {
		
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
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}
}
