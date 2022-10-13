package com.lab5._52000643.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn;

    public ProductDAO(){
        conn = new Database().conn;
    }

    public Integer add (String name, float price) throws SQLException {
        String myAddQuery = "insert into products (name, price) values (?,?);";
        PreparedStatement ptm = conn.prepareStatement(myAddQuery);

        ptm.setString(1, name);
        ptm.setFloat(2, price);

        return ptm.executeUpdate(); //tra ve 1 thi alert them thanh cong
    }

    public List<Product> allProducts() throws SQLException {
        String query = "select * from products";
        PreparedStatement ptm = conn.prepareStatement(query);

        ResultSet data = ptm.executeQuery();

        List<Product> products = new ArrayList<>();
        Product product = null;

       while(data.next()){
            product = new Product(data.getInt(1), data.getString(2), data.getFloat(3));
            products.add(product);
        }

        return products;
    }

    public Integer delete (int id) throws SQLException {
        String query = "delete from products where id = ?";
        PreparedStatement ptm = conn.prepareStatement(query);

        ptm.setInt(1, id);
        int data = ptm.executeUpdate();
        return data;
    }

}
