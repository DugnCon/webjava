package main.java.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import main.java.JDBC.JDBCSQL;
import main.java.model.author;

public class signUpAccount implements AccountInterface<author> {
    
    public static signUpAccount getInstance() {
        return new signUpAccount();
    }
    
    @Override
    public int insert(author t) {
        int res = 0;
        try {
            Connection con = JDBCSQL.getConnection();
            Statement sttm = con.createStatement();
            // Thêm dấu nháy đơn cho các giá trị chuỗi
            String sql = String.format("INSERT INTO signup(userName, passWord, repeatPassWord) VALUES('%s', '%s', '%s')", t.getUserName(), t.getPassWord(), t.getRepeatPassWord());
            res = sttm.executeUpdate(sql);
            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + res + " dòng bị thay đổi");
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(author t) {
        return 0;
    }

    @Override
    public int delete(author t) {
        return 0;
    }

    @Override
    public ArrayList<author> selectAll() {
        return null;
    }

    @Override
    public author selectById(author t) {
        return null;
    }

    @Override
    public ArrayList<author> selectByCondition(String condition) {
        return null;
    }
}
