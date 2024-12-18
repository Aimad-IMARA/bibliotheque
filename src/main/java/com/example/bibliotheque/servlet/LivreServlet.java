package com.example.bibliotheque.servlet;

import com.example.bibliotheque.entity.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.bibliotheque.service.LivreService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "livres", value = "/admin/livres/*")
@MultipartConfig
public class LivreServlet extends HttpServlet {
    private final LivreService livreService = new LivreService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            listLivres(request, response);
        } else if (pathInfo.equals("/addLivre")) {
            request.getRequestDispatcher("/WEB-INF/views/addLivre.jsp").forward(request, response);
        } else if (pathInfo.equals("/delete")) {
            doDelete(request, response);
        } else if (pathInfo.equals("/edit")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Livre livre = livreService.getLivreById(id);
            request.setAttribute("livre", livre);
            request.getRequestDispatcher("/WEB-INF/views/editLivre.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.equals(request.getParameter("_method"), "PUT")) {
            doPut(request, response);
        }else{
            String titre = request.getParameter("titre");
            String auteur = request.getParameter("auteur");
            String genre = request.getParameter("genre");

            Livre livre = new Livre();
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setGenre(genre);

            addLivre(livre);
            request.setAttribute("message", "Le livre a été ajouté!");
            request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        livreService.deleteLivre(id);
        request.setAttribute("message", "Le livre a été supprimé!");
        request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Livre livre = livreService.getLivreById(id);
        livre.setTitre(request.getParameter("titre"));
        livre.setAuteur(request.getParameter("auteur"));
        livre.setGenre(request.getParameter("genre"));

        livreService.updateLivre(livre);
        request.setAttribute("message", "Le livre a été modifié avec succès!");
        request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
    }

    private void listLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreService.getAllLivres();
        request.setAttribute("livres", livres);
        request.getRequestDispatcher("/WEB-INF/views/livres.jsp").forward(request, response);
    }

    private void addLivre(Livre livre){
        livre.setDisponible(true);
        livreService.saveLivre(livre);
    }

}
