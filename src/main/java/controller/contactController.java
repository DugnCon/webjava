package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.JDBC.JDBCSQL;
import main.java.dao.userLoginAccount;
import main.java.model.addNew;
import main.java.model.borrowNew;
import main.java.model.userLog;
import main.java.triviaGame.playmusic;

public class contactController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label5,label6,yourID,lab1,lab2;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4,vboxnew;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,
	img9,img10,img11,img_1,img_2,img_3,img_4,img_5,image,img_6,img_7,img_8,img_9
	,img_10,img22,img33,img44,img55,img66,img77,img88,img99,img1010,img111,img1001,img1002;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3,complete,comlete1,searchID,search;
	@FXML
	private TextField fieldSearch,username,textReturn;
	@FXML
	private TextField bookCode,borrowerID,userName
	,phone,borrowDate,returnDate,status,searchAccount
	,userID,Title,chapter,author,quantity;
	@FXML
	private Button searchBook,clear,loading,listen1,listen2,listen3,Pause1,Pause2,Pause3;
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private VBox searchResultsContainer,para,yourComment;
	@FXML
	private ProgressIndicator loadingIndicator;
	@FXML
	private Text text1,text2,lb1,lb2,title,title2
	,paragraph,note,yourName,comment;
	@FXML
    private BorderPane mainContent;
	@FXML
	private HBox hboxnew;
	@FXML
	private TextArea textarea;
	@FXML
	private TableView<addNew> tableBook;
	@FXML
	private TableView<borrowNew> tableBorrowBook;
	@FXML
	private TableColumn<borrowNew, String> columnUserID1, columnCode1, columnTitle1;
	@FXML
	private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
	@FXML
	private ObservableList<addNew> bookList = FXCollections.observableArrayList();
	@FXML
	private ObservableList<borrowNew> borrowList = FXCollections.observableArrayList();
	
	private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
	
	private ObservableList<borrowNew> incomingBorrowList = FXCollections.observableArrayList();
	
	private APIController apiController = new APIController();
	
    private ScheduledExecutorService scheduler;
    
    private Runnable searchTask;
    
    private playmusic musicPlayer = playmusic.getInstance();
    
	private boolean isMusicPlaying = false;
	 private playmusic play1,play2,play3,pau1,pau2,pau3;

    
    @FXML
	private void initialize() {
    	play1 = new playmusic();
    	play2 = new playmusic();
    	play3 = new playmusic();
    	
		transistionController tran = new transistionController();
		ArrayList<Node> img_1 = new ArrayList<Node>();
		img_1.add(img1);
		img_1.add(img2);
		img_1.add(img3);
		img_1.add(img4);
		
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
		label.add(label5);
		
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
		tran.COMERIGHT3(label);
		tran.COMEONARRAY(img_1);
		tran.COMERIGHT3(img_2);
		tran.COMERIGHT3(button);
		tran.COMELEFT(img_6);
		
		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		home.setFont(font);
		introduce.setFont(font);
		suprise.setFont(font);
		service.setFont(font);
		contact.setFont(font);
		back.setFont(font);
		
	}
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
		if (isMusicPlaying) {
	        stopAllMusic();
	        isMusicPlaying = false;
	    }
		createSceneGame(service,"/main/sources/Trivia.fxml","","/music.mp3");
	}
	
	@FXML
	private void handleContact() {
		
	}
	
	@FXML
	private void handleBack() {
		if (isMusicPlaying) {
	        stopAllMusic();
	        isMusicPlaying = false;
	    }
		createScene(back,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}


@FXML
private void handlePlay1() {
    if (!isMusicPlaying) {
        stopAllMusic();
        musicPlayer.initMusic(getClass().getResource("/dc558c1f-7664-4d24-9948-7a1ab318892a.mp3").toString());
        musicPlayer.playMusic();
        isMusicPlaying = true;
        System.out.println("Playing music 1.");
    }
}

@FXML
private void handlePlay2() {
    if (!isMusicPlaying) {
        stopAllMusic();
        musicPlayer.initMusic(getClass().getResource("/Tainhanh.net_YouTube_SON-TUNG-MTP-CHUNG-TA-CUA-HIEN-TAI-CM1X_Media_QPKHfugLTqc_006_144p.mp3").toString());
        musicPlayer.playMusic();
        isMusicPlaying = true;
        System.out.println("Playing music 2.");
    }
}

@FXML
private void handlePlay3() {
    if (!isMusicPlaying) {
        stopAllMusic();
        musicPlayer.initMusic(getClass().getResource("/Tainhanh.net_YouTube_Last-Christmas-Remix-Hung-Hack-Nhac-Remi_Media_iDXRKHY7mJA_006_144p.mp3").toString());
        musicPlayer.playMusic();
        isMusicPlaying = true;
        System.out.println("Playing music 3.");
    }
}

@FXML
private void handlePause1() {
    musicPlayer.pauseMusic();
    isMusicPlaying = false;
    System.out.println("Paused music 1.");
}

@FXML
private void handlePause2() {
    musicPlayer.pauseMusic();
    isMusicPlaying = false;
    System.out.println("Paused music 2.");
}

@FXML
private void handlePause3() {
    musicPlayer.pauseMusic();
    isMusicPlaying = false;
    System.out.println("Paused music 3.");
}

private void stopAllMusic() {
    musicPlayer.pauseMusic();
    isMusicPlaying = false;
    System.out.println("Stopped all music.");
}
}
