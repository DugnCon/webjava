package main.java.controller;

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class transistionController {
    public void flyOut(Node node, Runnable onFinished) {
        Scene scene = node.getScene();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.5));
        transition.setToX(-scene.getWidth());
        
        transition.setOnFinished(event -> {
            StackPane mainPane = (StackPane) scene.getRoot(); 
            mainPane.getChildren().remove(node);
            if (onFinished != null) {
                onFinished.run();
            }
        });
        
        transition.play();
    }

    public void flyIn(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.5));
        transition.setFromX(-node.getScene().getWidth());
        transition.setToX(0);
        
        transition.play();
    }
    
    public void ComeRight(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.5));
        transition.setFromX(0);
        transition.setToX(768);
        
        transition.play();
    }
    
    public void ComeLeft(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.5));
        transition.setFromX(0);
        transition.setToX(-768);
        
        transition.play();
    }
    
    public void ComeRight1(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.5));
        transition.setFromX(-768);
        transition.setToX(0);
        
        transition.play();
    }
    
    public void ComeLeft1(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.5));
        transition.setFromX(768);
        transition.setToX(0);
        
        transition.play();
    }
    
    public void hideWithFade(Node node) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> node.setVisible(false));
        fadeTransition.play();
    }
    public void UnhideWithFade(Node node) {
    	node.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), node);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setOnFinished(event -> node.setVisible(true));
        fadeTransition.play();
    }
    
    public void addButtonZoomEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.2);
        scaleIn.setToY(1.2);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(e -> scaleIn.play()); 
        button.setOnMouseExited(e -> scaleOut.play()); 
    }
    
    public void COMERIGHT(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.1));
        transition.setFromX(-500); 
        transition.setToX(0);
        
        transition.play();
    }
    
    public void COMERIGHT2(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.1));
        transition.setFromX(-100);
        transition.setToX(0);
        
        transition.play();
    }
    
    public void COMERIGHT3(ArrayList<Node> node) {
    	double time  = 1.0d;
        for(int i = 0; i < node.size(); i++) {
        	TranslateTransition tran = new TranslateTransition();
        	tran.setNode(node.get(i));
        	time += 0.3;
        	tran.setDuration(Duration.seconds(time));
        	tran.setFromX(-800);
        	tran.setToX(0);
        	tran.play();
        }
    }
    
    public void COMELEFT2(Node node1, Node node2) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.1));
        transition1.setFromX(500); 
        transition1.setToX(0); 
        
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(2.0));
        transition2.setFromX(600);
        transition2.setToX(0); 
        
        transition1.play();
        transition2.play();
    }
    
    public void COMELEFT(Node node1) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.1));
        transition1.setFromX(500);
        transition1.setToX(0);
        
        transition1.play();
    }
    
    public void COMELEFTARRAY(ArrayList<Node> node) {
    	double time  = 1.0d;
        for(int i = 0; i < node.size(); i++) {
        	TranslateTransition tran = new TranslateTransition();
        	tran.setNode(node.get(i));
        	time += 0.3;
        	tran.setDuration(Duration.seconds(time));
        	tran.setFromX(800);
        	tran.setToX(0);
        	tran.play();
        }
    }
    
    public void COMELEFT1(Node node1, Node node2, Node node3, Node node4, Node node5, Node node6, Node node7, Node node8) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.1));
        transition1.setFromX(500);
        transition1.setToX(0);

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.3));
        transition2.setFromX(500);
        transition2.setToX(0);

        TranslateTransition transition3 = new TranslateTransition();
        transition3.setNode(node3);
        transition3.setDuration(Duration.seconds(1.5));
        transition3.setFromX(500);
        transition3.setToX(0);
        
        TranslateTransition transition4 = new TranslateTransition();
        transition4.setNode(node4);
        transition4.setDuration(Duration.seconds(1.7));
        transition4.setFromX(500);
        transition4.setToX(0);
        
        TranslateTransition transition5 = new TranslateTransition();
        transition5.setNode(node5);
        transition5.setDuration(Duration.seconds(1.9));
        transition5.setFromX(500);
        transition5.setToX(0);

        TranslateTransition transition6 = new TranslateTransition();
        transition6.setNode(node6);
        transition6.setDuration(Duration.seconds(2.1));
        transition6.setFromX(500);
        transition6.setToX(0);

        TranslateTransition transition7 = new TranslateTransition();
        transition7.setNode(node7);
        transition7.setDuration(Duration.seconds(2.3));
        transition7.setFromX(500);
        transition7.setToX(0);

        TranslateTransition transition8 = new TranslateTransition();
        transition8.setNode(node8);
        transition8.setDuration(Duration.seconds(2.5));
        transition8.setFromX(500);
        transition8.setToX(0);

        transition1.play();
        transition2.play();
        transition3.play();
        transition4.play();
        transition5.play();
        transition6.play();
        transition7.play();
        transition8.play();
    }
    
    public void COMEON(Node node) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node);
        transition1.setDuration(Duration.seconds(1.1));
        transition1.setFromY(-500);
        transition1.setToY(0);
        transition1.play();
    }
    
    public void COMEONARRAY(ArrayList<Node> node) {
    	double time  = 1.0d;
        for(int i = 0; i < node.size(); i++) {
        	TranslateTransition tran = new TranslateTransition();
        	tran.setNode(node.get(i));
        	time += 0.3;
        	tran.setDuration(Duration.seconds(time));
        	tran.setFromY(-300);
        	tran.setToY(0);
        	tran.play();
        }
    }
    
    public void COMEONARRAY2(ArrayList<Node> node) {
    	double time  = 1.0d;
        for(int i = 0; i < node.size(); i++) {
        	TranslateTransition tran = new TranslateTransition();
        	tran.setNode(node.get(i));
        	time += 0.2;
        	tran.setDuration(Duration.seconds(time));
        	tran.setFromY(-800);
        	tran.setToY(0);
        	tran.play();
        }
    }
    
    public void COMEONALL2(Node node1, Node node2) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.2));
        transition1.setFromY(-100);
        transition1.setToY(0);
        
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.4));
        transition2.setFromY(-100); 
        transition2.setToY(0); 
        
        transition1.play();
        transition2.play();
    }
    
    public void COMEONALL1(Node node1, Node node2, Node node3) {
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.7));
        transition1.setFromY(-500); 
        transition1.setToY(0); 
        
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.9));
        transition2.setFromY(-500); 
        transition2.setToY(0);
        
        TranslateTransition transition3 = new TranslateTransition();
        transition3.setNode(node3);
        transition3.setDuration(Duration.seconds(2.1));
        transition3.setFromY(-500);
        transition3.setToY(0);
        
        transition1.play();
        transition2.play();
        transition3.play();
    }
    
    public void COMEONALL(Node node1, Node node2, Node node3) {

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(1.1));
        transition1.setFromY(-500);
        transition1.setToY(0);


        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.3));
        transition2.setFromY(-500);
        transition2.setToY(0);


        TranslateTransition transition3 = new TranslateTransition();
        transition3.setNode(node3);
        transition3.setDuration(Duration.seconds(1.5));
        transition3.setFromY(-500);
        transition3.setToY(0);


        transition1.play();
        transition2.play();
        transition3.play();
    }
    
    public void COMERIGHTALL(Node node1, Node node2, Node node3, Node node4, Node node5) {

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(0.8));
        transition1.setFromX(-100);
        transition1.setToX(0);

    
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.0));
        transition2.setFromX(-100);
        transition2.setToX(0);

       
        TranslateTransition transition3 = new TranslateTransition();
        transition3.setNode(node3);
        transition3.setDuration(Duration.seconds(1.2));
        transition3.setFromX(-100);
        transition3.setToX(0);
        
        TranslateTransition transition4 = new TranslateTransition();
        transition4.setNode(node4);
        transition4.setDuration(Duration.seconds(1.5));
        transition4.setFromX(-100);
        transition4.setToX(0);
        
        TranslateTransition transition5 = new TranslateTransition();
        transition5.setNode(node5);
        transition5.setDuration(Duration.seconds(1.7));
        transition5.setFromX(-100);
        transition5.setToX(0);


        transition1.play();
        transition2.play();
        transition3.play();
        transition4.play();
        transition5.play();
    }
    
    public void COMERIGHTALL2(Node node1, Node node2, Node node3, Node node4, Node node5, Node node6, Node node7) {

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(node1);
        transition1.setDuration(Duration.seconds(0.8));
        transition1.setFromX(-100);
        transition1.setToX(0);


        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(node2);
        transition2.setDuration(Duration.seconds(1.0));
        transition2.setFromX(-100);
        transition2.setToX(0);


        TranslateTransition transition3 = new TranslateTransition();
        transition3.setNode(node3);
        transition3.setDuration(Duration.seconds(1.2));
        transition3.setFromX(-100);
        transition3.setToX(0);
        

        TranslateTransition transition4 = new TranslateTransition();
        transition4.setNode(node4);
        transition4.setDuration(Duration.seconds(1.5));
        transition4.setFromX(-100);
        transition4.setToX(0);
        

        TranslateTransition transition5 = new TranslateTransition();
        transition5.setNode(node5);
        transition5.setDuration(Duration.seconds(1.7));
        transition5.setFromX(-100);
        transition5.setToX(0);


        TranslateTransition transition6 = new TranslateTransition();
        transition6.setNode(node6);
        transition6.setDuration(Duration.seconds(1.9));
        transition6.setFromX(-100);
        transition6.setToX(0);


        TranslateTransition transition7 = new TranslateTransition();
        transition7.setNode(node7);
        transition7.setDuration(Duration.seconds(2.1));
        transition7.setFromX(-100);
        transition7.setToX(0);

 
        transition1.play();
        transition2.play();
        transition3.play();
        transition4.play();
        transition5.play();
        transition6.play();
        transition7.play();
    }

    
    public void COMEUNDER3(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.7));
        transition.setFromY(800); 
        transition.setToY(0);
        transition.play();
    }
    
    public void COMEUNDER1(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.0));
        transition.setFromY(800); 
        transition.setToY(0); 
        
        transition.play();
    }
    
    public void COMEUNDER2(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(1.5));
        transition.setFromY(800); 
        transition.setToY(0); 
        
        transition.play();
    }

}