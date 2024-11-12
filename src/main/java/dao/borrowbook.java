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
import main.java.model.borrow;
public class borrowbook implements BorrowReturnInterface<borrow> {
	
	public static borrowbook setNew() {
		return new borrowbook();
	}
	
	@Override
	public int insert(borrow t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("INSERT INTO borrower (borrowerID, userID, bookCode, borrowDate, returnDate, userName, status, phonenum) "
					                                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			prsttm.setString(1, t.getBorrowerID());
			prsttm.setString(2, t.getUserID());
			prsttm.setString(3, t.getBookCode());
			prsttm.setString(4, t.getBorrowDate());
			prsttm.setString(5, t.getReturnDate());
			prsttm.setString(6, t.getUserName());
			prsttm.setString(7, t.getStatus());
			prsttm.setString(8, t.getPhonenum());
			res  = prsttm.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public ArrayList<borrow> selectByCondition(String condition) {
		ArrayList<borrow> arr = new ArrayList<>();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM borrower WHERE borrowerID = ?");
			prsttm.setString(1, condition);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				borrow Borrow = new borrow(rs.getString(3), rs.getString(8), rs.getString(4), rs.getString(5), rs.getString(6));
				arr.add(Borrow);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

	@Override
	public ArrayList<borrow> selectByCondition1(String condition) {
		ArrayList<borrow> arr = new ArrayList<>();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM borrower WHERE borrowerID = ?");
			prsttm.setString(1, condition);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				borrow Borrow = new borrow(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				arr.add(Borrow);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
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
			PreparedStatement prsttm = con.prepareStatement("DELETE FROM borrower WHERE borrowerID = ?");
			prsttm.setString(1,condition);
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

}
