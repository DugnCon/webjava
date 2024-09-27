module WebJava {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

    opens main.java.controller to javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
}
