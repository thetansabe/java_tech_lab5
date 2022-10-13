package com.lab5._52000643;

import com.lab5._52000643.entities.Product;
import com.lab5._52000643.entities.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    ProductDAO productDao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = null;

        try {
            products = productDao.allProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //handle add
        String productName = req.getParameter("product_name");
        Float productPrice = Float.parseFloat(req.getParameter("product_price"));

        try {
            if(productDao.add(productName, productPrice) == 1){
                //flash add success
                System.out.println("add thanh cong");
                resp.sendRedirect("/home");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("product_id"));

        try {
            if(productDao.delete(productId) == 1){
                //flash add success
                System.out.println("xoa thanh cong");
            }else{
                System.out.println("xoa failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
