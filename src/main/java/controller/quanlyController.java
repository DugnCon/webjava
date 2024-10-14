package main.java.controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Hyperlink;
import main.java.controller.addBookController;
import main.java.model.addNew;
import main.java.JDBC.JDBCSQL;

    public class quanlyController extends baseSceneController {
        @FXML
        private Button home, borrower, payer, user, 
                       employees, addBook, searchBook,
                       deleteBook, updateBook;
        @FXML
        private TextField fieldSearch;
        @FXML
        private VBox searchResultsContainer;
        
        private MultiThreadedAPIController apiController = new MultiThreadedAPIController();


    @FXML
    private void handleHome() {
        createScene(home, "/main/sources/interfaceView.fxml", "/main/sources/css/interface.css");
    }
    
    /**xử lý sự kiện người mượn sách*/
    @FXML
    private void handleBorrower() {
        
    }
    
    /**xử lý sự kiện người trả*/
    @FXML
    private void handlePayer() {
        
    }
    
    /**xử lý sự kiện người dùng*/
    @FXML
    private void handleUser() {
        
    }

    /**xử lý sự kiện nhân viên*/
    @FXML
    private void handleEmployees() {
      
    }
    
    /**xử lý sự kiện thêm sách*/
    @FXML
    private void handleAddBook() {
        try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<addNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
                bookList.add(AddNew);
            }
            
            addBookController controller = (addBookController) createScene1(addBook, 
                "/main/sources/addBookView.fxml", "/main/sources/css/addBook.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    @FXML
    private void initialize() {
    	/**Thiết lập lắng nghe sự kiện để gọi ý kết quả*/
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                suggestBooks(newValue.trim());
            } else {
                searchResultsContainer.getChildren().clear(); // Xóa gợi ý khi không có gì
            }
        });
    }

    private void suggestBooks(String query) {
    	/**Gọi API để gợi ý*/
        apiController.getSuggestionsAsync(query).thenAccept(results -> {
            javafx.application.Platform.runLater(() -> updateSuggestionList(results));
        }).exceptionally(e -> {
            javafx.application.Platform.runLater(() -> {
                searchResultsContainer.getChildren().clear();
                Text errorText = new Text("Có lỗi xảy ra khi lấy gợi ý sách.");
                searchResultsContainer.getChildren().add(errorText);
            });
            e.printStackTrace();
            return null;
        });
    }

    private void updateSuggestionList(JsonArray items) {
    	/**Hủy gợi ý khi không cần thiết*/
        searchResultsContainer.getChildren().clear();
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
            	
            	/**Đường dẫn lấy sách*/
                JsonObject book = items.get(i).getAsJsonObject();
                
                /**Thiết lập để mô tả thông tin về cuốn sách*/
                JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");
                
                /**Biến nó thành 1 chuỗi gửi về tên của sách*/
                String title = volumeInfo.get("title").getAsString();
                
                /**Tạo trường link trong mỗi chuỗi tên sách*/
                String infoLink = volumeInfo.has("infoLink") ? volumeInfo.get("infoLink").getAsString() : "";
                Text bookTitleText = new Text(title);
                bookTitleText.setWrappingWidth(400); 
                
                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                searchResultsContainer.getChildren().add(bookLink);
            }
        } else {
            Text noResults = new Text("Không tìm thấy gợi ý nào.");
            searchResultsContainer.getChildren().add(noResults);
        }
    }

    @FXML
    private void handleSearchBook() {
        String query = fieldSearch.getText().trim(); 
        if (!query.isEmpty()) {
            apiController.searchBooksAsync(query).thenAccept(results -> {
                javafx.application.Platform.runLater(() -> updateBookList(results));
            }).exceptionally(e -> {
                javafx.application.Platform.runLater(() -> {
                    searchResultsContainer.getChildren().clear();
                    Text errorText = new Text("Có lỗi xảy ra khi tìm kiếm sách.");
                    searchResultsContainer.getChildren().add(errorText);
                });
                e.printStackTrace();
                return null;
            });
        }
    }
    
    /**Update danh sách list book*/
    private void updateBookList(JsonArray items) {
        searchResultsContainer.getChildren().clear();
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                JsonObject book = items.get(i).getAsJsonObject();
                JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");
                String title = volumeInfo.get("title").getAsString();
                String infoLink = volumeInfo.has("infoLink") ? volumeInfo.get("infoLink").getAsString() : "";
                Text bookTitleText = new Text(title);
                bookTitleText.setWrappingWidth(400); 
                
                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                searchResultsContainer.getChildren().add(bookLink);
            }
        } else {
            Text noResults = new Text("Không tìm thấy kết quả.");
            searchResultsContainer.getChildren().add(noResults);
        }
    }
    
    /**Tạo lên khi nhấp vào trường sách sẽ ra web mới*/
    private void openWebpage(String urlString) {
        try {
            URI uri = new URI(urlString);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    /**Xóa sách*/
    @FXML
    private void handleDeleteBook() {
    	createScene(deleteBook,"/main/sources/deleteBookView.fxml","/main/sources/css/deleteBook.css");
    }
    
    /**Sửa đổi sách đã thêm*/
    @FXML
    private void handleUpdateBook() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<addNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
                bookList.add(AddNew);
            }
            
            alterBookController controller = (alterBookController) createScene1(addBook, 
            		"/main/sources/alterBookView.fxml", "/main/sources/css/alterBook.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }
}
