package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.JDBC.JDBCSQL;
import main.java.model.borrow;

public class borrowedBooks implements BorrowReturnInterface<borrow> {
	
	public static borrowedBooks setNew() {
		return new borrowedBooks();
	}

	@Override
	public int insert(borrow t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			String sql = String.format("INSERT INTO borrowed_books(userID,bookCode,title) VALUES ('%s','%s','%s')", t.getUserID(), t.getBookCode(), t.getTitle());
			PreparedStatement prsttm = con.prepareStatement(sql);
			res = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int search(borrow t, String condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(borrow t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(String condition) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("DELETE FROM borrowed_books WHERE bookCode = ?");
			prsttm.setString(1, condition);
			res = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<borrow> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public borrow selectById(borrow t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<borrow> selectByCondition(String condition) {
		return null;
	}

	@Override
	public ArrayList<borrow> selectByCondition1(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<borrow> selectByCondition2(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
