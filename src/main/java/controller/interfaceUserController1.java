package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.dao.userLoginAccount;
import main.java.model.addNew;
import main.java.model.borrowNew;
import main.java.model.userLog;

public class interfaceUserController1 extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label11;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3,btt1,btt2,btt3,btt4,btt5;
	@FXML
	private ImageView IMG1,IMG2,IMG3,IMG4,IMG5,IMG6,IMG7;
	@FXML
	private VBox IMG8,IMG9,IMG10,IMG11,IMG12,IMG13;
	@FXML
	private Text LB1;
	@FXML
	private Button LB2,LB3,LB4,LB5,LB6,LB7;
	@FXML
	private HBox h2,h3,h4,h5,h6,h7;
	
	private void applyHoverEffect(Node node) {
	   	 ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), node);
	        scaleIn.setToX(1.2);
	        scaleIn.setToY(1.2);

	        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), node);
	        scaleOut.setToX(1.0);
	        scaleOut.setToY(1.0);

	        node.setOnMouseEntered(e -> scaleIn.play()); 
	        node.setOnMouseExited(e -> scaleOut.play()); 
	 }
	
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
		tran.COMERIGHT(label11);
		
		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		home.setFont(font);
		introduce.setFont(font);
		suprise.setFont(font);
		service.setFont(font);
		contact.setFont(font);
		back.setFont(font);
		
		LB2.setText("VNU-LIC CELEBRATES THE 70TH ANNIVERSARY\r\n"
				+ "OF HANOI LIBERATION DAY\r\n"
				+ "(10/10/1954 - 10/10/2024)");
		LB3.setText("VNU-LIC GUIDES ONLINE LEARNING RESOURCE USAGE\r\n"
				+ "FOR 700 FRESHMEN\r\n"
				+ "(21/09/2024)");
		LB5.setText("VNU-LIC SUPPORTS AND SHARES WITH STAFF,\r\n"
				+ "EDUCATORS, WORKERS, AND STUDENTS\r\n"
				+ "IN FLOOD-AFFECTED AREAS\r\n"
				+ "(17/09/2024)");
		LB4.setText("THE EXCITING AND ENTHUSIASTIC LEARNING ATMOSPHERE\r\n"
				+ "OF STUDENTS AT VNU-LIC\r\n"
				+ "(26/09/2024)");
		LB6.setText("STATISTICS ON INTERNATIONAL PUBLICATIONS SCOPUS/WOS\r\n"
				+ "OF VNU IN SEPTEMBER 2024\r\n"
				+ "(25/09/2024)");
		LB7.setText("THE FRAGRANCE OF GREEN RICE ON EVERY PAGE\r\n"
				+ "(17/09/2024)");
		
		LB1.setFont(font);
		LB2.setFont(font);
		LB3.setFont(font);
		LB4.setFont(font);
		LB5.setFont(font);
		LB6.setFont(font);
		label11.setFont(font);
		
		LB1.setWrappingWidth(500);
		LB2.setMaxWidth(300);
		LB3.setMaxWidth(300);
		LB4.setMaxWidth(300);
		LB5.setMaxWidth(300);
		LB6.setMaxWidth(300);
		
		applyHoverEffect(IMG1);
		applyHoverEffect(h2);
		applyHoverEffect(h3);
		applyHoverEffect(h4);
		applyHoverEffect(h5);
		applyHoverEffect(h6);
		applyHoverEffect(h7);
		applyHoverEffect(IMG8);
		applyHoverEffect(IMG9);
		applyHoverEffect(IMG10);
		applyHoverEffect(IMG11);
		applyHoverEffect(IMG12);
		applyHoverEffect(IMG13);
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
		try {
			 Connection con = JDBCSQL.getConnection();
	            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
	            PreparedStatement prsttm1 = con.prepareStatement("SELECT * FROM borrowed_books WHERE userID = ?");
	            userLog UserLog = new userLog(loginUserController.getInstance().getUser(), "");
	            String ID = userLoginAccount.setNew().searchAcc(UserLog);
	            prsttm1.setInt(1,Integer.parseInt(ID));
	            ResultSet rs = prsttm.executeQuery();
	            ResultSet rs1 = prsttm1.executeQuery();
	            ObservableList<addNew> bookList = FXCollections.observableArrayList();
	            ObservableList<borrowNew> borrowList = FXCollections.observableArrayList();
	            while (rs.next()) {
	                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
	                        rs.getString(5), rs.getString(6), rs.getString(7),
	                        rs.getString(8), rs.getString(9));
	                bookList.add(AddNew);
	            }
	            
	            while(rs1.next()) {
	            	borrowNew BorrowNew = new borrowNew(rs1.getInt(1), rs1.getString(2) , rs1.getString(3));
	            	borrowList.add(BorrowNew);
	            }
	            
	            requestUserController controller = (requestUserController) createScene1(suprise, 
	                "/main/sources/requestUserView.fxml", "/main/sources/css/interfaceUser.css");
	            controller.setBookList(bookList); 
	            controller.setBorrowList(borrowList);             
	                
	            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
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
	
	@FXML
	private void handleNews1() {
		createScene(LB2,"/main/sources/news1View.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews2() {
		createScene(LB3,"/main/sources/news2View.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews3() {
		createScene(LB4,"/main/sources/news3View.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews4() {
		createScene(LB5,"/main/sources/news4.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews5() {
		
	}
	
	@FXML
	private void handleNews6() {
		
	}
	
}
