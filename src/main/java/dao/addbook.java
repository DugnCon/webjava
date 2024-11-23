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
import main.java.model.alter;

public class addbook implements AddBookInterface<add> {
	
	public static addbook setNewAdd() {
		return new addbook();
	}

	 @Override
	 public int insert1(add t) {
	      int res = 0;
	        try {
	            Connection con = JDBCSQL.getConnection();
	            PreparedStatement prsttm = con.prepareStatement("INSERT INTO book "
	                    + "(bookCode, title, chapter, author, genre, publisher, releaseYear, quantity) "
	                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	            
	            int Quantity = Integer.parseInt(t.getchapBook());
	            int Chapter = Integer.parseInt(t.getchapBook());
	            
	            prsttm.setString(1, t.getbookCode());
	            prsttm.setString(2, t.getnameBook());
	            prsttm.setInt(3, Quantity);
	            prsttm.setString(4, t.getnameAuthor());
	            prsttm.setString(5, t.getstyleBook());
	            prsttm.setString(6, t.getPublisher());
	            prsttm.setString(7, t.getreleaseYear());
	            prsttm.setInt(8, Chapter);
	            res = prsttm.executeUpdate();
	            
	            ResultSet generatedKeys = prsttm.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int id = generatedKeys.getInt(1);
	                t.setID(id);
	            }
	            
	            con.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	     return res;
	}
	 
	@Override
	public String search(add t) {
		String res = "";
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode  = ?");
			prsttm.setString(1, t.getbookCode());
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				res = rs.getString(2);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public String search2(add t) {
		String res = "";
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode  = ?");
			prsttm.setString(1, t.getbookCode());
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				res = rs.getString(9);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int update(add t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("UPDATE book SET bookCode = ?, title = ?"
					                                        + ", chapter = ?, author = ?, genre = ?, publisher = ?"
					                                        + ", releaseYear = ?, quantity = ?"
					                                        + " WHERE bookCode = ?");
			prsttm.setString(1, t.getbookCode());
			prsttm.setString(2, t.getnameBook());
			prsttm.setString(3, t.getchapBook());
			prsttm.setString(4, t.getnameAuthor());
			prsttm.setString(5, t.getstyleBook());
			prsttm.setString(6, t.getPublisher());
			prsttm.setString(7, t.getreleaseYear());
			prsttm.setString(8, t.getQuantity());
			prsttm.setString(9, t.getbookCode());
			
			res = prsttm.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int update2(add t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("UPDATE book"
					          + " SET quantity = ?"
					          + " WHERE bookCode = ?");
			int Quantity = Integer.parseInt(t.getQuantity()) - 1;
			if(Quantity >= 1) {
				prsttm.setString(1,String.valueOf(Quantity));
				prsttm.setString(2, t.getbookCode());
				res = prsttm.executeUpdate();
			}else {
				return 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int update3(add t) {
		int res = 0;
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("UPDATE book"
					          + " SET quantity = ?"
					          + " WHERE bookCode = ?");
			int Quantity = Integer.parseInt(t.getQuantity()) + 1;
			if(Quantity >= 1) {
				prsttm.setString(1,String.valueOf(Quantity));
				prsttm.setString(2, t.getbookCode());
				res = prsttm.executeUpdate();
			}else {
				return 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int Delete(add t) {
		int res = 0;
        try {
            Connection con = JDBCSQL.getConnection();
            PreparedStatement prsttm = con.prepareStatement("DELETE FROM book WHERE bookCode = ?");
            prsttm.setString(1, t.getbookCode());
            res = prsttm.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
       return res;
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
		ArrayList<alter> arr = new ArrayList<>();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode = ?");
			prsttm.setString(1, condition);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				alter Alter = new alter(rs.getString(2), rs.getString(3), rs.getString(4),
						               rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						               rs.getString(9));
				arr.add(Alter);
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	@Override
	public ArrayList selectByCondition1(String condition) {
		ArrayList<alter> arr = new ArrayList<>();
		try {
			Connection con = JDBCSQL.getConnection();
			PreparedStatement prsttm = con.prepareStatement("SELECT * FROM book WHERE bookCode = ?");
			prsttm.setString(1, condition);
			ResultSet rs = prsttm.executeQuery();
			if(rs.next()) {
				alter Alter = new alter(rs.getString(3), rs.getString(4),
						               rs.getString(5),rs.getString(9));
				arr.add(Alter);
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

}
