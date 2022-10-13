package com.lab5._52000643.entities;

import java.sql.*;

public class UserDAO {
    Connection conn;

    public UserDAO(){
        this.conn = new Database().conn;
    }

//    find user for: register + check exists when login
    public User findUser(String email, String password) throws SQLException {
        String query = "select * from users where email= ? and password = ?";
        PreparedStatement ptm = conn.prepareStatement(query);

        ptm.setString(1,email);
        ptm.setString(2,password);

        ResultSet data = ptm.executeQuery();
        User user = null;

        if(data.next()){
            user = new User(data.getInt(1),data.getString(2),data.getString(3),data.getString(4));
        }

        return user;
    }

    public boolean isEmailExisted(String email) throws SQLException{
        String query = "select * from users where email= ?";
        PreparedStatement ptm = conn.prepareStatement(query);

        ptm.setString(1,email);

        ResultSet data = ptm.executeQuery();

        if(data.next()) return true; //existed
        return false;
    }

    public int addUser(String email, String fullName, String password) throws SQLException {
        //check email ton tai chua
        if(isEmailExisted(email)){
            //true -> ton tai thi bao loi da ton tai
            return -1; //email da ton tai
        }

        String query = "insert into users(fullname, email, password) values (?,?,?)";
        PreparedStatement ptm = conn.prepareStatement(query);

        ptm.setString(1,fullName);
        ptm.setString(2,email);
        ptm.setString(3,password);

        int rowAffected = ptm.executeUpdate();
        return rowAffected; //>0 -> add success
    }
}
