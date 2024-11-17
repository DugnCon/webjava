package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.JDBC.JDBCSQL;
import main.java.model.require;

public class requireUser implements UserAccountInterface<require> {
	
	public static requireUser setNew() {
		return new requireUser();
	}

	@Override
	public int insertSign(require t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertLog(require t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			String sql = String.format("INSERT INTO request(userName, request) VALUES ('%s','%s')", t.getUserName(), t.getRequest());
			PreparedStatement prsttm = con.prepareStatement(sql);
			res = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertLock(require t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(require t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(require t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchAcc(require t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int searchId(require t) {
		int res  = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT userName FROM user WHERE userName = ?");
			prsttm.setString(1, t.getUserName());
			ResultSet rs = prsttm.executeQuery();
	        if (rs.next()) {
	            res = 1;
	        }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<require> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public require selectById(require t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public require selectByUser(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<require> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<require> selectByCondition1(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
