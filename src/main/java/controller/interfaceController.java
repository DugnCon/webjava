package main.java.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.model.borrowNew;
import main.java.model.userLog;

public class interfaceController extends baseSceneController {
    @FXML
    private Button signout, manageBook, manageBorrow, managePay, manageUser, manageEmployees, interfaceUser;
    @FXML
    private ImageView image;
    @FXML
    private Label label;
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
    public void initialize() {
        addButtonZoomEffect(signout);
        addButtonZoomEffect(manageBook);
        addButtonZoomEffect(manageBorrow);
        addButtonZoomEffect(managePay);
        addButtonZoomEffect(manageUser);
        addButtonZoomEffect(manageEmployees);
        addButtonZoomEffect(interfaceUser);
        transistionController tran = new transistionController();
        tran.COMERIGHTALL2(manageBook,managePay,manageBorrow,manageUser,manageEmployees, signout, interfaceUser);
        tran.COMEONALL2(label,image);
    }

    @FXML
    private void handleKhosach() {
        createScene(manageBook, "/main/sources/quanlyView.fxml", "/main/sources/css/quanly.css");
    }

    @FXML
    private void handleMuonsach() {
        createScene(manageBorrow, "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
    }

    @FXML
    private void handleTrasach() {
        createScene(managePay, "/main/sources/returnBookView.fxml", "/main/sources/css/returnBook.css");
    }

    @FXML
    private void handleNhanvien() {

    }

    @FXML
    private void handleNguoimuon() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<userLog> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                userLog userlog = new userLog(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5));
                bookList.add(userlog);
            }
            
            manageUserController controller = (manageUserController) createScene1(manageUser, 
                "/main/sources/manageUserView.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    @FXML
    private void handleInterfaceuser() {
        createScene(interfaceUser, "/main/sources/loginUserView.fxml", "/main/sources/css/login_signUser.css");
    }

    @FXML
    private void handleDangxuat() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
            Scene newScene = new Scene(newRoot, 1536, 790);
            newScene.getStylesheets().add(getClass().getResource("/main/sources/css/login.css").toExternalForm());
            // Get the current stage
            Stage stage = (Stage) signout.getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
