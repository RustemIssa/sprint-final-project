package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/addnews")
public class AddNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user == null) {
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        } else {
            req.setAttribute("category", DbConnection.getCategory());
            req.getRequestDispatcher("addnews.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        int catId = Integer.parseInt(req.getParameter("category"));

        DbConnection.addNews(title, text, date, catId);
        resp.sendRedirect("/news");

    }
}
