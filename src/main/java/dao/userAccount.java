package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.model.user;
import main.java.JDBC.JDBCSQL;
import main.java.model.author;
import main.java.model.borrow;

public class userAccount implements UserAccountInterface<user> {
	
	public static userAccount setNew() {
		return new userAccount();
	}
	
	@Override
	public int insert(user t) {
		int res = 0;
        try {
            Connection con = JDBCSQL.getConnection();
            Statement sttm = con.createStatement();
            if(t.isUserVallid() && t.isPassWordValid() && t.getPassword().equals(t.getReapeatPass())) {
            	// Thêm dấu nháy đơn cho các giá trị chuỗi
                String sql = String.format("INSERT INTO signup(userName, passWord, repeatPassWord, fullname) VALUES('%s', '%s', '%s', '%s')", t.getUsername(), t.getPassword(), t.getReapeatPass(), t.getFullname());
                res = sttm.executeUpdate(sql);
                System.out.println("Bạn đã thực thi: " + sql);
                System.out.println("Có " + res + " dòng bị thay đổi");
            }else {
            	System.out.println("Mật khẩu không phù hợp");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
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
	public String searchAcc(user t) {
		return "";
	}
	
	@Override
	public int searchId(user t) {
		int res  = 0;
		return res;
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
