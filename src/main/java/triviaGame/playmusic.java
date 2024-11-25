package main.java.triviaGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class playmusic {
    private static MediaPlayer mediaPlayer;

    // Initialize music
    public static void initMusic(String musicPath) {
         Media media = new Media(musicPath); // Use the file path of the music file
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);}

    // Play the music
    public static void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        } else {
            System.out.println("Music not initialized!");
        }
    }

    // Pause the music
    public static void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        } else {
            System.out.println("Music not initialized!");
        }
    }
}