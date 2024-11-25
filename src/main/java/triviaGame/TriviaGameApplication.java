package main.java.triviaGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class TriviaGameApplication extends Application {
    protected Scene scene;
    protected Parent root;
    protected Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/main/sources/Trivia.fxml"));
        scene = new Scene(root, 1536, 790);
        primaryStage.setTitle("Game");
        playmusic.initMusic(getClass().getResource("/music.mp3").toString());
        playmusic.playMusic();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S -> {
                    playmusic.pauseMusic();
                    System.out.println("Stopping music...");
                }
                case D -> {
                    playmusic.playMusic();
                    System.out.println("Playing music...");
                }
            }
            });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}