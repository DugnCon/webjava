package main.java.controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Hyperlink;

    public class quanlyController extends baseSceneController {
        @FXML
        private Button home, borrower, payer, user, employees, addBook, searchBook;
        @FXML
        private TextField fieldSearch;
        @FXML
        private VBox searchResultsContainer;

        private MultiThreadedAPIController apiController = new MultiThreadedAPIController(); // Khởi tạo MultiThreadedAPIController


    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }

    @FXML
    private void handleBorrower() {
        // Implement logic for borrower
    }

    @FXML
    private void handlePayer() {
        // Implement logic for payer
    }

    @FXML
    private void handleUser() {
        // Implement logic for user
    }

    @FXML
    private void handleEmployees() {
        // Implement logic for employees
    }

    @FXML
    private void handleAddBook() {
        createScene(addBook, "/main/sources/addBookView.fxml", "/main/sources/css/addBook.css");
    }
    @FXML
    private void handleSearchBook() {
        String query = fieldSearch.getText();
        if (!query.isEmpty()) {
            apiController.searchBooks(query).thenAccept(results -> {
                javafx.application.Platform.runLater(() -> updateBookList(results));
            }).exceptionally(e -> {
                e.printStackTrace(); // Xử lý lỗi
                return null;
            });
        }
    }

    private void updateBookList(JsonArray items) {
        searchResultsContainer.getChildren().clear(); // Xóa kết quả cũ

        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                JsonObject book = items.get(i).getAsJsonObject();
                JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");
                String title = volumeInfo.get("title").getAsString();
                String infoLink = volumeInfo.has("infoLink") ? volumeInfo.get("infoLink").getAsString() : "";

                // Tạo Hyperlink cho tiêu đề sách
                Hyperlink bookLink = new Hyperlink(title);
                bookLink.setOnAction(event -> openWebpage(infoLink)); // Mở liên kết khi nhấp vào

                // Thêm Hyperlink vào giao diện
                searchResultsContainer.getChildren().add(bookLink);
            }
        } else {
            Text noResults = new Text("Không tìm thấy kết quả.");
            searchResultsContainer.getChildren().add(noResults);
        }
    }

    private void openWebpage(String urlString) {
        try {
            URI uri = new URI(urlString);
            Desktop.getDesktop().browse(uri); // Mở URL trong trình duyệt mặc định
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
    }
}
