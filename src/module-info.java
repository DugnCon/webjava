<<<<<<< HEAD
module WebJava {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires mysql.connector.j;
	requires java.net.http;
	requires com.google.gson;
	requires java.desktop;
	requires javafx.media;

    opens main.java.controller to javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
	opens main.java.model to javafx.base;
}
=======
module WebJava {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

    opens main.java.controller to javafx.fxml;
	opens main to javafx.graphics, javafx.fxml;
}
>>>>>>> d0e13a88e8dcc7379032e2c55cda6ea8f443d06e
