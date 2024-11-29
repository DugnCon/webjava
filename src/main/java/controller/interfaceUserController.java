package main.java.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.JDBC.JDBCSQL;
import main.java.controller.transistionController;
import main.java.dao.userLoginAccount;
import main.java.model.addNew;
import main.java.model.borrowNew;
import main.java.model.userLog;

public class interfaceUserController extends baseSceneController {
	@FXML
	private Label label1,label2,label3,label4,label5,label6;
	@FXML
	private VBox tuto1,tuto2,tuto3;
	@FXML
	private HBox tuto4;
	@FXML
	private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img_6;
	@FXML
	private Button home,introduce,suprise,service,contact,back;
	@FXML
	private Button bt1,bt2,bt3;
	@FXML
	private Text text1,text2,text3,text4,text5,text4_1,text5_1;
	@FXML
	private void initialize() {
		transistionController tran = new transistionController();
		ArrayList<Node> img_1 = new ArrayList<Node>();
		img_1.add(img1);
		img_1.add(img2);
		img_1.add(img3);
		img_1.add(img4);
		
		ArrayList<Node> vbox = new ArrayList<Node>();
		vbox.add(tuto1);
		vbox.add(tuto2);
		vbox.add(tuto3);
		vbox.add(tuto4);
		
		ArrayList<Node> label = new ArrayList<Node>();
		label.add(label1);
		label.add(label2);
		label.add(label3);
		label.add(label4);
		label.add(label5);
		
		ArrayList<Node> img_2 =  new ArrayList<Node>();
		img_2.add(img5);
		img_2.add(img6);
		img_2.add(img7);
		img_2.add(img8);
		img_2.add(img9);
		img_2.add(img10);
		img_2.add(img11);
		
		ArrayList<Node> button = new ArrayList<Node>();
		button.add(bt1);
		button.add(bt2);
		button.add(bt3);
		
		tran.COMELEFTARRAY(vbox);
		tran.COMERIGHT3(label);
		tran.COMEONARRAY(img_1);
		tran.COMERIGHT3(img_2);
		tran.COMERIGHT3(button);
		tran.COMELEFT(img_6);
		
		Font font = Font.loadFont(getClass().getResourceAsStream("/Accent Graphic W00 Medium.ttf"), 20);
		home.setFont(font);
		introduce.setFont(font);
		suprise.setFont(font);
		service.setFont(font);
		contact.setFont(font);
		back.setFont(font);
		
		text1.setText(
			    "Article 1. Name and Headquarters \n" +
			    "1. Vietnamese name: Center for Library and Digital Knowledge. \n" +
			    "2. English name: VNU - Library and Digital Knowledge Center. \n" +
			    "3. Abbreviated name: VNU - LIC. \n" +
			    "4. Headquarters: \n" +
			    "a) Main headquarters: C1T Building - No. 144, Xuan Thuy Street, Cau Giay District, Hanoi. " +
			    "b) Branches: \n" +
			    "- A2 Building, University of Foreign Languages - No. 2, Pham Van Dong Street, Cau Giay District, Hanoi. " +
			    "- Me Tri Dormitory - No. 182, Luong The Vinh Street, Thanh Xuan District, Hanoi. " +
			    "- M Building, University of Social Sciences and Humanities - No. 336, Nguyen Trai Street, Thanh Xuan District, Hanoi. " +
			    "- VNU Urban Area in Hoa Lac - Thach Hoa Commune, Thach That District, Hanoi. \n" +
			    "5. Website: http://lic.vnu.edu.vn Email: lic@vnu.edu.vn \n" +
			    "6. Telephone: (024) 37546545 Hotline: (024) 62539899 \n" +
			    "Article 2. Legal Position \n" +
			    "1. The Center for Library and Digital Knowledge (hereinafter referred to as the Center) was reorganized based on adjusting the functions, tasks, and renaming of the Information - Library Center under Decision No. 316/QĐ-ĐHQGHN dated February 14, 2022, issued by the Director of Vietnam National University, Hanoi (VNU); it is a service unit affiliated with VNU, partially self-financed for regular expenses, with legal status, its own seal, and a separate account. \n" +
			    "2. The Center is organized and operates under the current laws, VNU regulations, and this Regulation. " +
			    "3. The Center is under the comprehensive and direct management of VNU, obligated and responsible for performing the assigned tasks, strictly adhering to laws, regulations, and policies of the State and VNU."
			);
		text1.setFont(font);
		text1.setWrappingWidth(800);
		
		text2.setText(
			    "The Center collaborates with more than 50 universities, research institutes, international organizations, and publishers from the United States, France, Germany, Russia, Italy, Japan, South Korea, and many other countries, including: \n" +
			    "• Harvard Yenching Institute \n" +
			    "• Cornell University \n" +
			    "• University of Hawaii \n" +
			    "• Library of Congress (USA) \n" +
			    "• University of Paris \n" +
			    "• Sorbonne University \n" +
			    "• University of Bonn \n" +
			    "• Lomonosov Moscow State University \n" +
			    "• Russian Academy of Sciences \n" +
			    "• University of Tokyo \n" +
			    "• Kyoto University \n" +
			    "• Kangwon National University \n" +
			    "• Tsinghua University \n" +
			    "• Liaoning University \n" +
			    "• National Library of Australia \n" +
			    "• International Development Research Centre \n" +
			    "• University of California Press \n" +
			    "• Geological Survey of Japan Library \n" +
			    "• Support program of the Abdus Salam International Centre for Theoretical Physics...\n"
			);
		text2.setFont(font);
		text2.setWrappingWidth(600);
		
		text3.setText(
			    "Additionally, the Center maintains relationships with many international organizations in Hanoi, such as:\n " +
			    "• Cultural and Information Office of the U.S. Embassy \n" +
			    "• Goethe Institute \n" +
			    "• British Council \n" +
			    "• Asia Foundation \n" +
			    "• Information Office of the Korean Embassy \n" +
			    "• World Bank \n" +
			    "• Francophone Council... \n" +
			    "The Center is a founding member and participates in the Executive Board of the ASEAN University Network Inter-Library Online (AUNILO) \n" +
			    "and the East Asian University Library Council.\n"
			);
		text3.setFont(font);
		text3.setWrappingWidth(600);
		
		text4.setText(
			    "1. Collect, store, process, organize, and manage various types of data, information, knowledge, and learning materials (traditional, digital, multimedia) and provide and disseminate scientific information products, services, and resources (scientific data, scientific information, scientific knowledge, learning materials) to learners, faculty, researchers, managers, staff, employees at VNU and external users (hereafter referred to as users). \n" +
			    	    "2. Effectively manage the large data systems of VNU (digital learning materials, lectures, textbooks, digital theses, scientific research data, digital research results, patents, inventions, and scientific profiles) and connect and integrate with high-quality academic databases from Vietnam and the world to support digital research, digital training, digital learning, and digital transformation activities at VNU. \n" +
			    	    "3. Support the management of VNU's high-level research and training through activities such as: research, analysis, synthesis, and forecasting research trends at VNU; researching and implementing bibliometric indicators such as ISI, Scopus, Vcgate...; training and supporting research and learning tools such as: information literacy, skills in searching, storing, analyzing, and using data, information, and knowledge, self-research and self-learning methods, scientific citation, plagiarism prevention. \n" +
			    	    "4. Research and implement advancements in library science, information science, and knowledge science; organize advanced training courses on information, knowledge, digital libraries, data-information-knowledge management for staff within and outside VNU; provide knowledge-library services; consult on building and implementing digital library activities, digital transformation, and knowledge management.\n"
	    );
		text4.setFont(font);
		text4.setWrappingWidth(600);
		
		text4_1.setText(
				"5. Digital transformation: Digitize and convert all traditional learning materials into digital format, transform and digitize all types of documents: books, newspapers, magazines, historical documents, multimedia into digital file formats, and organize storage to serve the Center's operational processes; store, retrieve, use, and exploit VNU's long-term digital data; develop and store a large data system including VNU's training and research units.\n" +
					    "6. Cataloging and creating metadata: Catalog and create indexes, metadata templates according to international standards for data description. Build an appropriate information retrieval system; establish an automated information access and search network; organize for all users at VNU to conveniently and effectively exploit and use the Center's knowledge resources and external sources.\n" +
					    "7. Manage technology and big data: Apply and manage the technology system, data management platforms, software, network systems, server storage systems, hardware devices, and computer systems at the Center; organize, connect, interlink, and manage the Center's big data system with data from within and outside VNU, integrate and unify with VNU's data hub to develop a digital university and improve usage efficiency; build and develop a shared digital library data system contributing to the Digital Knowledge System of the Government; create a digital author profile for the entire VNU to store and manage the intellectual output of scientists.\n" +
					    "8. Implement digital knowledge services: Implement bibliometric measurement ISI/Scopus to analyze, evaluate, report, and forecast VNU's research trends; provide search and scientific data services for researchers; implement online services; train users and support research-learning tools such as: Information literacy; Skills in searching, storing, analyzing, and using data, information, knowledge; Self-research and self-learning methods; Scientific citation; Plagiarism prevention; Survey and evaluate users and the quality of service of the Center.\n" +
					    "9. Implement modern knowledge service systems: Serve the learning materials system; automatic borrowing and returning of learning materials; smart reading rooms; self-study and self-research; provide academic exchange spaces by group - topic; provide modern and smart equipment to support self-study - self-research processes.\n" +
					    "10. Manage technical infrastructure, facilities, and modern-smart equipment; design, build, and operate the Center's smart building.\n" 
				);
		text4_1.setFont(font);
		//text5.setFont(font);
		text4_1.setWrappingWidth(600);
		
		text5.setText(
					    "1. Advise the leadership of VNU on the direction of organizing and managing knowledge management-library, digital transformation, development of digital libraries, and digital knowledge centers to improve the quality of training, research, teaching, learning, and serving users within VNU.\n" +
					    "2. Build strategies and development plans: Organize and coordinate the data management-information management-knowledge management-library system, digital transformation, digital library development, and digital knowledge center in VNU.\n" +
					    "3. Develop resources, information, and knowledge: Collect, purchase, and supplement various forms of scientific data, scientific information, scientific knowledge, learning materials (textbooks, lecture notes, reference books, newspapers, journals, etc.), databases, and organize, preserve, and store these knowledge resources to provide services for users at VNU; statistics, analysis, and synthesis of data-information-knowledge needs to meet training, research, and learning demands of training units, communities, and individual users within VNU; build and develop a data ecosystem, learning materials to serve study, research, and teaching at VNU.\n" +
					    "4. Collect and store the publications published by VNU, including: books, textbooks, other types of materials published by VNU Publishing House; theses, master's theses, doctoral dissertations defended at VNU or written by civil servants, employees, workers (CCVC), and students, graduate students, PhD students (HSSV) of VNU; final reports of research projects, scientific papers that have been accepted; conference proceedings, seminar proceedings, electronic learning materials, electronic lectures led by VNU units or conducted by CCVC, HSSV of VNU; data systems, information, knowledge in print, digital, and physical formats about VNU.\n" 
					);
		text5.setFont(font);
		text5.setWrappingWidth(600);
		
		text5_1.setText(
				"11. Research achievements in library science, information science; data-information-knowledge management to apply and implement for the Center's activities, VNU, Vietnam, and the world.\n" +
					    "12. Organize scientific workshops, conferences; organize advanced training courses in digital libraries, data-information-knowledge management; consult on building and developing digital libraries, knowledge management for staff within and outside VNU.\n" +
					    "13. Manage and provide data, information, scientific publications, and research for training, research, and the standard procedure for the recognition of scientific titles in the process of professor and associate professor title evaluation.\n" +
					    "14. Develop exchange and cooperation relationships with agencies, organizations working in the field of data-information-knowledge-library management within and outside Vietnam; participate in activities of the Union of University Libraries, Vietnam Library Association, Vietnam Information-Documentation Association, and other library, data-information-knowledge centers in and outside the country according to regulations of the Government and VNU.\n" +
					    "15. Link, cooperate comprehensively with units inside and outside VNU in implementing training, research, science-technology transfer, and services, production, business, providing knowledge-library products and services; use and share resources and facilities according to VNU regulations.\n" +
					    "16. Develop reading culture, build and develop habits, needs, skills, and reading movements for CCVC, HSSV within VNU; organize reading ambassador competitions; organize book festivals, Vietnam Book Day.\n" +
					    "17. Perform other tasks assigned by the Director of VNU."
				);
		text5_1.setFont(font);
		text5_1.setWrappingWidth(600);
		
		label1.setFont(font);
		label2.setFont(font);
		label3.setFont(font);
		label5.setFont(font);
		label6.setFont(font);
	}
	@FXML
	private void handleHome() {
		
	}
	@FXML
	private void handleIntro() {
		createScene(introduce,"/main/sources/interfaceUser_1.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleSuprise() {
		try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book");
            PreparedStatement prsttm1 = con.prepareStatement("SELECT * FROM borrowed_books WHERE userID = ?");
            userLog UserLog = new userLog(loginUserController.getInstance().getUser(), "");
            String ID = userLoginAccount.setNew().searchAcc(UserLog);
            prsttm1.setInt(1,Integer.parseInt(ID));
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
            
            requestUserController controller = (requestUserController) createScene1(suprise, 
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
	private void handleService() {
		createSceneGame(service,"/main/sources/Trivia.fxml","","/music.mp3");
	}
	@FXML
	private void handleContact() {
		createScene(contact,"/main/sources/contactView.fxml","/main/sources/css/interfaceUser.css");
	}
	@FXML
	private void handleBack() {
		createScene(back,"/main/sources/interfaceView.fxml","/main/sources/css/interface.css");
	}
}
