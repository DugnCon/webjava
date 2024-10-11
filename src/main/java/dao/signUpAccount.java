package main.java.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import main.java.JDBC.JDBCSQL;
import main.java.model.author;

public class signUpAccount implements AccountInterface<author> {
    
    public static signUpAccount getInstance() {
        return new signUpAccount();
    }
    /**Thông báo khi đăg kí thành công*/
    public void AlertComplete() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText("Đăng kí thành công");
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }
    /**Thông báo khi không đăng kí được*/
    public void AlertUnComplete() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText("Đăng kí không thành công");
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }
    @Override
    public int insert(author t) {
        int res = 0;
        try {
            Connection con = JDBCSQL.getConnection();
            Statement sttm = con.createStatement();
            if(t.isUserVallid() && t.isPassWordValid() && t.getPassWord().equals(t.getRepeatPassWord())) {
            	// Thêm dấu nháy đơn cho các giá trị chuỗi
            	
                String sql = String.format("INSERT INTO signup(userName, passWord, repeatPassWord) VALUES('%s', '%s', '%s')", t.getUserName(), t.getPassWord(), t.getRepeatPassWord());
                res = sttm.executeUpdate(sql);
                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + res + " dòng bị thay đổi");
            }else {
            	System.out.println("Mật khẩu không phù hợp");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(author t) {
        return 0;
    }

    @Override
    public int delete(author t) {
        return 0;
    }

    @Override
    public ArrayList<author> selectAll() {
        return null;
    }

    @Override
    public author selectById(author t) {
        return null;
    }

    @Override
    public ArrayList<author> selectByCondition(String condition) {
        return null;
    }
}
