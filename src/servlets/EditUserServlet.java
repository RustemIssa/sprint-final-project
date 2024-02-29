package servlets;

import db.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;

import java.io.IOException;

@WebServlet("/edituser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pass = req.getParameter("pass");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int id = Integer.parseInt(req.getParameter("id"));

        User user = DbConnection.getUserById(id);
        if (pass.equals(user.getPassword())){
            DbConnection.updateUser(name, email, id);
            resp.sendRedirect("/profile?id=" + id);
        } else {
            req.getRequestDispatcher("errdelete.jsp").forward(req,resp);
        }
    }
}
