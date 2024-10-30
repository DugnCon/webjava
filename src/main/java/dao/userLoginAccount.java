package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.JDBC.JDBCSQL;
import main.java.model.userLog;

public class userLoginAccount implements UserAccountInterface<userLog> {
	
	public static userLoginAccount setNew() {
		return new userLoginAccount();
	}

	@Override
	public int insertSign(userLog t) {
		int res = 0;
	    try {
	        Connection con = JDBCSQL.getConnection();
	        PreparedStatement prsttm = con.prepareStatement("SELECT COUNT(*) FROM signup WHERE userName = ? AND passWord = ?");
	        
	        // Kiểm tra tính hợp lệ của username và password
	        if(t.isUserVallid() && t.isPassWordValid()) {
	            prsttm.setString(1, t.getUsername());
	            prsttm.setString(2, t.getPassword());
	            
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
	public int insertLog(userLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(userLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(userLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchAcc(userLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchId(userLog t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public userLog selectById(userLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<userLog> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<userLog> selectByCondition(String condition) {
		ArrayList<userLog> arr = new ArrayList<>();
		try {
			int res = Integer.parseInt(condition);
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
			prsttm.setInt(1, res);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				userLog userlog = new userLog(rs.getString(1), rs.getString(2), rs.getString(5));
				arr.add(userlog);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}

	@Override
	public ArrayList<userLog> selectByCondition1(int condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
