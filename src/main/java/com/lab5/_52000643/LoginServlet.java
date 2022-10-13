package com.lab5._52000643;

import com.lab5._52000643.entities.User;
import com.lab5._52000643.entities.UserDAO;
import com.lab5._52000643.utils.FlashMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Cookie login info
        Cookie loginCookies[] = req.getCookies();
        for(Cookie cookie : loginCookies){
            if(cookie.getName().equals("emailCookie")){
                req.setAttribute("emailLoginCookie", cookie.getValue());
            }

            if(cookie.getName().equals("passCookie")){
                req.setAttribute("passLoginCookie", cookie.getValue());
            }
        }

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get data from login form
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember  = req.getParameter("remember");

        UserDAO userDao = new UserDAO();

        try {
            User user = userDao.findUser(email,password);
            //System.out.println(user.toString());
            HttpSession session = req.getSession();

            if(user == null){
                //tien hanh thong bao' sai email or pass
                //luu error vo session
                FlashMessage flash =
                        new FlashMessage("danger", "Validation Error", "Wrong email or password");
//                session.setAttribute("loginError", flash);

                req.setAttribute("flashLogin", flash);

                req.getRequestDispatcher("login.jsp").forward(req,resp);
                return;
            }

            //truong hop login success -> trang chu -> session
            session.setAttribute("userSession", user);

            if(remember != null){
                //luu acc vao cookie
                Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
                Cookie passCookie = new Cookie("passCookie", user.getPassword());
                Cookie userIdCookie = new Cookie("userIdCookie", Integer.toString(user.getId()));

                emailCookie.setMaxAge(2592000); //30 days
                passCookie.setMaxAge(2592000);
                userIdCookie.setMaxAge(2592000);

                resp.addCookie(emailCookie);
                resp.addCookie(passCookie);
                resp.addCookie(userIdCookie);
            }

            //truong hop thanh cong
            req.removeAttribute("flashLogin");
            resp.sendRedirect("/");
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
