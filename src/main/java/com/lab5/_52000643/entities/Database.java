package com.lab5._52000643.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    Connection conn;

    public Database(){
        conn = initDbConn();
    }

    public Connection initDbConn(){
        String hostname = "127.0.0.1:3306";
        String dbname = "lab5_javatech";

        String dbUserName = "root";
        String dbPassword = "1478963aMysql";

        String url = String.format("jdbc:mysql://%s/%s?useSSL=false", hostname, dbname);

        try {
//          //tomcat phien ban cao (7+) can dang ki driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
            Connection conn = DriverManager.getConnection(url, dbUserName, dbPassword);
            System.out.println(">>> Ket noi DB thanh cong den: " + url);

            return conn;

        }catch (SQLException e){
            System.out.println(">>> Loi ket noi DB " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
