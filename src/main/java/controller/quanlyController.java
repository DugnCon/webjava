package main.java.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.model.addNew;

public class quanlyController extends baseSceneController {
    @FXML
    private Button home, searchBook;
    @FXML
    private Button borrower, payer, user, 
                   employees, addBook,
                   deleteBook, updateBook;
    @FXML
    private TextField fieldSearch;
    @FXML
    private VBox searchResultsContainer;
    @FXML
    private ProgressIndicator loadingIndicator;
    @FXML
    private StackPane rootContainer;
    @FXML
    private BorderPane mainContent;
    @FXML
    private ImageView image;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private Label label;


    private APIController apiController = new APIController();
    private ScheduledExecutorService scheduler;
    private Runnable searchTask;
   
    
    private void addButtonZoomEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), button);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), button);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(e -> scaleIn.play()); 
        button.setOnMouseExited(e -> scaleOut.play()); 
    }
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
    
    /**Xóa sách*/
    @FXML
    private void handleDeleteBook() {
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
            
            deleteBookController controller = (deleteBookController) createScene1(deleteBook, 
            		"/main/sources/deleteBookView.fxml", "/main/sources/css/deleteBook.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
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
    	
        scheduler = Executors.newSingleThreadScheduledExecutor();
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                scheduleSearch(newValue.trim());
            } else {
                searchResultsContainer.getChildren().clear();
            }
        });
        
        addButtonZoomEffect(home);
        addButtonZoomEffect(borrower);
        addButtonZoomEffect(payer);
        addButtonZoomEffect(employees);
        addButtonZoomEffect(user);
        transistionController tran = new transistionController();
        tran.COMEONALL1(searchBook,fieldSearch,image);
        tran.COMEONALL(addBook,deleteBook,updateBook);
        tran.COMEUNDER1(scrollpane);
        tran.COMEUNDER3(label);
        tran.COMEUNDER2(searchResultsContainer);
        tran.COMERIGHTALL(home,borrower,payer,user,employees);
    }

    private void scheduleSearch(String query) {
        if (searchTask != null) {
            scheduler.shutdownNow(); 
        }
        
        searchTask = () -> {
            try {
                JsonArray results = apiController.getSuggestions(query);
                Platform.runLater(() -> updateSuggestionList(results));
            } catch (IOException | InterruptedException e) {
                Platform.runLater(() -> {
                    searchResultsContainer.getChildren().clear();
                    Text errorText = new Text("Có lỗi xảy ra khi lấy gợi ý sách.");
                    searchResultsContainer.getChildren().add(errorText);
                });
                e.printStackTrace();
            }
        };

        scheduler.schedule(searchTask, 500, TimeUnit.MILLISECONDS);
    }

    private void updateSuggestionList(JsonArray items) {
        searchResultsContainer.getChildren().clear();
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                JsonObject book = items.get(i).getAsJsonObject();
                JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");

                String title = volumeInfo.get("title").getAsString();
                String infoLink = volumeInfo.has("infoLink") ? volumeInfo.get("infoLink").getAsString() : "";
                String thumbnailUrl = volumeInfo.has("imageLinks") && volumeInfo.getAsJsonObject("imageLinks").has("thumbnail")
                        ? volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString() : null;

                HBox bookEntry = new HBox(10); 
                bookEntry.setAlignment(Pos.CENTER_LEFT);

                if (thumbnailUrl != null) {
                    ImageView thumbnail = new ImageView(new Image(thumbnailUrl, 50, 75, true, true));
                    bookEntry.getChildren().add(thumbnail);
                }

                Text bookTitleText = new Text(title);
                bookTitleText.setWrappingWidth(300);

                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                bookEntry.getChildren().add(bookLink);
                searchResultsContainer.getChildren().add(bookEntry);
            }
        } else {
            Text noResults = new Text("Không tìm thấy gợi ý nào.");
            searchResultsContainer.getChildren().add(noResults);
        }
    }



    @FXML
    private void handleSearchBook() {
        String query = fieldSearch.getText().trim();
        if (query.isEmpty()) {
            loadingIndicator.setVisible(false);
            mainContent.setEffect(null); 
            return;
        }

        loadingIndicator.setVisible(true);

        GaussianBlur blurEffect = new GaussianBlur(10);
        mainContent.setEffect(blurEffect);

        Task<JsonArray> searchTask = new Task<JsonArray>() {
            @Override
            protected JsonArray call() throws Exception {
                return apiController.searchBooks(query, 10);
            }

            @Override
            protected void succeeded() {
                JsonArray results;
                try {
                    results = get();
                    updateResults(results);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    loadingIndicator.setVisible(false);
                    mainContent.setEffect(null);
                }
            }

            @Override
            protected void failed() {
                loadingIndicator.setVisible(false);
                mainContent.setEffect(null);
                Platform.runLater(() -> {
                    Text errorText = new Text("Có lỗi xảy ra khi tìm kiếm.");
                    searchResultsContainer.getChildren().add(errorText);
                });
            }
        };

        new Thread(searchTask).start();
    }


    private void updateResults(JsonArray results) {
        searchResultsContainer.getChildren().clear();

        if (results != null && results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                JsonObject book = results.get(i).getAsJsonObject();
                JsonObject volumeInfo = book.getAsJsonObject("volumeInfo");

                String title = volumeInfo.get("title").getAsString();
                String infoLink = volumeInfo.has("infoLink") ? volumeInfo.get("infoLink").getAsString() : "";
                String thumbnailUrl = volumeInfo.has("imageLinks") && volumeInfo.getAsJsonObject("imageLinks").has("thumbnail")
                        ? volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString() : null;

                HBox bookEntry = new HBox(10);
                bookEntry.setAlignment(Pos.TOP_CENTER);

                if (thumbnailUrl != null) {
                    ImageView thumbnail = new ImageView(new Image(thumbnailUrl, 50, 75, true, true));
                    bookEntry.getChildren().add(thumbnail);
                }

                Text bookTitleText = new Text(title);
                bookTitleText.setWrappingWidth(300);

                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                bookEntry.getChildren().add(bookLink);
                searchResultsContainer.getChildren().add(bookEntry);
            }
        } else {
            Text noResults = new Text("Không tìm thấy kết quả.");
            searchResultsContainer.getChildren().add(noResults);
        }
    }



    private void openWebpage(String urlString) {
        try {
            if (urlString != null && !urlString.trim().isEmpty()) {
                URI uri = new URI(urlString);
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Liên kết không hợp lệ: " + urlString);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
