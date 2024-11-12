package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.JDBC.JDBCSQL;
import main.java.model.lockaccount;

public class lockAccount implements UserAccountInterface<lockaccount> {
	
	public static lockAccount setNew() {
		return new lockAccount();
	}
	
	@Override
	public int insertSign(lockaccount t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertLog(lockaccount t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int insertLock(lockaccount t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			String sql = String.format("INSERT INTO lockaccount(userID, userName, passWord, fullname, explains) VALUES('%s', '%s', '%s', '%s','%s')", 
					t.getID(),t.getUsername(), t.getPassword(),t.getFullname(), t.getExplain());
			PreparedStatement prsttm = con.prepareStatement(sql);
			res = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(lockaccount t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(lockaccount t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchAcc(lockaccount t) {
		return null;
	}

	@Override
	public int searchId(lockaccount t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<lockaccount> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public lockaccount selectById(lockaccount t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<lockaccount> selectByCondition(String condition) {
		ArrayList<lockaccount> arr = new ArrayList<lockaccount>();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM lockaccount WHERE userName = ?");
			prsttm.setString(1, condition);
			ResultSet res = prsttm.executeQuery();
			if(res.next()) {
				lockaccount lock = new lockaccount(res.getString(2), res.getString(6));
				arr.add(lock);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<lockaccount> selectByCondition1(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
