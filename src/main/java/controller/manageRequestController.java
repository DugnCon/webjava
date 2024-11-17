package main.java.controller;

import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import main.java.model.requireNew;
import main.java.model.userLog;

public class manageRequestController extends baseSceneController {
	@FXML
	private Label label1,label2,label;
	@FXML
    private Button home, borrower, payer, user, employees, record,search,searchID,back;
	@FXML
    private TableView<requireNew> tableBook; 
    @FXML
    private TableColumn<requireNew, String> columnID, columnUser, columnRequest,columnTime;
    @FXML
    private ObservableList<requireNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<requireNew> incomingBookList = FXCollections.observableArrayList();
    
    public void setBookList(ObservableList<requireNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }
    
    private void addButtonZoomEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(e -> scaleIn.play()); 
        button.setOnMouseExited(e -> scaleOut.play()); 
    }
	
    @FXML
    private void initialize() {
    	columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        columnRequest.setCellValueFactory(new PropertyValueFactory<>("request"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("timeRequire"));
        
        tableBook.setItems(incomingBookList);
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(user);
        addButtonZoomEffect(borrower);
        
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        ArrayList<Node> node = new ArrayList<Node>();
        node.add(label);
        node.add(tableBook);
        tran.COMEONARRAY(node);
    }
    
    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }
    /**xử lý sự kiện mượn sách*/
	@FXML
	private void handleBorrower() {
		
	}
	
	/**xử lý sự kiện người trả*/
	@FXML
	private void handlePayer() {
		
	}
	
	
	/**xử lý sự kiện người dùng*/
	@FXML
	private void handleUser() {
		
	}
	
	/**xử lý sự kiện nhân viên*/
	@FXML
	private void handleEmployees() {
		
	}
	
}
