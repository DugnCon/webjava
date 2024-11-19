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
		return 0;
	}
	
	@Override
	public int insertLog(userLog t) {
		int res = 0;
	    try {
	        Connection con = JDBCSQL.getConnection();
	        PreparedStatement prsttm = con.prepareStatement("SELECT COUNT(*) FROM user WHERE userName = ? AND passWord = ?");
	        if(t.isUserVallid() && t.isPassWordValid()) {
	            prsttm.setString(1, t.getUsername());
	            prsttm.setString(2, t.getPassword());
	            ResultSet rs = prsttm.executeQuery();
	            if (rs.next()) {
	                res = rs.getInt(1);
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
	public int insertLock(userLog t) {
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
		StringBuilder ID = new StringBuilder();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT userID FROM user WHERE userName = ?");
			prsttm.setString(1, t.getUsername());
			ResultSet res = prsttm.executeQuery();
			if(res.next()) {
				ID.append(res.getString(1));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(ID);
	}

	@Override
	public int searchId(userLog t) {
		return 0;
	}

	@Override
	public userLog selectById(userLog t) {
		userLog log = null;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
			prsttm.setString(1, t.getID());
			ResultSet res = prsttm.executeQuery();
			if(res.next()) {
				log = new userLog(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
	}
	
	@Override
	public userLog selectByUser(String condition) {
		userLog log = null;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user WHERE userName = ?");
			prsttm.setString(1, condition);
			ResultSet res = prsttm.executeQuery();
			if(res.next()) {
				log = new userLog(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
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
	public ArrayList<userLog> selectByCondition1(String condition) {
		ArrayList<userLog> arr = new ArrayList<>();
		try {
			int res = Integer.parseInt(condition);
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM user WHERE userID = ?");
			prsttm.setInt(1, res);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				userLog userlog = new userLog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				arr.add(userlog);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}

}
