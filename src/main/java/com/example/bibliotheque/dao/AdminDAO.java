package com.example.bibliotheque.dao;

import com.example.bibliotheque.entity.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.NoResultException;

public class AdminDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioPU");

    public Admin findByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Admin a WHERE a.username = :username", Admin.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
