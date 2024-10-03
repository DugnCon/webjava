package main.java.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCSQL {
	private static String url = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
    private static String username = "root";
    private static String password = "dungcon2005";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Nạp driver
    	Connection con =null;
    	try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con =  DriverManager.getConnection(url, username, password);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return con;
    }

	public static void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
