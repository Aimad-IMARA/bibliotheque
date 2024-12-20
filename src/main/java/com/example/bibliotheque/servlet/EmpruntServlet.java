package com.example.bibliotheque.servlet;

import com.example.bibliotheque.entity.Emprunt;
import com.example.bibliotheque.entity.Livre;
import com.example.bibliotheque.service.EmpruntService;
import com.example.bibliotheque.service.LivreService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "emprunts", value = "/admin/emprunts/*")
public class EmpruntServlet extends HttpServlet {
    private final EmpruntService empruntService = new EmpruntService();
    private final LivreService livreService = new LivreService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            listEmprunts(request, response);
        } else if (pathInfo.equals("/addEmprunt")) {
            List<Livre> livres = livreService.getAllLivres();
            request.setAttribute("livres", livres);
            request.getRequestDispatcher("/WEB-INF/views/addEmprunt.jsp").forward(request, response);
        } else if (pathInfo.equals("/retourner")) {
            doPut(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer livreId = Integer.parseInt(request.getParameter("livreId"));
        String email = request.getParameter("email");
        String numeroTel = request.getParameter("numeroTel");
        Date dateEmprunt = new Date(); // Date actuelle
        Date dateRetour = java.sql.Date.valueOf(request.getParameter("dateRetour")); // Format yyyy-MM-dd

        Livre livre = livreService.getLivreById(livreId);

        Emprunt emprunt = new Emprunt();
        emprunt.setEmail(email);
        emprunt.setNumeroTel(numeroTel);
        emprunt.setDateEmprunt(dateEmprunt);
        emprunt.setDateRetour(dateRetour);
        emprunt.setLivre(livre);
        livre.setDisponible(false);
        livreService.updateLivre(livre);
        empruntService.addEmprunt(emprunt);

        request.setAttribute("message", "L'emprunt a été ajouté avec succès!");
        request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empruntId = Integer.parseInt(request.getParameter("id"));
        empruntService.markAsReturned(empruntId);
        Livre livre = empruntService.getEmpruntById(empruntId).getLivre();
        livre.setDisponible(true);
        livreService.updateLivre(livre);
        request.setAttribute("message", "Le livre a été retourner.");
        request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
    }


    private void listEmprunts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Emprunt> emprunts = empruntService.getAllEmprunts();
        request.setAttribute("emprunts", emprunts);
        request.getRequestDispatcher("/WEB-INF/views/emprunts.jsp").forward(request, response);
    }
}
