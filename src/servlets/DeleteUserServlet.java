package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/deleteuser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String pass = req.getParameter("pass");
        User user = DbConnection.getUserById(id);
        if (pass.equals(user.getPassword())) {
            DbConnection.deleteUser(id);
            resp.sendRedirect("/logout");
        } else {
            req.getRequestDispatcher("errdelete.jsp").forward(req, resp);
        }
    }
}
