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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.model.addNew;

public class news2Controller extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label11;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,imgtitle;
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
	@FXML
	private Button customButton;
	@FXML
	private Text text1,title,title1;
	
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
		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		text1.setText("On September 21, 2024, the Center for Library and Digital Knowledge (VNU-LIC) organized a tutorial on using learning materials for nearly 700 freshmen from the Interdisciplinary and Arts Faculty in an online format.\r\n"
				+"📝The tutorial was presented by VNU-LIC staff using slides, videos, and various illustrative images that helped the freshmen understand the overview of the 5 learning resources, 10 Knowledge Management services, as well as how to obtain accounts, log in, and use VNU-LIC's learning materials. During the session, the staff also answered questions and clarified how to access and use the accounts.\r\n"
				+"In this tutorial session, VNU-LIC guided 15,000 freshmen from 11 training units of VNU in both in-person and online formats.");
		text1.setFont(font);
		text1.setWrappingWidth(1000);
		
		title.setText("VNU-LIC GUIDES 700 FRESHMEN OF THE INTERDISCIPLINARY AND ARTS FACULTY ON USING ONLINE LEARNING MATERIALS.");
		title.setFont(font);
		title.setWrappingWidth(1000);
		
		title1.setText("Related Articles");
		title1.setFont(font);
		title1.setWrappingWidth(200);
		transistionController tran = new transistionController();
		ArrayList<Node> Tran = new ArrayList<Node>();
		Tran.add(title);
		Tran.add(text1);
		Tran.add(imgtitle);
		tran.COMERIGHT3(Tran);
		
		ArrayList<Node> img_1 = new ArrayList<Node>();
		img_1.add(img1);
		img_1.add(img2);
		img_1.add(img3);
		img_1.add(img4);
		tran.COMEONARRAY(img_1);
		
		ArrayList<Node> img_2 =  new ArrayList<Node>();
		img_2.add(img5);
		img_2.add(img6);
		img_2.add(img7);
		img_2.add(img8);
		img_2.add(img9);
		img_2.add(img10);
		img_2.add(img11);
		tran.COMERIGHT3(img_2);
		
		ArrayList<Node> node1 =  new ArrayList<Node>();
		node1.add(title1);
		node1.add(h2);
		node1.add(h4);
		node1.add(h5);
		node1.add(h6);
		node1.add(h7);
		tran.COMELEFTARRAY(node1);
		
		LB2.setText("VNU-LIC CELEBRATES THE 70TH ANNIVERSARY\\r\\n\"\r\n"
				+"OF HANOI LIBERATION DAY\\r\\n\"\r\n"
				+"(10/10/1954 - 10/10/2024)");
		LB4.setText("VNU-LIC SUPPORTS AND SHARES WITH STAFF,\r\n"
				+ "EDUCATORS, WORKERS, AND STUDENTS\r\n"
				+ "IN FLOOD-AFFECTED AREAS\r\n"
				+ "(17/09/2024)");
		LB5.setText("THE EXCITING AND ENTHUSIASTIC LEARNING ATMOSPHERE\r\n"
				+ "OF STUDENTS AT VNU-LIC\r\n"
				+ "(26/09/2024)");
		LB6.setText("STATISTICS ON INTERNATIONAL PUBLICATIONS SCOPUS/WOS\r\n"
				+ "OF VNU IN SEPTEMBER 2024\r\n"
				+ "(25/09/2024)");
		LB7.setText("THE FRAGRANCE OF GREEN RICE ON EVERY PAGE\r\n"
				+ "(17/09/2024)");
		
		LB2.setFont(font);
		LB4.setFont(font);
		LB5.setFont(font);
		LB6.setFont(font);
		
		LB2.setMaxWidth(300);
		LB4.setMaxWidth(300);
		LB5.setMaxWidth(300);
		LB6.setMaxWidth(300);
		
		applyHoverEffect(h2);
		applyHoverEffect(h4);
		applyHoverEffect(h5);
		applyHoverEffect(h6);
		applyHoverEffect(h7);
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
            ResultSet rs = prsttm.executeQuery();
            ObservableList<addNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
                bookList.add(AddNew);
            }
            
            requestUserController controller = (requestUserController) createScene1(suprise, 
                "/main/sources/requestUserView.fxml", "/main/sources/css/interfaceUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
	}
	@FXML
	private void handleService() {
		createScene(service,"/main/sources/Trivia.fxml","");
	}
	@FXML
	private void handleContact() {
		createScene(contact,"/main/sources/contactView.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/interfaceUser_1.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleNews1() {
		createScene(LB2,"/main/sources/news1View.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews3() {
		createScene(LB4,"/main/sources/news4.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews4() {
		createScene(LB5,"/main/sources/news3View.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleNews5() {
		
	}
	
	@FXML
	private void handleNews6() {
		
	}
	
}
