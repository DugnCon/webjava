package main.java.controller;

import javafx.scene.Node;
import main.java.controller.transistionController;
public class AppController {
    private baseSceneController baseSceneController;
    private transistionController transistionController;

    public AppController() {
        baseSceneController = new baseSceneController();
        transistionController = new transistionController();
    }
    
    public static AppController getAppController() {
    	return new AppController();
    }

    public void createScene(Node node, String url, String urlCss) {
        baseSceneController.createScene(node, url, urlCss);
    }

    public void flyOut(Node node) {
        transistionController.flyOut(node);
    }

    public void flyIn(Node node) {
        transistionController.flyIn(node);
    }
}
