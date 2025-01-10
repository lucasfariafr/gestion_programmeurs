package com.programmeur.gestion_programmeurs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "programmeur")
public class Programmeur {

    @Id
    private int id;
    private String nom;
    private String prenom;
    private int anNaissance;
    private double salaire;
    private double prime;
    private String pseudo;

    public Programmeur() {
    }

    public Programmeur(int id, String nom, String prenom, int anNaissance, double salaire, double prime, String pseudo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Nom: " + nom + " | Prénom: " + prenom + " | Année de naissance: " + anNaissance +
                " | Salaire: " + salaire + " | Prime: " + prime + " | Pseudo: " + pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAnNaissance() {
        return anNaissance;
    }

    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public double getPrime() {
        return prime;
    }

    public void setPrime(double prime) {
        this.prime = prime;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
