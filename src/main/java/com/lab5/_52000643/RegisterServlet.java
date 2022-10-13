package com.lab5._52000643;

import com.lab5._52000643.entities.User;
import com.lab5._52000643.entities.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullname");
        String pass = req.getParameter("psw");
        String passRepeat = req.getParameter("psw-repeat");

        if(!pass.equals(passRepeat)){
            //flash -> error
            System.out.println("Register ~ 32: Pass khong trung repeat");
            return;
        }

        UserDAO userDao = new UserDAO();
        try {
            if(userDao.addUser(email, fullName, pass) > 0){
                //create session -> redirect home
                User user = userDao.findUser(email,pass);

                HttpSession session = req.getSession();
                session.setAttribute("userSession", user);

                resp.sendRedirect("/");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
