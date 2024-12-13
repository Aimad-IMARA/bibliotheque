package com.example.bibliotheque.service;

import com.example.bibliotheque.entity.Livre;
import com.example.bibliotheque.dao.LivreDAO;

import java.util.List;

public class LivreService {
    private LivreDAO livreDAO = new LivreDAO();

    public void saveLivre(Livre livre) {
        livreDAO.save(livre);
    }

    public List<Livre> getAllLivres() {
        return livreDAO.findAll();
    }
}
