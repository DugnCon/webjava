package main.java.controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.dao.addbook;
import main.java.dao.borrowbook;
import main.java.dao.borrowedBooks;
import main.java.dao.requireUser;
import main.java.dao.userLoginAccount;
import main.java.model.add;
import main.java.model.addNew;
import main.java.model.alter;
import main.java.model.borrow;
import main.java.model.borrowNew;
import main.java.model.require;
import main.java.model.userLog;
public class requestUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label5,label6,yourID,lab1,lab2;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4,vboxnew;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,
	img9,img10,img11,img_1,img_2,img_3,img_4,img_5,image,img_6,img_7,img_8,img_9
	,img_10,img22,img33,img44,img55,img66,img77,img88,img99,img1010,img111,img1001,img1002;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3,complete,comlete1,searchID,search;
	@FXML
	private TextField fieldSearch,username,textReturn;
	@FXML
	private TextField bookCode,borrowerID,userName
	,phone,borrowDate,returnDate,status,searchAccount
	,userID,Title,chapter,author,quantity;
	@FXML
	private Button searchBook,clear,loading;
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private VBox searchResultsContainer,para,yourComment;
	@FXML
	private ProgressIndicator loadingIndicator;
	@FXML
	private Text text1,text2,lb1,lb2,title,title2
	,paragraph,note,yourName,comment;
	@FXML
    private BorderPane mainContent;
	@FXML
	private HBox hboxnew;
	@FXML
	private TextArea textarea;
	@FXML
	private TableView<addNew> tableBook;
	@FXML
	private TableView<borrowNew> tableBorrowBook;
	@FXML
	private TableColumn<borrowNew, String> columnUserID1, columnCode1, columnTitle1;
	@FXML
	private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
	@FXML
	private ObservableList<addNew> bookList = FXCollections.observableArrayList();
	@FXML
	private ObservableList<borrowNew> borrowList = FXCollections.observableArrayList();
	
	private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
	
	private ObservableList<borrowNew> incomingBorrowList = FXCollections.observableArrayList();
	
	private APIController apiController = new APIController();
	
    private ScheduledExecutorService scheduler;
    
    private Runnable searchTask;
    
    private static requestUserController instance;
    
    public static requestUserController getInstance() {
    	if(instance == null) {
    		instance = new requestUserController();
    	}
    	return instance;
    }
	
	private String id;
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	public static requestUserController setNew() {
    	return new requestUserController();
    }
	
	 public void setBookList(ObservableList<addNew> diffbook) {
	        this.incomingBookList = diffbook;
	        tableBook.setItems(incomingBookList);
	 }
	
	 public void setBorrowList(ObservableList<borrowNew> diffbook) {
	        this.incomingBorrowList = diffbook;
	        tableBorrowBook.setItems(incomingBorrowList);
	 }
	
	private void applyHoverEffect(Node node) {
   	 ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), node);
        scaleIn.setToX(1.2);
        scaleIn.setToY(1.2);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), node);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        node.setOnMouseEntered(e -> scaleIn.play()); 
        node.setOnMouseExited(e -> scaleOut.play()); 
   }
	
	@FXML
	private void initialize() {
		columnCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("nameBook"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("nameAuthor"));
        columnYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        
        columnUserID1.setCellValueFactory(new PropertyValueFactory<>("userID"));
        columnCode1.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        columnTitle1.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        tableBook.setItems(incomingBookList);
        tableBorrowBook.setItems(incomingBorrowList);
        
        hboxnew.setVisible(false);
        
        userLog UserLog = new userLog(loginUserController.getInstance().getUser(), "");
        String ID = userLoginAccount.setNew().searchAcc(UserLog);
        yourID.setText("Your ID: " + ID);
        setID(ID);
		
		scheduler = Executors.newSingleThreadScheduledExecutor();
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                scheduleSearch(newValue.trim());
            } else {
                searchResultsContainer.getChildren().clear();
            }
        });
        
        
		text1.setText("This is one of the greatest works of literature in the world. Written in the 19th century, "
		        + "this novel depicts the lives of a group of characters against the backdrop of the Napoleonic Wars "
		        + "and the invasion of Russia in 1812. "
		        + "The work is not just a historical novel about war, but also a profound exploration of love, family, "
		        + "fate, and social change. The main characters include Pierre Bezukhov, Andrei Bolkonsky, and Natasha Rostova, "
		        + "each of whom has their own journey in the search for meaning and happiness in life.");

		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		text1.setFont(font);
		text1.setWrappingWidth(300);
		
		text2.setText("The work tells the story of the life of Pavel Korchagin, a young Russian man living during the October Revolution "
		        + "and the Russian Civil War. The book is not only a work about an individual but also reflects the process "
		        + "of building and developing Soviet society. It has become a symbol of heroism in Russian literature, "
		        + "and Pavel Korchagin has become an ideal model of the era.");
		
		text2.setFont(font);
		text2.setWrappingWidth(300);
		
		paragraph.setText("\"Every love story begins differently. Some start with a shy glance in a crowded classroom, others with a spontaneous conversation at a street corner. For them, it was the simplest of moments—a shared umbrella on a rainy afternoon. The city seemed to slow down as the rain poured, creating a world that belonged only to the two of them.\r\n"
				+ "\r\n"
				+ "She spoke about her favorite books, and he listened, captivated not just by her words but by the way her eyes sparkled when she talked about characters she loved. He told her about the stars, pointing out constellations even when the sky was hidden by clouds.\r\n"
				+ "\r\n"
				+ "Each day brought something new—a cup of coffee left on her desk, a note scribbled on the back of a receipt, or just a walk through the park as autumn leaves fell around them. They never made grand promises, never spoke of forever. Instead, they cherished the quiet certainty of now, finding joy in the smallest of gestures.\r\n"
				+ "\r\n"
				+ "Perhaps love wasn’t about fireworks or dramatic declarations. Perhaps it was about noticing the way her laughter lingered long after she had stopped, or how his presence turned an ordinary day into something unforgettable. And so, their story grew—not in leaps, but in gentle steps, one moment, one memory, one heartbeat at a time.\"");
		paragraph.setFont(font);
		paragraph.setWrappingWidth(1300);
		
		note.setText("*Note: Please fill in the full account name, the book you want to borrow, and the book code in the request form below. If not, it will not be approved.");
		note.setFont(font);
		note.setWrappingWidth(1000);
		
		
		lb1.setFont(font);
		lb2.setFont(font);
		title.setFont(font);
		title2.setFont(font);
		home.setFont(font);
		introduce.setFont(font);
		suprise.setFont(font);
		service.setFont(font);
		contact.setFont(font);
		back.setFont(font);
		label5.setFont(font);
		label6.setFont(font);
		
		applyHoverEffect(img_1);
		applyHoverEffect(img_2);
		applyHoverEffect(img_3);
		applyHoverEffect(img_4);
		applyHoverEffect(img_5);
		applyHoverEffect(img_6);
		applyHoverEffect(img_7);
		applyHoverEffect(img_8);
		applyHoverEffect(img_9);
		applyHoverEffect(img_10);
		applyHoverEffect(img111);
		applyHoverEffect(img22);
		applyHoverEffect(img33);
		applyHoverEffect(img44);
		applyHoverEffect(img55);
		applyHoverEffect(img66);
		applyHoverEffect(img77);
		applyHoverEffect(img88);
		applyHoverEffect(img99);
		applyHoverEffect(img1010);
		applyHoverEffect(img1001);
		applyHoverEffect(img1002);
		
		transistionController tran = new transistionController();
		tran.COMEON(title);
		tran.COMEONALL1(searchBook,fieldSearch,image);
		tran.COMERIGHT(label5);
		tran.COMERIGHT(para);
		
		ArrayList<Node> I = new ArrayList<Node>();
		I.add(img1);
		I.add(img2);
		I.add(img3);
		I.add(img4);
		tran.COMEONARRAY(I);
		
		ArrayList<Node> I1 = new ArrayList<Node>();
		I1.add(img1001);
		I1.add(lb1);
		I1.add(text1);
		tran.COMERIGHT3(I1);
		
		ArrayList<Node> I2 = new ArrayList<Node>();
		I2.add(lb2);
		I2.add(text2);
		I2.add(img1002);
		tran.COMELEFTARRAY(I2);
		
		ArrayList<Node> I3 = new ArrayList<Node>();
		I3.add(img_1);
		I3.add(img_2);
		I3.add(img_3);
		I3.add(img_4);
		I3.add(img_5);
		tran.COMERIGHT3(I3);
		
		ArrayList<Node> I4 = new ArrayList<Node>();
		I4.add(img_6);
		I4.add(img_7);
		I4.add(img_8);
		I4.add(img_9);
		I4.add(img_10);
		tran.COMELEFTARRAY(I4);
		
		ArrayList<Node> I5 = new ArrayList<Node>();
		I5.add(img11);
		I5.add(img22);
		I5.add(img33);
		I5.add(img44);
		I5.add(img55);
		I5.add(img66);
		I5.add(img77);
		I5.add(img88);
		I5.add(img99);
		I5.add(img1010);
		tran.COMERIGHT3(I5);
		
		ArrayList<Node> I6 = new ArrayList<Node>();
		I6.add(label6);
		I6.add(username);
		I6.add(tableBook);
		I6.add(textarea);
		I6.add(complete);
		tran.COMERIGHT3(I6);
		
		ArrayList<Node> button = new ArrayList<Node>();
		button.add(bt1);
		button.add(bt2);
		button.add(bt3);
		tran.COMERIGHT3(button);
		
		ArrayList<Node> I9 = new ArrayList<Node>();
		I9.add(label6);
		I9.add(note);
		I9.add(tableBook);
		I9.add(yourID);
		tran.COMERIGHT3(I9);
		
		ArrayList<Node> I7 = new ArrayList<Node>();
		I7.add(label6);
		I7.add(note);
		I7.add(tableBook);
		I7.add(yourID);
		I7.add(lab2);
		I7.add(borrowerID);
		I7.add(userName);
		I7.add(phone);
		I7.add(borrowDate);
		I7.add(returnDate);
		tran.COMERIGHT3(I7);
		
		ArrayList<Node> I8 = new ArrayList<Node>();
		I8.add(lab1);
		I8.add(Title);
		I8.add(chapter);
		I8.add(author);
		I8.add(quantity);
		I8.add(comlete1);
		tran.COMELEFTARRAY(I8);
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
	
    @FXML
    private void handleSearchBook() {
        String query = fieldSearch.getText().trim();
        if (query.isEmpty()) {
        	hboxnew.setVisible(false);
            loadingIndicator.setVisible(false);
            mainContent.setEffect(null); 
            searchResultsContainer.setVisible(false);
            return;
        }

        loadingIndicator.setVisible(true);

        GaussianBlur blurEffect = new GaussianBlur(10);
        mainContent.setEffect(blurEffect);

        searchResultsContainer.setVisible(false);
        hboxnew.setVisible(false);

        Task<JsonArray> searchTask = new Task<JsonArray>() {
            @Override
            protected JsonArray call() throws Exception {
            	Thread.sleep(500);
                return apiController.searchBooks(query, 8);
            }

            @Override
            protected void succeeded() {
                JsonArray results;
                try {
                    results = get();
                    updateResults(results);

                    Platform.runLater(() -> {
                        searchResultsContainer.setVisible(true);
                        hboxnew.setVisible(true);
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
                String authors = volumeInfo.has("authors") ? volumeInfo.get("authors").toString().replace("[", "").replace("]", "") : "Unknown author";
                String description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "No description available";
                String publishedDate = volumeInfo.has("publishedDate") ? volumeInfo.get("publishedDate").getAsString() : "Unknown publication date";
                String ratings = volumeInfo.has("averageRating") ? volumeInfo.get("averageRating").getAsString() : "No ratings available";
                String publisher = volumeInfo.has("publisher") ? volumeInfo.get("publisher").getAsString() : "Unknown publisher";  
                String categories = volumeInfo.has("categories") ? volumeInfo.get("categories").toString().replace("[", "").replace("]", "") : "No categories available";  // Thêm thể loại

                HBox bookEntry = new HBox(10);
                bookEntry.setAlignment(Pos.CENTER_LEFT);
                bookEntry.setStyle("-fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #f9f9f9; -fx-effect: dropshadow(gaussian, #cccccc, 10, 0.1, 0, 2);");
                bookEntry.setOnMouseEntered(e -> bookEntry.setStyle("-fx-background-color: #eef; -fx-effect: dropshadow(gaussian, #999999, 15, 0.3, 0, 4);"));
                bookEntry.setOnMouseExited(e -> bookEntry.setStyle("-fx-background-color: #f9f9f9; -fx-effect: dropshadow(gaussian, #cccccc, 10, 0.1, 0, 2);"));

                if (thumbnailUrl != null) {
                    ImageView thumbnail = new ImageView(new Image(thumbnailUrl, 50, 75, true, true));
                    bookEntry.getChildren().add(thumbnail);
                }

                VBox bookDetails = new VBox(5);
                bookDetails.setAlignment(Pos.CENTER_LEFT);

                Text bookTitleText = new Text(title);
                bookTitleText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                bookTitleText.setWrappingWidth(300);

                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                Tooltip tooltip = new Tooltip("Description: " + description + "\nPublication Date: " + publishedDate + "\nRatings: " + ratings);
                Tooltip.install(bookEntry, tooltip);

                Text authorText = new Text("Authors: " + authors);
                authorText.setStyle("-fx-font-size: 14px;");

                Text publisherText = new Text("Publisher: " + publisher); 
                publisherText.setStyle("-fx-font-size: 14px;");

                Text categoriesText = new Text("Categories: " + categories);
                categoriesText.setStyle("-fx-font-size: 14px;");

                Text descriptionText = new Text("Description: " + description);
                descriptionText.setWrappingWidth(1000);
                descriptionText.setStyle("-fx-font-size: 12px; -fx-fill: #555;");

                Text publishedDateText = new Text("Publication Date: " + publishedDate);
                publishedDateText.setStyle("-fx-font-size: 12px; -fx-fill: #777;");

                Text ratingsText = new Text("Ratings: " + ratings);
                ratingsText.setStyle("-fx-font-size: 12px; -fx-fill: #777;");

                bookDetails.getChildren().addAll(bookLink, authorText, publisherText, categoriesText, descriptionText, publishedDateText, ratingsText);

                bookEntry.getChildren().add(bookDetails);
                searchResultsContainer.getChildren().add(bookEntry);
            }
        } else {
            Text noResults = new Text("No suggestions found.");
            noResults.setStyle("-fx-font-size: 16px; -fx-fill: #999;");
            searchResultsContainer.getChildren().add(noResults);
        }
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
                String authors = volumeInfo.has("authors") ? volumeInfo.get("authors").toString().replace("[", "").replace("]", "") : "Unknown author";
                String description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "No description available";
                String publishedDate = volumeInfo.has("publishedDate") ? volumeInfo.get("publishedDate").getAsString() : "Unknown publication date";
                String ratings = volumeInfo.has("averageRating") ? volumeInfo.get("averageRating").getAsString() : "No ratings available";
                String publisher = volumeInfo.has("publisher") ? volumeInfo.get("publisher").getAsString() : "Unknown publisher";
                String categories = volumeInfo.has("categories") ? volumeInfo.get("categories").toString().replace("[", "").replace("]", "") : "No categories available";  // Thêm thể loại

                HBox bookEntry = new HBox(10);
                bookEntry.setAlignment(Pos.TOP_CENTER);
                bookEntry.setStyle("-fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-color: #f9f9f9;");

                if (thumbnailUrl != null) {
                    ImageView thumbnail = new ImageView(new Image(thumbnailUrl, 100, 150, true, true));
                    bookEntry.getChildren().add(thumbnail);
                }

                VBox bookDetails = new VBox(20);
                bookDetails.setAlignment(Pos.TOP_CENTER);
                Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);

                Text bookTitleText = new Text(title);

                bookTitleText.setStyle("-fx-font-size: 16px; -fx-font-family:'Accent Graphic W00 Medium';");
                bookTitleText.setWrappingWidth(300);

                Hyperlink bookLink = new Hyperlink();
                bookLink.setGraphic(bookTitleText);
                bookLink.setOnAction(event -> openWebpage(infoLink));

                Text authorText = new Text("Authors: " + authors);
                authorText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");

                Text publisherText = new Text("Publisher: " + publisher);
                publisherText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");

                Text categoriesText = new Text("Categories: " + categories);
                categoriesText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");

                Text descriptionText = new Text();
                Button seeMoreButton = new Button("View Details");
                seeMoreButton.getStyleClass().add("buttonAdd");

                if (description.length() > 200) {
                    descriptionText.setText("Description: " + description.substring(0, 200) + "...");
                    descriptionText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");
                    seeMoreButton.setOnAction(event -> showDetailedPopup(title, thumbnailUrl, authors, publishedDate, ratings, description, publisher, categories));
                } else {
                    descriptionText.setText("Description: " + description);
                    seeMoreButton.setVisible(false);
                }
                descriptionText.setWrappingWidth(300);
                descriptionText.setStyle("-fx-font-size: 12px;-fx-text-fill:black;-fx-font-family:'Accent Graphic W00 Medium';");

                Text publishedDateText = new Text("Publication date: " + publishedDate);
                publishedDateText.setStyle("-fx-font-size: 12px;-fx-text-fill:black;-fx-font-family:'Accent Graphic W00 Medium';");

                Text ratingsText = new Text("Ratings: " + ratings);
                ratingsText.setStyle("-fx-font-size: 12px;-fx-text-fill:black;-fx-font-family:'Accent Graphic W00 Medium';");

                bookDetails.getChildren().addAll(bookLink, authorText, publisherText, categoriesText, descriptionText, publishedDateText, ratingsText, seeMoreButton);

                bookEntry.getChildren().add(bookDetails);
                searchResultsContainer.getChildren().add(bookEntry);
            }
        } else {
            Text noResults = new Text("No results found.");
            noResults.setStyle("-fx-font-size: 16px; -fx-fill: #999;");
            searchResultsContainer.getChildren().add(noResults);
        }
    }



    private void showDetailedPopup(String title, String thumbnailUrl, String authors, String publishedDate, String ratings, String description,String publisher, String categories) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Thông tin chi tiết");
        Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);

        VBox popupContent = new VBox(10);
        popupContent.setPadding(new Insets(10));
        popupContent.setAlignment(Pos.TOP_CENTER);
        
        Text popupTitle = new Text(title);
        popupTitle.setStyle("-fx-font-size: 30px; -fx-font-family:'Accent Graphic W00 Medium';");
        popupTitle.setFont(font);
        popupTitle.setWrappingWidth(400);

        ImageView thumbnail = null;
        if (thumbnailUrl != null) {
            thumbnail = new ImageView(new Image(thumbnailUrl, 200, 300, true, true));
        }
        
        Text authorText = new Text("Authors: " + authors);
        authorText.setStyle("-fx-font-size: 14px;-fx-font-family:'Accent Graphic W00 Medium';");
        authorText.setFont(font);
        
        Text publisherText = new Text("Publisher: " + publisher);
        publisherText.setStyle("-fx-font-size: 14px; -fx-text-fill:black; -fx-font-family:'Accent Graphic W00 Medium';");
        publisherText.setFont(font);

        Text categoriesText = new Text("Categories: " + categories);
        categoriesText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");
        categoriesText.setFont(font);
        
        Text publishedDateText = new Text("Publication date: " + publishedDate);
        publishedDateText.setStyle("-fx-font-size: 14px; -fx-font-family:'Accent Graphic W00 Medium';");
        publishedDateText.setFont(font);
        
        Text ratingsText = new Text("Rating: " + ratings);
        ratingsText.setStyle("-fx-font-size: 14px;-fx-font-family:'Accent Graphic W00 Medium';");
        ratingsText.setFont(font);
        
        Text fullDescription = new Text(description);
        fullDescription.setWrappingWidth(400);
        fullDescription.setStyle("-fx-font-size: 12px; -fx-text-fill:black; -fx-font-family:'Accent Graphic W00 Medium';");
        fullDescription.setFont(font);
        
        ScrollPane scrollPane = new ScrollPane(fullDescription);
        scrollPane.setPrefWidth(450);
        scrollPane.setPrefHeight(300);
        scrollPane.setPadding(new Insets(20));
        scrollPane.setStyle("-fx-border-radius:10; -fx-background-radius:10; -fx-border-color:black;");

        VBox layout = new VBox(10, popupTitle);
        if (thumbnail != null) {
            layout.getChildren().add(thumbnail);
            layout.setAlignment(Pos.CENTER_LEFT);
        }
        layout.getChildren().addAll(authorText, publishedDateText, publisherText, categoriesText, ratingsText);
        layout.setAlignment(Pos.CENTER_LEFT);
        
        HBox lay = new HBox(10);
        lay.getChildren().addAll(layout, scrollPane);
        lay.setAlignment(Pos.TOP_CENTER);
        lay.setPadding(new Insets(15));

        Scene popupScene = new Scene(lay, 1000, 600);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();
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
	
	@FXML
	private void handleHome() {
		createScene(home,"/main/sources/interfaceUser.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleIntro() {
		createScene(introduce,"/main/sources/interfaceUser_1.fxml","/main/sources/css/interfaceUser.css");
	}
	
	@FXML
	private void handleSuprise() {
		
	}
	
	@FXML
	private void handleService() {
		createSceneGame(service,"/main/sources/Trivia.fxml","","/music.mp3");
	}
	
	@FXML
	private void handleContact() {
		createScene(contact,"/main/sources/contactView.fxml","/main/sources/css/interfaceUser.css");
	}
	
	/**xử lý sự kiện kiểm tra xe sách có tồn tại*/
	@FXML
	private void handleSearch() {
		add Add = new add(bookCode.getText());
		String bookcode = addbook.setNewAdd().search(Add);
		if(!bookcode.isEmpty()) {
			alertController.setNew().AlertComplete("Đã tìm thấy mã sách thành công");
			ArrayList<alter> arr = addbook.setNewAdd().selectByCondition1(bookcode);
			Title.setText(arr.get(0).getnameBook());
			chapter.setText(arr.get(0).getchapBook());
			author.setText(arr.get(0).getnameAuthor());
			quantity.setText(arr.get(0).getQuantity());
		}else {
			alertController.setNew().AlertComplete("Đã tìm thấy mã sách không thành công");
			clearFields();
		}
	}
	
	/**xử lý sự kiện tìm kiếm ID người dùng*/
	@FXML
	private void handleSearchID() {
		String check = userID.getText();
		if(!check.isEmpty() && check.equals(getID())) {
			int randomNumber = (int) (Math.random() * 90000000) + 10000000;
			alertController.setNew().AlertComplete("Đã tìm thấy ID của bạn");
			ArrayList<userLog> arr = userLoginAccount.setNew().selectByCondition(check);
			borrowerID.setText(String.valueOf(randomNumber));
			userName.setText(arr.get(0).getFullname());
			arr.clear();
		}else {
			alertController.setNew().AlertComplete("Có thể bạn đã nhập sai ID");
			userID.clear();
		}
	}
	
	@FXML
	private void handleBorrow() {
		String res = bookCode.getText();
        if(!res.isEmpty()) {
         LocalDate currentDate = LocalDate.now();
	     LocalDate borrowReturnDate = LocalDate.parse(returnDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	     String status = currentDate.isAfter(borrowReturnDate) ? "Quá hạn" : "Đang mượn";
        	
       	 borrow Borrow = new borrow(borrowerID.getText(), userID.getText(),bookCode.getText()
                    ,borrowDate.getText(), returnDate.getText(), userName.getText() ,status, phone.getText());
       	 
       	 borrow newBorrow = new borrow(getID(),bookCode.getText(), Title.getText());
       	 
       	 int rs = borrowbook.setNew().insert(Borrow);
       	 int rs2 = borrowedBooks.setNew().insert(newBorrow);
       	 
       	 add Add = new add(quantity.getText(), res);
       	 int check = addbook.setNewAdd().update2(Add);
       	 int Quantity = Integer.parseInt(quantity.getText());
       	 if(rs > 0 && rs2 > 0 && check > 0 && Quantity >= 1) {
       		 borrowNew borrownew = new borrowNew(Integer.parseInt(getID()),bookCode.getText(),Title.getText());
       		 incomingBorrowList.add(borrownew);
       		 alertController.setNew().AlertComplete("Ghi nhận thành công");
           	 clearFields();
           	 clearFields1();
       	 }else {
       		 alertController.setNew().AlertUnComplete("Ghi nhận không thành công");
       	 }
        }else {
       	 alertController.setNew().AlertUnComplete("Không tìm thấy mã sách");
        }
	}
	
	private void clearFields() {
	       bookCode.clear();
	       Title.clear();
	       chapter.clear();
	       author.clear();
	       quantity.clear();
	}
	
	private void clearFields1() {
		borrowerID.clear();
		userName.clear();
		phone.clear();
		borrowDate.clear();
		returnDate.clear();
		userID.clear();
	}
	
	@FXML
	private void handleRequire() {
		if(username.getText().isEmpty()) {
			alertController.setNew().AlertUnComplete("Bạn chưa nhập tên tài khoản");
		}else {
			if(loginUserController.getInstance().getUser().equals(username.getText())) {
				require req = new require(username.getText(),textarea.getText());
				int res = requireUser.setNew().searchId(req);
				if(res > 0) {
					if(textarea.getText().isEmpty()) {
						alertController.setNew().AlertUnComplete("Vui lòng nhập yêu cầu của bạn");
					}else {
						int ins = requireUser.setNew().insertLog(req);
						textarea.clear();
						if(ins > 0) {
							alertController.setNew().AlertComplete("Bạn đã yêu cầu thành công");
						}else {
							alertController.setNew().AlertUnComplete("Bạn chưa yêu cầu được");
						}	
					}
				}
			}else {
				alertController.setNew().AlertUnComplete("Bạn đã nhập sai tên tài khoản của bạn");
			}
		}
	}
	
	@FXML
	private void handleReturn() {
		String Code = textReturn.getText();
		int res = borrowedBooks.setNew().Delete(Code);
		int res1 = borrowbook.setNew().DeleteBookCode(Code);
		if(Code.isEmpty()) {
			alertController.setNew().AlertUnComplete("Vui lòng nhập mã sách cần trả");
		}else {
			if(res > 0 && res1 > 0) {
				alertController.setNew().AlertUnComplete("Đã trả sách thành công");
			}else {
				alertController.setNew().AlertUnComplete("Có thể bạn đã nhập sai mã sách");
			}
		}
	}
	
	@FXML
	private void handleLoadingPage() {
		try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
            PreparedStatement prsttm1 = con.prepareStatement("SELECT * FROM borrowed_books WHERE userID = ?");
            prsttm1.setInt(1,Integer.parseInt(getID()));
            ResultSet rs = prsttm.executeQuery();
            ResultSet rs1 = prsttm1.executeQuery();
            ObservableList<addNew> bookList = FXCollections.observableArrayList();
            ObservableList<borrowNew> borrowList = FXCollections.observableArrayList();
            while (rs.next()) {
                addNew AddNew = new addNew(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
                bookList.add(AddNew);
            }
            
            while(rs1.next()) {
            	borrowNew BorrowNew = new borrowNew(rs1.getInt(1), rs1.getString(2) , rs1.getString(3));
            	borrowList.add(BorrowNew);
            }
            
            requestUserController controller = (requestUserController) createScene1(loading, 
                "/main/sources/requestUserView.fxml", "/main/sources/css/interfaceUser.css");
            controller.setBookList(bookList); 
            controller.setBorrowList(borrowList); 
            
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
	}
	
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}

}