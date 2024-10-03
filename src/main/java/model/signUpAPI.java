package main.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class signUpAPI extends author {
    public signUpAPI(String userName, String passWord, String repeatPassWord) {
        super(userName, passWord, repeatPassWord);
    }

    // URL, tên đăng nhập, mật khẩu
    private static String url = "jdbc:mysql://localhost:3306/library?autoReconnect=true&useSSL=false";
    private static String username = "root";
    private static String password = "dungcon2005";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Nạp driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        author Author = new author();
        try (Connection con = getConnection()) {
            if (con == null) {
                System.out.println("Thất bại!");
                return;
            }
            Statement sttm = con.createStatement();
            String update = String.format("INSERT INTO signup(userName, passWord, repeatPassWord) VALUES('%s', '%s', '%s')",
                    Author.getUserName(), Author.getPassWord(), Author.getRepeatPassWord());
            sttm.executeUpdate(update);
            ResultSet rs = sttm.executeQuery("SELECT * FROM signup");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
