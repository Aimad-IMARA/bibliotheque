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
    public Livre findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Livre.class, id);
        } finally {
            em.close();
        }
    }

    public void update(Livre livre) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(livre); // merge updates the entity
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Livre livre = em.find(Livre.class, id);
            if (livre != null) {
                em.remove(livre);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Livre> searchByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM Livre l WHERE LOWER(l.titre) LIKE :title", Livre.class)
                    .setParameter("title", "%" + title.toLowerCase() + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
