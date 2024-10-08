package main.java.controller;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class transistionController {
    public void flyOut(Node node) {
        Scene scene = node.getScene();
        
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.2)); 
        transition.setToY(scene.getHeight());
        
        transition.setOnFinished(event -> {
            StackPane mainPane = (StackPane) scene.getRoot(); 
            mainPane.getChildren().remove(node);
        });
        
        transition.play();
    }

    public void flyIn(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.2));
        transition.setToY(0);
        
        transition.play();
    }
}
