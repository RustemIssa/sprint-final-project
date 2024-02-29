package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/users")
public class UsersAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user.getRoleId() == 1) {
            req.setAttribute("users", DbConnection.getUsers());
            req.getRequestDispatcher("users.jsp").forward(req, resp);

        } else {
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        }
    }
}
