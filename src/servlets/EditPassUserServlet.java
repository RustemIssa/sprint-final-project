package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/editpassuser")
public class EditPassUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("pass");
        String pass1 = req.getParameter("pass1");
        String pass2 = req.getParameter("pass2");
        int id = Integer.parseInt(req.getParameter("id"));

        User user = DbConnection.getUserById(id);
        if (pass.equals(user.getPassword()) && pass1.equals(pass2)) {
            DbConnection.updateUserPass(pass1, id);
            resp.sendRedirect("/profile?id=" + id);
        } else {
            req.getRequestDispatcher("errdelete.jsp").forward(req, resp);
        }
    }
}
