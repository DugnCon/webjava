package main.java.controller;

import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class interfaceUserController1 extends baseSceneController {
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
	private ImageView IMG1,IMG2,IMG3,IMG4,IMG5,IMG6,IMG7;
	@FXML
	private VBox IMG8,IMG9,IMG10,IMG11,IMG12,IMG13;
	@FXML
	private Label LB1,LB2,LB3,LB4,LB5,LB6,LB7;
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
		
		ArrayList<Node> vbox1 = new ArrayList<Node>();
		vbox1.add(IMG8);
		vbox1.add(IMG9);
		vbox1.add(IMG10);
		vbox1.add(IMG11);
		vbox1.add(IMG12);
		vbox1.add(IMG13);
		Collections.reverse(vbox1);
		
		ArrayList<Node> label = new ArrayList<Node>();
		label.add(label1);
		label.add(label2);
		label.add(label3);
		label.add(label4);
		
		ArrayList<Node> label1 = new ArrayList<Node>();
		label1.add(LB1);
		label1.add(LB2);
		label1.add(LB3);
		label1.add(LB4);
		label1.add(LB5);
		label1.add(LB6);
		label1.add(LB7);
		
		ArrayList<Node> img_2 =  new ArrayList<Node>();
		img_2.add(img5);
		img_2.add(img6);
		img_2.add(img7);
		img_2.add(img8);
		img_2.add(img9);
		img_2.add(img10);
		img_2.add(img11);
		
		ArrayList<Node> img_3 =  new ArrayList<Node>();
		img_3.add(IMG1);
		img_3.add(IMG2);
		img_3.add(IMG3);
		img_3.add(IMG4);
		img_3.add(IMG5);
		img_3.add(IMG6);
		img_3.add(IMG7);
		
		ArrayList<Node> button = new ArrayList<Node>();
		button.add(bt1);
		button.add(bt2);
		button.add(bt3);
		
		tran.COMELEFTARRAY(vbox);
		tran.COMELEFTARRAY(img_3);
		tran.COMELEFTARRAY(label1);
		tran.COMERIGHT3(node);
		tran.COMERIGHT3(label);
		tran.COMERIGHT3(vbox1);
		tran.COMEONARRAY(img_1);
		tran.COMERIGHT3(img_2);
		tran.COMERIGHT3(button);
	}
	@FXML
	private void handleHome() {
		createScene(home,"/main/sources/interfaceUser.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleIntro() {
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
