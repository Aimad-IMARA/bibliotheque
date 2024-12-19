package com.example.bibliotheque.service;

import com.example.bibliotheque.dao.EmpruntDAO;
import com.example.bibliotheque.entity.Emprunt;

import java.util.List;

public class EmpruntService {
    private final EmpruntDAO empruntDAO = new EmpruntDAO();

    public void addEmprunt(Emprunt emprunt) {
        empruntDAO.addEmprunt(emprunt);
    }

    public List<Emprunt> getAllEmprunts() {
        return empruntDAO.getAllEmprunts();
    }

    public Emprunt getEmpruntById(int id) {
        return empruntDAO.getEmpruntById(id);
    }

    public void deleteEmprunt(int id) {
        empruntDAO.deleteEmprunt(id);
    }

    public void markAsReturned(int empruntId) {
        empruntDAO.updateStatusToRetour(empruntId);
    }

}
