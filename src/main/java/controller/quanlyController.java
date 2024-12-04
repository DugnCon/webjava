package main.java.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.Image;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;
import main.java.model.add;
import main.java.model.addNew;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.userLog;

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
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM borrower");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<borrowNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                borrowNew borrownew = new borrowNew(rs.getString(3), rs.getString(8), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                bookList.add(borrownew);
            }
            
            borrowBookController controller = (borrowBookController) createScene1(borrower, 
                "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
            controller.setBookList(bookList);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }
    
    /**xử lý sự kiện người trả*/
    @FXML
    private void handlePayer() {
    	createScene(payer, "/main/sources/returnBookView.fxml", "/main/sources/css/returnBook.css");
    }
    
    /**xử lý sự kiện người dùng*/
    @FXML
    private void handleUser() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<userLog> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                userLog userlog = new userLog(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5));
                bookList.add(userlog);
            }
            
            manageUserController controller = (manageUserController) createScene1(user, 
                "/main/sources/manageUserView.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    /**xử lý sự kiện nhân viên*/
    @FXML
    private void handleEmployees() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM request");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<requireNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                requireNew require = new requireNew(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4));
                bookList.add(require);
            }
            
            manageRequestController controller = (manageRequestController) createScene1(employees, 
                "/main/sources/manageRequest.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
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
	
    @FXML
    private void handleSearchBook() {
        String query = fieldSearch.getText().trim();
        if (query.isEmpty()) {
            loadingIndicator.setVisible(false);
            mainContent.setEffect(null); 
            searchResultsContainer.setVisible(false);
            return;
        }

        loadingIndicator.setVisible(true);

        GaussianBlur blurEffect = new GaussianBlur(10);
        mainContent.setEffect(blurEffect);

        searchResultsContainer.setVisible(false);

        Task<JsonArray> searchTask = new Task<JsonArray>() {
            @Override
            protected JsonArray call() throws Exception {
            	Thread.sleep(200);
                return apiController.searchBooks(query, 40);
            }

            @Override
            protected void succeeded() {
                JsonArray results;
                try {
                    results = get();
                    updateResults(results);

                    Platform.runLater(() -> {
                        searchResultsContainer.setVisible(true);
                        //loadingIndicator.setVisible(false);
                    });
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    Platform.runLater(() -> {
                        loadingIndicator.setVisible(false);
                        mainContent.setEffect(null);
                    });
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

                String title = getStringValue(volumeInfo, "title");
                String infoLink = getStringValue(volumeInfo, "infoLink", "");
                String thumbnailUrl = getThumbnailUrl(volumeInfo);
                String authors = getAuthors(volumeInfo);
                String description = getStringValue(volumeInfo, "description", "No description available");
                String publishedDate = getStringValue(volumeInfo, "publishedDate", "Unknown publication date");
                String ratings = getStringValue(volumeInfo, "averageRating", "No ratings available");
                String publisher = getStringValue(volumeInfo, "publisher", "Unknown publisher");
                String categories = getCategories(volumeInfo);

                HBox bookEntry = createBookEntry(title, thumbnailUrl);
                VBox bookDetails = createBookDetails(title, infoLink, thumbnailUrl,authors, publisher, categories, description, publishedDate, ratings);

                bookEntry.getChildren().add(bookDetails);
                searchResultsContainer.getChildren().add(bookEntry);
            }
        } else {
            displayNoResultsMessage();
        }
    }

    private String getStringValue(JsonObject volumeInfo, String key) {
        return getStringValue(volumeInfo, key, "Unknown");
    }

    private String getStringValue(JsonObject volumeInfo, String key, String defaultValue) {
        return volumeInfo.has(key) ? volumeInfo.get(key).getAsString() : defaultValue;
    }

    private String getThumbnailUrl(JsonObject volumeInfo) {
        if (volumeInfo.has("imageLinks") && volumeInfo.getAsJsonObject("imageLinks").has("thumbnail")) {
            return volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString();
        }
        return null;
    }
    private String getAuthors(JsonObject volumeInfo) {
        return volumeInfo.has("authors") ? volumeInfo.get("authors").toString().replace("[", "").replace("]", "") : "Unknown author";
    }

    private String getCategories(JsonObject volumeInfo) {
        return volumeInfo.has("categories") ? volumeInfo.get("categories").toString().replace("[", "").replace("]", "") : "No categories available";
    }

    private HBox createBookEntry(String title, String thumbnailUrl) {
        HBox bookEntry = new HBox(10);
        bookEntry.setAlignment(Pos.TOP_CENTER);
        bookEntry.setStyle("-fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");

        if (thumbnailUrl != null) {
            ImageView thumbnail = new ImageView();
            
            Task<Image> loadImageTask = new Task<Image>() {
                @Override
                protected Image call() throws Exception {
                    return new Image(thumbnailUrl, 200, 350, true, true); 
                }
            };

            loadImageTask.setOnSucceeded(event -> {
                Platform.runLater(() -> thumbnail.setImage(loadImageTask.getValue()));
            });

            new Thread(loadImageTask).start(); 

            bookEntry.getChildren().add(thumbnail);
        }

        return bookEntry;
    }


    private VBox createBookDetails(String title, String infoLink, String thumbnailUrl, String authors,String publisher, String categories, String description, String publishedDate, String ratings) {
        VBox bookDetails = new VBox(20);
        bookDetails.setAlignment(Pos.CENTER);

        Text bookTitleText = new Text(title);
        bookTitleText.setStyle("-fx-font-size:20px;");
        bookTitleText.setWrappingWidth(250);

        Hyperlink bookLink = new Hyperlink();
        bookLink.setGraphic(bookTitleText);
        bookLink.setOnAction(event -> openWebpage(infoLink));

        Text authorText = new Text("Authors: " + authors);
        Text publisherText = new Text("Publisher: " + publisher);
        Text categoriesText = new Text("Categories: " + categories);

        Text descriptionText = new Text();
        Button seeMoreButton = new Button("View Details");
        seeMoreButton.getStyleClass().add("buttonAdd");

        if (description.length() > 200) {
            descriptionText.setText("Description: " + description.substring(0, 200) + "...");
            seeMoreButton.setOnAction(event -> showDetailedPopup(title, thumbnailUrl, authors, publishedDate, ratings, description, publisher, categories));
        } else {
            descriptionText.setText("Description: " + description);
            seeMoreButton.setVisible(false);
        }

        descriptionText.setWrappingWidth(300);

        Text publishedDateText = new Text("Publication date: " + publishedDate);
        Text ratingsText = new Text("Ratings: " + ratings);

        bookDetails.getChildren().addAll(bookLink, authorText, publisherText, categoriesText, descriptionText, publishedDateText, ratingsText, seeMoreButton);

        return bookDetails;
    }
    private void displayNoResultsMessage() {
        Text noResults = new Text("No results found.");
        noResults.setStyle("-fx-font-size: 16px; -fx-fill: #999;");
        searchResultsContainer.getChildren().add(noResults);
    }
    private void showDetailedPopup(String title, String thumbnailUrl, String authors, String publishedDate, String ratings, String description, String publisher, String categories) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Thông tin chi tiết");

        VBox popupContent = new VBox(10);
        popupContent.setPadding(new Insets(10));
        popupContent.setAlignment(Pos.TOP_CENTER);
        
        Text popupTitle = new Text(title);
        popupTitle.setStyle("-fx-font-size: 30px;");
        popupTitle.setWrappingWidth(400);

        ImageView thumbnailImage = new ImageView();
        Task<Image> loadImageTask = new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                return new Image(thumbnailUrl, 200, 300, true, true);
            }
        };

        loadImageTask.setOnSucceeded(event -> {
            thumbnailImage.setImage(loadImageTask.getValue());
        });

        new Thread(loadImageTask).start();
        
        Text authorText = new Text("Authors: " + authors);
        Text publisherText = new Text("Publisher: " + publisher);
        Text categoriesText = new Text("Categories: " + categories);
        Text publishedDateText = new Text("Publication date: " + publishedDate);
        Text ratingsText = new Text("Rating: " + ratings);

        Text fullDescription = new Text(description);
        fullDescription.setWrappingWidth(400);

        // ScrollPane cho phần mô tả
        ScrollPane scrollPane = new ScrollPane(fullDescription);
        scrollPane.setPrefWidth(450);
        scrollPane.setPrefHeight(300);
        scrollPane.setPadding(new Insets(20));
        scrollPane.setStyle("-fx-border-radius:10; -fx-background-radius:10; -fx-border-color:black;");

        // Nút "Add Book"
        Button add = new Button("Add Book");
        add.setStyle("-fx-pref-width:130; -fx-pref-height:40; -fx-background-radius:45; -fx-border-radius:45; -fx-font-size:12; -fx-font-weight:bold; -fx-text-fill:#2B579A; -fx-border-color:#2B579A; -fx-border-style:solid; -fx-border-width:2; -fx-effect: dropshadow(gaussian, #2b579a, 0, 1, 5, 5);");
        add.getStyleClass().add("buttonAdd");

        add.setOnAction(event -> showDetailsAddBook(title, thumbnailUrl, authors, publishedDate, ratings, description, publisher, categories));

        VBox layout = new VBox(10, popupTitle);
        if (thumbnailImage != null) {
            layout.getChildren().add(thumbnailImage);  // Thêm ảnh vào layout
            layout.setAlignment(Pos.CENTER_LEFT);
        }
        layout.getChildren().addAll(authorText, publishedDateText, publisherText, categoriesText, ratingsText, add);
        layout.setAlignment(Pos.CENTER_LEFT);

        HBox lay = new HBox(10);
        lay.getChildren().addAll(layout, scrollPane);
        lay.setAlignment(Pos.TOP_CENTER);
        lay.setPadding(new Insets(15));

        ScrollPane pane = new ScrollPane();
        pane.setContent(lay);

        Scene popupScene = new Scene(pane, 1000, 600);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
    }
    public void showDetailsAddBook(String title, String thumbnailUrl, String authors, String publishedDate, String ratings, String description,String publisher, String categories) {
    	TextField nameBook, chapBook, Publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity;
    	
    	 nameBook = new TextField();
         chapBook = new TextField();
         Publisher = new TextField();
         releaseYear = new TextField();
         nameAuthor = new TextField();
         styleBook = new TextField();
         bookCode = new TextField();
         quantity = new TextField();
         
         bookCode.setPromptText("Mã sách");
         quantity.setPromptText("Số lượng");
         chapBook.setPromptText("Chương sách");
         
     	VBox layall = new VBox(20);
        layall.getChildren().addAll(bookCode, nameBook, chapBook, nameAuthor, styleBook, Publisher, releaseYear, quantity);
        layall.setPadding(new Insets(40));
         
         Button record = new Button("Record");
         record.setStyle("-fx-pref-width:130;\r\n"
          		+ "	-fx-pref-height:40;\r\n"
          		+ "	-fx-background-radius:45;\r\n"
          		+ "	-fx-border-radius:45;\r\n"
          		+ "	-fx-font-size:12;\r\n"
          		+ "	-fx-font-weight:bold;\r\n"
          		+ "	-fx-text-fill:#2B579A;\r\n"
          		+ "	-fx-border-color:#2B579A;\r\n"
          		+ "	-fx-border-style:solid;\r\n"
          		+ "	-fx-border-width:2;\r\n"
          		+ "	-fx-effect: dropshadow(gaussian, #2b579a, 0, 1, 5, 5);");
         
         record.getStyleClass().add("buttonAdd");
         
        nameBook.setText(title);
 	    nameAuthor.setText(authors);
 	    styleBook.setText(categories);
 	    Publisher.setText(publisher);
 	    releaseYear.setText(publishedDate);

 	    List<TextField> textFields = Arrays.asList(nameBook, chapBook, Publisher, releaseYear, nameAuthor, styleBook, bookCode, quantity);

 	    String commonStyle = "-fx-pref-width:500;\r\n"
 	            + " -fx-pref-height:40;\r\n"
 	            + " -fx-border-color:#168560;\r\n"
 	            + " -fx-border-radius:20;\r\n"
 	            + " -fx-background-radius:20;\r\n"
 	            + " -fx-border-color:#2B579A;\r\n"
 	            + " -fx-border-width:2;\r\n"
 	            + " -fx-text-fill:black;\r\n"
 	            + " -fx-effect: dropshadow(gaussian, #2b579a, 0, 1, 5, 5);";

 	    for (TextField textField : textFields) {
 	        textField.setStyle(commonStyle);
 	    }
 	    
 	    Label newLabel = new Label("Thông tin chi tiết sách");
 	    newLabel.setStyle("-fx-text-fill:#2B579A; -fx-font-size:25;-fx-font-weight:bold;");

 	    VBox newLayall = new VBox(20);
 	    
 	    newLayall.getChildren().add(newLabel);

 	    List<TextField> newTextFields = Arrays.asList(
 	        bookCode,
 	        nameBook,
 	        chapBook,
 	        nameAuthor,
 	        styleBook,
 	        Publisher,
 	        releaseYear,
 	        quantity
 	    );

 	    for (TextField newTextField : newTextFields) {
 	        newTextField.setStyle(commonStyle);
 	        newLayall.getChildren().addAll(newTextField);
 	    }
 	    newLayall.getChildren().add(record);
 	    newLayall.setAlignment(Pos.CENTER);  

 	    double time = 0.5;
 	    for (TextField textField : newTextFields) {
 	        TranslateTransition transition = new TranslateTransition();
 	        transition.setNode(textField);
 	        transition.setDuration(Duration.seconds(1.0 + time));
 	        transition.setFromY(-400);
 	        transition.setToY(0); 
 	        transition.setCycleCount(1); 
 	        transition.play();
 	        time += 0.2;
 	    }
 	    

 	     record.setOnAction(event -> {
 	    	    if (bookCode.getText().isEmpty() || chapBook.getText().isEmpty() || quantity.getText().isEmpty()) {
 	    	        alertController.setNew().AlertUnComplete(
 	    	            "Thiếu thông tin sách."
 	    	        );
 	    	    } else {

 	    	        add Add = new add(
 	    	            bookCode.getText(),
 	    	            nameBook.getText(),
 	    	            chapBook.getText(),
 	    	            nameAuthor.getText(),
 	    	            styleBook.getText(),
 	    	            Publisher.getText(),
 	    	            releaseYear.getText(),
 	    	            quantity.getText()
 	    	        );
 	    	        
 	    	        add SearchAdd = new add(bookCode.getText());
 	    	        String Code = addbook.setNewAdd().search(SearchAdd);
 	    	        if(Code.isEmpty()) {
 	    	        	int res = addbook.setNewAdd().insert1(Add);
 	    	        	if (res > 0) {
 	 	    	        	bookCode.clear();
 	 	    	            nameBook.clear();
 	 	    	            chapBook.clear();
 	 	    	            nameAuthor.clear();
 	 	    	            styleBook.clear();
 	 	    	            Publisher.clear();
 	 	    	            releaseYear.clear();
 	 	    	            quantity.clear();
 	 	    	            alertController.setNew().AlertComplete("Thêm sách thành công");
 	 	    	        } else {
 	 	    	            alertController.setNew().AlertUnComplete("Thêm sách không thành công");
 	 	    	        }
 	    	        }else {
 	    	        	alertController.setNew().AlertUnComplete("Sách này đã được thêm");
 	    	        }
 	    	    }
 	    	});

 	    newLayall.setPadding(new Insets(40));

 	    Stage popupStageNew = new Stage();
 	    popupStageNew.initModality(Modality.APPLICATION_MODAL);
 	    popupStageNew.setTitle("Thông tin chi tiết");

 	    Scene scene = new Scene(newLayall, 500, 600);
 	    popupStageNew.setScene(scene);
 	    popupStageNew.showAndWait();
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