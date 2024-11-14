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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,image;
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
