package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.java.dao.addbook;
import main.java.model.add;
import main.java.model.addNew;
import main.java.JDBC.JDBCSQL;

public class deleteBookController extends baseSceneController {
	@FXML
	private Label label;
	@FXML
	private ImageView image;
	@FXML
    private Button home, borrower, payer, user, employees, deleted,back,Back;
    @FXML
    private TextField nameBook, chapBook, publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity,searchCode;
    @FXML
    private TableView<addNew> tableBook;
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
 
    public void setBookList(ObservableList<addNew> diffbook) {
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
    private void handleBack() {
    	createScene(Back,"/main/sources/quanlyView.fxml","/main/sources/css/quanly.css");
    }
    
    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(borrower);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(back);
        addButtonZoomEffect(user);

        tableBook.setItems(incomingBookList);
        transistionController tran = new transistionController();
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
        tran.COMEON(back);
        tran.COMELEFT(Back);
        tran.COMEUNDER2(tableBook);
        tran.COMEON(label);
        tran.COMEONALL1(deleted,searchCode,image);
        tran.COMELEFT1(nameBook, nameAuthor, publisher, bookCode, chapBook, styleBook, releaseYear, quantity);
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
	
	@FXML
	private void handleLoad() {
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
            
            deleteBookController controller = (deleteBookController) createScene1(back, 
            		"/main/sources/deleteBookView.fxml", "/main/sources/css/deleteBook.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
	}
	
	@FXML
	private void handleDelete() {
	    try {
	        Connection con = JDBCSQL.getConnection();
	        PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode = ?");
	        prsttm.setString(1, searchCode.getText());
	        ResultSet rs = prsttm.executeQuery();
	        if (rs.next()) {
	            // Nếu tìm thấy sách, tiến hành xóa
	            add Add = new add(searchCode.getText(), rs.getString(2), rs.getString(3), rs.getString(4), 
	                              rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
	            int res = addbook.setNewAdd().Delete(Add);

	            if (res > 0) {
	            	addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                            rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
	                incomingBookList.add(AddNew);
	                addbook.setNewAdd().AlertComplete();
	                clearFields();
	            } else {
	                addbook.setNewAdd().AlertUnComplete();
	                //System.out.print("1");
	                clearFields();
	            }
	        } else {
	            addbook.setNewAdd().AlertUnComplete();
	            //System.out.print("2");
	            clearFields();
	        }
	        
	        con.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

    private void clearFields() {
        searchCode.clear();
    }
}