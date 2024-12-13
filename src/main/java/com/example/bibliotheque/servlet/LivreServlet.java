package com.example.bibliotheque.servlet;

import com.example.bibliotheque.entity.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.bibliotheque.service.LivreService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "livres", value = "/livres/*")
public class LivreServlet extends HttpServlet {
    private final LivreService livreService = new LivreService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.equals("/")) {
            listLivres(request, response);
        }
    }

    private void listLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreService.getAllLivres();
        request.setAttribute("livres", livres);
        request.getRequestDispatcher("/WEB-INF/views/livres.jsp").forward(request, response);
    }
}
