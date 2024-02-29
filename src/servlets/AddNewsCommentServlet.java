package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/comment")
public class AddNewsCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newsId = Integer.parseInt(req.getParameter("news_id"));
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String comment = req.getParameter("comment");
        DbConnection.addNewsComment(newsId, userId, comment);
        resp.sendRedirect("/openpost?id=" + newsId);

    }
}
