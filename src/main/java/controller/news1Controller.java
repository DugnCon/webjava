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

public class news1Controller extends baseSceneController {
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
		text1.setText("Throughout thousands of years of heroic history, Thang Long - Hanoi has endured countless wars of resistance against foreign invaders, ending in triumphant victories. Among them, the Capital Liberation Day, October 10, 1954, is a glorious milestone marking the complete defeat of French colonialism in Vietnam, ushering in a new era of development for the capital and the nation.\n\n"
		        + "Turning back the pages of history to 70 years ago, at exactly 4:00 PM on October 9, 1954, the last French soldiers withdrew across the Long Bien Bridge; our troops gained full control of the city. On the morning of October 10, 1954, the city's Military Commission and units of the People's Army, including infantry, artillery, anti-aircraft, and mechanized forces, organized a historic march into Hanoi. Two hundred thousand citizens of the capital enthusiastically welcomed the victorious troops amidst a sea of red flags with golden stars.\n\n"
		        + "At 3:00 PM that day, tens of thousands of citizens solemnly attended the flag-raising ceremony organized by the Military Commission with the participation of units of the People's Army taking over the city. The red flag with a golden star fluttered proudly atop the ancient Flag Tower... All of Hanoi rejoiced in the liberation.\n\n"
		        + "October 10, 1954, remains an unforgettable emotional landmark for generations of Vietnamese people. It is a milestone in the history of building and developing the capital and the nation, marking a significant turning point, heralding a new era of development where working people became masters of their destiny, joyfully embarking on building a new society - a socialist society.");
		text1.setFont(font);
		text1.setWrappingWidth(1000);
		
		title.setText("VNU-LIC WELCOMES THE 70TH ANNIVERSARY OF THE LIBERATION OF HANOI (10/10/1954-10/10/2024)");
		title.setFont(font);
		title.setWrappingWidth(1000);
		
		title1.setText("Related Articles");
		title1.setFont(font);
		title1.setWrappingWidth(200);
		transistionController tran = new transistionController();
		ArrayList<Node> Tran = new ArrayList<Node>();
		Tran.add(title);
		Tran.add(text1);
		tran.COMERIGHT3(Tran);
		
		ArrayList<Node> node = new ArrayList<Node>();
		node.add(home);
		node.add(introduce);
		node.add(suprise);
		node.add(service);
		node.add(contact);
		node.add(back);
		Collections.reverse(node);
		tran.COMERIGHT3(node);
		
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
		node1.add(h3);
		node1.add(h4);
		node1.add(h5);
		node1.add(h6);
		node1.add(h7);
		tran.COMELEFTARRAY(node1);
		
		LB3.setText("VNU-LIC GUIDES ONLINE LEARNING RESOURCE USAGE\r\n"
				+ "FOR 700 FRESHMEN\r\n"
				+ "(21/09/2024)");
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
		
		LB3.setFont(font);
		LB4.setFont(font);
		LB5.setFont(font);
		LB6.setFont(font);
		
		LB3.setMaxWidth(300);
		LB4.setMaxWidth(300);
		LB5.setMaxWidth(300);
		LB6.setMaxWidth(300);
		
		applyHoverEffect(h3);
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
	private void handleNews2() {
		createScene(LB3,"/main/sources/news2View.fxml","/main/sources/css/interfaceUser.css");
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
