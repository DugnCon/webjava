package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class loginAPI {
	// URL, tên đăng nhập, mật khẩu
    private static String url = "jdbc:mysql://localhost:3306/northwind?autoReconnect=true&useSSL=false";
    private static String username = "root";
    private static String password = "dungcon2005";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Nạp driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        if (con == null) {
            System.out.println("Thất bại!");
        } else {
            PreparedStatement prsttm = con.prepareStatement("WHERE ");
        }
    }
}
