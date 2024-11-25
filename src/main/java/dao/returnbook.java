package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.JDBC.JDBCSQL;
import main.java.model.Return;

public class returnbook implements BorrowReturnInterface<Return>{
	
	public static returnbook setNew() {
		return new returnbook();
	}

	@Override
	public int insert(Return t) {
	    int result = 0;
	    try {
	        Connection con = JDBCSQL.getConnection();
	        String sql = "INSERT INTO payer (userID, borrowerID, bookCode, returnDate, userName) VALUES (?, ?, ?, ?, ?)";
	        
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        
	        // Thiết lập các tham số
	        pstmt.setString(1, t.getUserID());
	        pstmt.setString(2, t.getBorrowerID());
	        pstmt.setString(3, t.getBookCode());
	        pstmt.setDate(4, java.sql.Date.valueOf(t.getReturnDate()));
	        pstmt.setString(5, t.getUserName());

	        result = pstmt.executeUpdate();
	        
	        pstmt.close();
	        con.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public int search(Return t, String condition) {
		return 0;
	}

	@Override
	public int update(Return t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Return> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Return selectById(Return t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Return> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Return> selectByCondition1(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Delete(String condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Return> selectByCondition2(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int DeleteBookCode(String condition) {
		// TODO Auto-generated method stub
		return 0;
	}

}
