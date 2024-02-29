package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

@WebServlet("/openpost")
public class OpenPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        int id = Integer.parseInt(req.getParameter("id"));
        if (user != null){
            req.setAttribute("news", DbConnection.getNewsById(id));
            req.setAttribute("comments", DbConnection.getCommentsByNewsId(id));
            req.getRequestDispatcher("openpost.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("authorization.jsp").forward(req, resp);
    }
}
