package com.example.bibliotheque.entity;


import jakarta.persistence.Column;
import jakarta.persistence.*;

@Entity
@Table(name = "livres")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="titre", nullable = false)
    private String titre;

    @Column(name="auteur", nullable = false)
    private String auteur;

    @Column(name="disponible",nullable = false)
    private boolean disponible;

    @Column(name="genre",nullable = false)
    private String genre;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
