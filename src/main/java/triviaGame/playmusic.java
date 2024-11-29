package main.java.triviaGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class playmusic {
    private MediaPlayer mediaPlayer;
    private static MediaPlayer mediaPlayerNew;
    private static playmusic instance;
    
    public static playmusic getInstance() {
    	if(instance == null) {
    		instance = new playmusic();
    	}
    	return instance;
    }

    public void initMusic(String musicPath) {
         Media media = new Media(musicPath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    public static void initMusicNew(String musicPath) {
        Media media = new Media(musicPath);
       mediaPlayerNew = new MediaPlayer(media);
       mediaPlayerNew.setCycleCount(MediaPlayer.INDEFINITE);
   }
    

    public static void playMusicNew() {
        if (mediaPlayerNew != null) {
            mediaPlayerNew.play();
        } else {
            System.out.println("Music not initialized!");
        }
    }
    
    public void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        } else {
            System.out.println("Music not initialized!");
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        } else {
            System.out.println("Music not initialized!");
        }
    }
    
    public static void pauseMusicNew() {
        if (mediaPlayerNew != null) {
            mediaPlayerNew.pause();
        } else {
            System.out.println("Music not initialized!");
        }
    }
}