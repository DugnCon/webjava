package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import main.java.model.user;
import main.java.JDBC.JDBCSQL;
import main.java.model.author;

public class userAccount implements UserAccountInterface<user,author> {
	
	public static userAccount setNew() {
		return new userAccount();
	}
	
	@Override
	public int insert(user t) {
		int res  = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			 String sql = String.format("INSERT INTO user(userName, passWord, fullname) VALUES('%s', '%s', '%s')", t.getUsername(), t.getPassword(), t.getFullname());
			 PreparedStatement prsttm = con.prepareStatement(sql);
			 res = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(user t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(user t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<user> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public user selectById(user t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<user> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
