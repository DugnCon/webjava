package main.java.controller;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class transistionController {
    // Di chuyển node ra khỏi màn hình
    public void flyOut(Node node, Runnable onFinished) {
        Scene scene = node.getScene();
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.5));
        transition.setToX(-scene.getWidth()); // Di chuyển ra bên trái
        
        transition.setOnFinished(event -> {
            StackPane mainPane = (StackPane) scene.getRoot(); 
            mainPane.getChildren().remove(node);
            if (onFinished != null) {
                onFinished.run();
            }
        });
        
        transition.play();
    }

    // Di chuyển node vào màn hình
    public void flyIn(Node node) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(node);
        transition.setDuration(Duration.seconds(0.5));
        transition.setFromX(-node.getScene().getWidth()); // Bắt đầu bên ngoài màn hình
        transition.setToX(0); // Đến vị trí gốc
        
        transition.play();
    }
    
    public void switchToSignup(Node fromPane, Node toPane) {
        // Di chuyển fromPane sang trái
        TranslateTransition transitionOut = new TranslateTransition(Duration.millis(300), fromPane);
        transitionOut.setByX(-fromPane.getBoundsInLocal().getWidth());
        
        // Di chuyển toPane vào màn hình từ bên phải
        toPane.setVisible(true); // Đảm bảo toPane được hiển thị trước khi di chuyển
        toPane.setTranslateX(toPane.getBoundsInLocal().getWidth()); // Đặt toPane bên ngoài màn hình
        TranslateTransition transitionIn = new TranslateTransition(Duration.millis(300), toPane);
        transitionIn.setByX(-toPane.getBoundsInLocal().getWidth());

        // Ẩn fromPane khi hoàn thành di chuyển
        transitionOut.setOnFinished(event -> {
            fromPane.setVisible(false);
            transitionIn.play();
        });
        
        transitionOut.play();
    }

    public void switchToLogin(Node fromPane, Node toPane) {
        // Di chuyển fromPane sang phải
        TranslateTransition transitionOut = new TranslateTransition(Duration.millis(300), fromPane);
        transitionOut.setByX(fromPane.getBoundsInLocal().getWidth());
        
        // Di chuyển toPane vào màn hình từ bên trái
        toPane.setVisible(true); // Đảm bảo toPane được hiển thị trước khi di chuyển
        toPane.setTranslateX(-toPane.getBoundsInLocal().getWidth()); // Đặt toPane bên ngoài màn hình
        TranslateTransition transitionIn = new TranslateTransition(Duration.millis(300), toPane);
        transitionIn.setByX(toPane.getBoundsInLocal().getWidth());

        // Ẩn fromPane khi hoàn thành di chuyển
        transitionOut.setOnFinished(event -> {
            fromPane.setVisible(false);
            transitionIn.play();
        });
        
        transitionOut.play();
    }
}
