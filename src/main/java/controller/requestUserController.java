package main.java.controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.model.addNew;
public class requestUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,
	img9,img10,img11,image,img_6,img_7,img_8,img_9
	,img_10,img22,img33,img44,img55,img66,img77,img88,img99,img1010,img111;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3;
	@FXML
	private TextField fieldSearch;
	@FXML
	private Button searchBook;
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private VBox searchResultsContainer;
	@FXML
	private ProgressIndicator loadingIndicator;
	@FXML
	private Text text1,text2,lb1,lb2,title,title2;
	
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
		text1.setText("This is one of the greatest works of literature in the world. Written in the 19th century, "
		        + "this novel depicts the lives of a group of characters against the backdrop of the Napoleonic Wars "
		        + "and the invasion of Russia in 1812. "
		        + "The work is not just a historical novel about war, but also a profound exploration of love, family, "
		        + "fate, and social change. The main characters include Pierre Bezukhov, Andrei Bolkonsky, and Natasha Rostova, "
		        + "each of whom has their own journey in the search for meaning and happiness in life.");

		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		text1.setFont(font);
		text1.setWrappingWidth(300);
		
		text2.setText("The work tells the story of the life of Pavel Korchagin, a young Russian man living during the October Revolution "
		        + "and the Russian Civil War. The book is not only a work about an individual but also reflects the process "
		        + "of building and developing Soviet society. It has become a symbol of heroism in Russian literature, "
		        + "and Pavel Korchagin has become an ideal model of the era.");
		
		text2.setFont(font);
		text2.setWrappingWidth(300);
		
		lb1.setFont(font);
		lb2.setFont(font);
		title.setFont(font);
		title2.setFont(font);
		home.setFont(font);
		introduce.setFont(font);
		suprise.setFont(font);
		service.setFont(font);
		contact.setFont(font);
		back.setFont(font);
		
		applyHoverEffect(img1);
		applyHoverEffect(img2);
		applyHoverEffect(img3);
		applyHoverEffect(img4);
		applyHoverEffect(img5);
		applyHoverEffect(img_6);
		applyHoverEffect(img_7);
		applyHoverEffect(img_8);
		applyHoverEffect(img_9);
		applyHoverEffect(img_10);
		applyHoverEffect(img111);
		applyHoverEffect(img22);
		applyHoverEffect(img33);
		applyHoverEffect(img44);
		applyHoverEffect(img55);
		applyHoverEffect(img66);
		applyHoverEffect(img77);
		applyHoverEffect(img88);
		applyHoverEffect(img99);
		applyHoverEffect(img1010);
		
	}
	
	@FXML
	private void handleSearchBook() {
		
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
