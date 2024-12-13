package com.example.bibliotheque.dao;

import com.example.bibliotheque.entity.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class LivreDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioPU");

    public void save(Livre livre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(livre);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Livre> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livre l", Livre.class).getResultList();
        } finally {
            em.close();
        }
    }
}
