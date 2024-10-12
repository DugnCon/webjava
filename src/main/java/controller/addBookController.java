package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.model.add;
import main.java.model.addNew;
import main.java.dao.addbook;

public class addBookController extends baseSceneController {
    @FXML
    private Button home, borrower, payer, user, employees, completed;
    @FXML
    private TextField nameBook, chapBook, publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity;
    @FXML
    private TableView<addNew> tableBook; 
    @FXML
    private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
    @FXML
    private ObservableList<addNew> bookList = FXCollections.observableArrayList();
    
    private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
    
    public static addBookController setNew() {
    	return new addBookController();
    }
    
    public void setBookList(ObservableList<addNew> diffbook) {
        this.incomingBookList = diffbook;
        tableBook.setItems(incomingBookList);
    }
    
    @FXML
    private void initialize() {
        columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));

        tableBook.setItems(incomingBookList);
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
    private void handleComplete() {
        if (isInputValid()) {
            add Add = new add(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                    nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                    releaseYear.getText(), quantity.getText());
            addNew AddNew = new addNew(bookCode.getText(), nameBook.getText(), chapBook.getText(),
                    nameAuthor.getText(), styleBook.getText(), publisher.getText(),
                    releaseYear.getText(), quantity.getText());

            int res = addbook.setNewAdd().insert1(Add);
            if (res > 0) {
            	incomingBookList.add(AddNew);
                addbook.setNewAdd().AlertComplete();
                clearFields();
            } else {
                addbook.setNewAdd().AlertUnComplete();
            }
        }
    }

    private boolean isInputValid() {
        return !bookCode.getText().isEmpty() && !nameBook.getText().isEmpty();
    }

    private void clearFields() {
        bookCode.clear();
        nameBook.clear();
        chapBook.clear();
        nameAuthor.clear();
        styleBook.clear();
        publisher.clear();
        releaseYear.clear();
        quantity.clear();
    }
}