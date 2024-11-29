package main.java.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.triviaGame.playmusic;

public class baseSceneController {
	private Scene scene;
	private Parent root;
	private Stage stage;
	
	public void createScene(Node node,String url,String urlCss) {
		try {
			root = FXMLLoader.load(getClass().getResource(url));
			scene = new Scene(root,1536, 800);
			scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
			stage = (Stage) node.getScene().getWindow();
	        stage.setScene(scene);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createSceneGame(Node node,String url,String urlCss, String music) {
		try {
			root = FXMLLoader.load(getClass().getResource(url));
			scene = new Scene(root, 1536, 800);
	        playmusic.initMusicNew(getClass().getResource(music).toString());
	        playmusic.playMusicNew();
	        scene.setOnKeyPressed(event -> {
	            switch (event.getCode()) {
	                case S -> {
	                    playmusic.pauseMusicNew();
	                    System.out.println("Stopping music...");
	                }
	                case D -> {
	                    playmusic.playMusicNew();
	                    System.out.println("Playing music...");
	                }
	            }
	            });
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
            
            controller = loader.getController();
            
            scene = new Scene(root, 1536, 800);
            scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
            stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return controller;
    }
	
	public void createSceneOnTextClick(Text text, String url, String urlCss) {
        text.setOnMouseClicked(event -> {
            try {
                root = FXMLLoader.load(getClass().getResource(url));
                scene = new Scene(root, 1536, 800);
                if (urlCss != null && !urlCss.isEmpty()) {
                    scene.getStylesheets().add(getClass().getResource(urlCss).toExternalForm());
                }
                stage = (Stage) text.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
