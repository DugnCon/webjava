package main;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    	// /main/sources/interfaceUser.fxml
    	// /main/sources/css/interfaceUser.css
    	// /main/sources/loginView.fxml
    	// /main/sources/css/login.css
        Parent root = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
        Scene scene = new Scene(root, 1536, 790);
        scene.getStylesheets().add(getClass().getResource("/main/sources/css/login.css").toExternalForm());
        primaryStage.setTitle("FXML Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
