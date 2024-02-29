package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/userdetails")
public class UserDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        int id = Integer.parseInt(req.getParameter("id"));
        if (user.getRoleId() == 1) {
            req.setAttribute("user", DbConnection.getUserById(id));
            req.getRequestDispatcher("userdetails.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        }
    }
}
