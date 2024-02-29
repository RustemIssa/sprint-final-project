package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String email = req.getParameter("email");
       String pass1 = req.getParameter("pass1");
       String pass2 = req.getParameter("pass2");
       String redirect = "/reg?error";
       User u = DbConnection.getUserByEmail(email);

       if (pass1.equals(pass2)) {
           if (u==null) {
               User user = new User(1, email, pass1, name, 2);
               DbConnection.addUser(user);
               redirect = "/authorization";
           } else  redirect = "/reg?erroremail";;
       }
           resp.sendRedirect(redirect);

    }
}
