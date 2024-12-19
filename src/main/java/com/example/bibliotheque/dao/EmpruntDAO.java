package com.example.bibliotheque.dao;

import com.example.bibliotheque.entity.Emprunt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EmpruntDAO {
    private final EntityManagerFactory entityManagerFactory;

    public EmpruntDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("biblioPU");
    }

    public void addEmprunt(Emprunt emprunt) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            emprunt.setStatus(Emprunt.Status.ACTIF);
            entityManager.persist(emprunt);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }


    public List<Emprunt> getAllEmprunts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Emprunt getEmpruntById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Emprunt.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void deleteEmprunt(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Emprunt emprunt = entityManager.find(Emprunt.class, id);
            if (emprunt != null) {
                entityManager.remove(emprunt);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public void updateStatusToRetour(int empruntId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Emprunt emprunt = entityManager.find(Emprunt.class, empruntId);
            if (emprunt != null) {
                emprunt.setStatus(Emprunt.Status.RETOUR);
                entityManager.merge(emprunt);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

}
