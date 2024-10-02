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
			scene = new Scene(root,1200,780);
			scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
			stage = (Stage) node.getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
