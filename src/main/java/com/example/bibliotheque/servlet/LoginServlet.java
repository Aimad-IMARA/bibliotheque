package com.example.bibliotheque.servlet;

import com.example.bibliotheque.dao.AdminDAO;
import com.example.bibliotheque.entity.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private final AdminDAO adminDAO = new AdminDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/admin/livres");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminDAO.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath()+"/admin/livres");
        } else {
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            doGet(request, response);
        }
    }
}
