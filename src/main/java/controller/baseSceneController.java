package main.java.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class baseSceneController {
	private Scene scene;
	private Parent root;
	private Stage stage;
	public void createScene(Node node,String url,String urlCss) {
		try {
			root = FXMLLoader.load(getClass().getResource(url));
			scene = new Scene(root,1536, 790);
			scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
			stage = (Stage) node.getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected <T> T createScene1(Node node, String url, String urlCss) {
        T controller = null;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            root = loader.load();
            
            // Lấy controller từ FXMLLoader
            controller = loader.getController();
            
            scene = new Scene(root, 1536, 790);
            scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
            stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return controller; // Trả về controller
    }
}
