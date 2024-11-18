package main.java.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.JDBC.JDBCSQL;
import main.java.model.borrowNew;
import main.java.model.requireNew;
import main.java.model.userLog;

public class interfaceController extends baseSceneController {
    @FXML
    private Button signout, manageBook, manageBorrow, managePay, manageUser, manageRequest, interfaceUser;
    @FXML
    private ImageView image;
    @FXML
    private Text text1,text2;
    @FXML
    private Label label;
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
    public void initialize() {
        addButtonZoomEffect(signout);
        addButtonZoomEffect(manageBook);
        addButtonZoomEffect(manageBorrow);
        addButtonZoomEffect(managePay);
        addButtonZoomEffect(manageUser);
        addButtonZoomEffect(manageRequest);
        addButtonZoomEffect(interfaceUser);
        transistionController tran = new transistionController();
        tran.COMERIGHTALL2(manageBook,managePay,manageBorrow,manageUser,manageRequest, signout, interfaceUser);
        tran.COMEONALL2(label,image);
        
        text1.setText("Trường Đại học Công nghệ, Đại học Quốc gia Hà Nội được thành lập theo Quyết định số 92/2004/QĐ-TTg ngày 25/05/2004 của Thủ tướng Chính phủ trên cơ sở Khoa Công nghệ và Trung tâm Hợp tác Đào tạo và Bồi dưỡng Cơ học trực thuộc Đại học Quốc gia Hà Nội với hai nhiệm vụ như sau:\r\n"
        		+ "    \r\n"
        		+ "    1. Đào tạo nguồn nhân lực trình độ đại học, sau đại học và bồi dưỡng nhân tài thuộc lĩnh vực khoa học, công nghệ;\r\n"
        		+ "    2. Nghiên cứu và triển khai ứng dụng khoa học, công nghệ đáp ứng nhu cầu phát triển kinh tế – xã hội.\r\n"
        		+ "    \r\n"
        		+ "    Sứ mạng của Nhà trường là đào tạo nguồn nhân lực chất lượng cao, trình độ cao và bồi dưỡng nhân tài; nghiên cứu phát triển và ứng dụng các lĩnh vực khoa học công nghệ tiên tiến mũi nhọn trên cơ sở phát huy thế mạnh về Công nghệ thông tin và Truyền thông.\r\n"
        		+ "    \r\n"
        		+ "    Nhà trường cũng đã xác định sứ mạng tiên phong tiếp cận chuẩn mực giáo dục đại học khu vực và thế giới, ứng dụng công nghệ thông tin trong quản trị đại học đóng góp tích cực vào sự phát triển nền kinh tế và xã hội tri thức của đất nước.\r\n"
        		+ "    \r\n"
        		+ "    Sứ mạng này hoàn toàn phù hợp và thống nhất với chủ trương, giải pháp và các mục tiêu của Chương trình đổi mới toàn diện giáo dục đại học mà Đảng và Nhà nước đang triển khai thực hiện.\r\n");
        text1.setWrappingWidth(1300);
        
        text2.setText("Sau gần hai mươi năm xây dựng và phát triển, Trường Đại học Công nghệ đã và đang từng bước thực hiện nhiệm vụ và dần khẳng định là một trường đại học có vị thế, uy tín trong hệ thống giáo dục đại học của cả nước.\r\n"
        		+ "    \r\n"
        		+ "    Tỷ lệ giảng viên cơ hữu có trình độ tiến sĩ đạt 75%, tỷ lệ giáo sư và phó giáo sư đạt 25%, tỷ lệ công bố quốc tế tăng nhanh hằng năm.\r\n"
        		+ "    \r\n"
        		+ "    Môi trường đào tạo chuẩn mực chất lượng cao đã được thiết lập với hệ thống chương trình đào tạo được phát triển, hoàn thiện ở mọi bậc đào tạo theo hướng tiên tiến, hiện đại, cập nhật; chất lượng đào tạo được xã hội ghi nhận, đánh giá cao.\r\n"
        		+ "    \r\n"
        		+ "    Phần lớn các chương trình đào tạo của Trường Đại học Công nghệ được kiểm định chương trình đào tạo theo bộ tiêu chuẩn của mạng lưới các trường đại học ASEAN (AUN) và theo tiêu chuẩn của Bộ Giáo dục và Đào tạo.\r\n"
        		+ "    \r\n"
        		+ "    Trường Đại học Công nghệ còn là đối tác tin cậy của hơn 50 trường đại học, viện nghiên cứu và tập đoàn công nghiệp lớn trên thế giới.\r\n"
        		+ "    \r\n"
        		+ "    Nhà trường đi đầu và triển khai có hiệu quả hoạt động hợp tác 'Trường – Viện – Doanh nghiệp'.");
        text2.setWrappingWidth(1300);
    }

    @FXML
    private void handleKhosach() {
        createScene(manageBook, "/main/sources/quanlyView.fxml", "/main/sources/css/quanly.css");
    }

    @FXML
    private void handleMuonsach() {
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
            
            borrowBookController controller = (borrowBookController) createScene1(manageBorrow, 
                "/main/sources/borrowBookView.fxml", "/main/sources/css/borrowBook.css");
            controller.setBookList(bookList);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    @FXML
    private void handleTrasach() {
        createScene(managePay, "/main/sources/returnBookView.fxml", "/main/sources/css/returnBook.css");
    }

    @FXML
    private void handleYeucau() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM request");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<requireNew> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                requireNew require = new requireNew(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4));
                bookList.add(require);
            }
            
            manageRequestController controller = (manageRequestController) createScene1(manageUser, 
                "/main/sources/manageRequest.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    @FXML
    private void handleNguoimuon() {
    	try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = prsttm.executeQuery();
            ObservableList<userLog> bookList = FXCollections.observableArrayList();
            while (rs.next()) {
                userLog userlog = new userLog(rs.getString(1), rs.getString(2) , rs.getString(3), rs.getString(4), rs.getString(5));
                bookList.add(userlog);
            }
            
            manageUserController controller = (manageUserController) createScene1(manageUser, 
                "/main/sources/manageUserView.fxml", "/main/sources/css/manageUser.css");
            controller.setBookList(bookList); 
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot execute: " + e.getMessage());
        }
    }

    @FXML
    private void handleInterfaceuser() {
        createScene(interfaceUser, "/main/sources/loginUserView.fxml", "/main/sources/css/login_signUser.css");
    }

    @FXML
    private void handleDangxuat() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/sources/loginView.fxml"));
            Scene newScene = new Scene(newRoot, 1536, 790);
            newScene.getStylesheets().add(getClass().getResource("/main/sources/css/login.css").toExternalForm());
            // Get the current stage
            Stage stage = (Stage) signout.getScene().getWindow();
            stage.setScene(newScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
