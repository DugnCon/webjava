package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import main.java.JDBC.JDBCSQL;
import main.java.model.add;

public class addbook implements AccountInterface<add> {
	
	public static addbook setNewAdd() {
		return new addbook();
	}
	
	/**Thông báo khi đăg kí thành công*/
    public void AlertComplete() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Thông báo");
    	alert.setHeaderText("Thêm sách thành công");
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
    	alert.setHeaderText("Thêm sách không thành công");
    	alert.setContentText("Chọn lựa chọn của bạn");
    	
    	ButtonType buttonTypeOk = new ButtonType("OK",ButtonData.YES);
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
    	alert.show();
    }

	 @Override
	    public int insert(add t) {
	        int res = 0;
	        try {
	            Connection con = JDBCSQL.getConnection();
	            PreparedStatement prsttm = con.prepareStatement("INSERT INTO book "
	                    + "(bookCode, title, chapter, author, genre, publisher, releaseYear, quantity) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

	            prsttm.setString(1, t.getbookCode());
	            prsttm.setString(2, t.getnameBook());
	            prsttm.setInt(3, Integer.parseInt(t.getchapBook()));
	            prsttm.setString(4, t.getnameAuthor());
	            prsttm.setString(5, t.getstyleBook());
	            prsttm.setString(6, t.getPublisher());
	            prsttm.setString(7, t.getreleaseYear());
	            prsttm.setInt(8, Integer.parseInt(t.getQuantity()));
	            res = prsttm.executeUpdate();
	            
	            con.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return res;
	    }

	@Override
	public int update(add t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(add t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public add selectById(add t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
