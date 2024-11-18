package main.java.controller;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import main.java.dao.requireUser;
import main.java.model.addNew;
import main.java.model.require;
public class requestUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label5,label6;
	@FXML
	private VBox tuto1,tuto2,tuto3,tuto4,vboxnew;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,
	img9,img10,img11,img_1,img_2,img_3,img_4,img_5,image,img_6,img_7,img_8,img_9
	,img_10,img22,img33,img44,img55,img66,img77,img88,img99,img1010,img111,img1001,img1002;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3,complete;
	@FXML
	private TextField fieldSearch,username;
	@FXML
	private Button searchBook;
	@FXML
	private ScrollPane scrollpane;
	@FXML
	private VBox searchResultsContainer,para;
	@FXML
	private ProgressIndicator loadingIndicator;
	@FXML
	private Text text1,text2,lb1,lb2,title,title2,paragraph,note;
	@FXML
    private BorderPane mainContent;
	@FXML
	private HBox hboxnew;
	@FXML
	private TextArea textarea;
	@FXML
	private TableView<addNew> tableBook;
	@FXML
	private TableColumn<addNew, String> columnCode, columnTitle, columnAuthor, columnYear;
	@FXML
	private ObservableList<addNew> bookList = FXCollections.observableArrayList();
	    
	private ObservableList<addNew> incomingBookList = FXCollections.observableArrayList();
	
	public static requestUserController setNew() {
    	return new requestUserController();
    }
	
	 public void setBookList(ObservableList<addNew> diffbook) {
	        this.incomingBookList = diffbook;
	        tableBook.setItems(incomingBookList);
	 }
	
	private APIController apiController = new APIController();
    private ScheduledExecutorService scheduler;
    private Runnable searchTask;
	
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
        
        tableBook.setItems(incomingBookList);
        
        hboxnew.setVisible(false);
		
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
		
		ArrayList<Node> butt = new ArrayList<Node>();
		butt.add(home);
		butt.add(introduce);
		butt.add(suprise);
		butt.add(service);
		butt.add(contact);
		butt.add(back);
		Collections.reverse(butt);
		tran.COMERIGHT3(butt);
		
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
                bookEntry.setAlignment(Pos.CENTER);

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
		
	}
	@FXML
	private void handleContact() {
		
	}
	
	@FXML
	private void handleRequire() {
		if(username.getText().isEmpty()) {
			alertController.setNew().AlertUnComplete("Bạn chưa nhập tên tài khoản");
		}else {
			require req = new require(username.getText(),textarea.getText());
			int res = requireUser.setNew().searchId(req);
			if(res > 0) {
				if(textarea.getText().isEmpty()) {
					alertController.setNew().AlertUnComplete("Vui lòng nhập yêu cầu của bạn");
				}else {
					int ins = requireUser.setNew().insertLog(req);
					if(ins > 0) {
						username.clear();
						textarea.clear();
						alertController.setNew().AlertComplete("Bạn đã yêu cầu thành công");
					}else {
						alertController.setNew().AlertUnComplete("Bạn chưa yêu cầu được");
					}	
				}
			}
		}
	}
	
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}

}