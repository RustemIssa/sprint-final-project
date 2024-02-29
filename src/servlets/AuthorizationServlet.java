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

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("currentUser");
        if (user == null) {
            req.getRequestDispatcher("authorization.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String redirect = "/authorization?error";
        User user = DbConnection.getUserByEmailAndPassword(email, pass);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            redirect = "/profile";
        }
        resp.sendRedirect(redirect);
    }
}
