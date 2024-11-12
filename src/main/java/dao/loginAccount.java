package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import main.java.JDBC.JDBCSQL;
import main.java.model.authorLog;

public class loginAccount implements AccountInterface<authorLog> {
	public static loginAccount getInstance() {
		return new loginAccount();
	}
	
	@Override
	public ArrayList selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(authorLog t) {
	    int res = 0;
	    try {
	        Connection con = JDBCSQL.getConnection();
	        PreparedStatement prsttm = con.prepareStatement("SELECT COUNT(*) FROM signup WHERE userName = ? AND passWord = ?");
	        
	        // Kiểm tra tính hợp lệ của username và password
	        if(t.isUserVallid() && t.isPassWordValid()) {
	            prsttm.setString(1, t.getUserName());
	            prsttm.setString(2, t.getPassWord());
	            
	            // Thực hiện truy vấn
	            ResultSet rs = prsttm.executeQuery();
	            if (rs.next()) {
	                res = rs.getInt(1); // lấy số lượng dòng trả về
	            }
	        } else {
	            System.out.println("Mật khẩu hoặc tài khoản không phù hợp");
	        }
	        con.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return res;
	}


	@Override
	public int update(authorLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(authorLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public authorLog selectById(authorLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<authorLog> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
