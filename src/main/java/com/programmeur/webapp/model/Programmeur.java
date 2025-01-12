package com.programmeur.webapp.model;

import jakarta.persistence.*;

/**
 * Représente un programmeur dans le système.
 * Cette classe est utilisée pour mapper les données d'un programmeur avec la table 'programmeur' dans la base de données.
 */
@Entity
@Table(name = "programmeur")
public class Programmeur {

    // Identifiant unique du programmeur dans la base de données
    @Id
    private int id;

    // Nom du programmeur
    private String nom;

    // Prénom du programmeur
    private String prenom;

    // Année de naissance du programmeur
    private int anNaissance;

    // Salaire du programmeur
    private double salaire;

    // Prime du programmeur
    private double prime;

    // Pseudo du programmeur
    private String pseudo;

    /**
     * Constructeur par défaut.
     * Utilisé par JPA pour instancier l'objet sans initialiser les attributs.
     */
    public Programmeur() {
    }

    /**
     * Constructeur avec paramètres pour initialiser tous les attributs de l'objet Programmeur.
     *
     * @param id          l'identifiant du programmeur.
     * @param nom         le nom du programmeur.
     * @param prenom      le prénom du programmeur.
     * @param anNaissance l'année de naissance du programmeur.
     * @param salaire     le salaire du programmeur.
     * @param prime       la prime du programmeur.
     * @param pseudo      le pseudo du programmeur.
     */
    public Programmeur(int id, String nom, String prenom, int anNaissance, double salaire, double prime, String pseudo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
    }

    /**
     * Retourne une chaîne de caractères représentant l'objet Programmeur sous forme lisible.
     *
     * @return une chaîne contenant les informations du programmeur.
     */
    @Override
    public String toString() {
        return "Id: " + id + " | Nom: " + nom + " | Prénom: " + prenom + " | Année de naissance: " + anNaissance +
                " | Salaire: " + salaire + " | Prime: " + prime + " | Pseudo: " + pseudo;
    }

    /**
     * Obtient l'identifiant du programmeur.
     *
     * @return l'id du programmeur.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant du programmeur.
     *
     * @param id l'identifiant du programmeur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom du programmeur.
     *
     * @return le nom du programmeur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du programmeur.
     *
     * @param nom le nom du programmeur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom du programmeur.
     *
     * @return le prénom du programmeur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du programmeur.
     *
     * @param prenom le prénom du programmeur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient l'année de naissance du programmeur.
     *
     * @return l'année de naissance du programmeur.
     */
    public int getAnNaissance() {
        return anNaissance;
    }

    /**
     * Définit l'année de naissance du programmeur.
     *
     * @param anNaissance l'année de naissance du programmeur.
     */
    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    /**
     * Obtient le salaire du programmeur.
     *
     * @return le salaire du programmeur.
     */
    public double getSalaire() {
        return salaire;
    }

    /**
     * Définit le salaire du programmeur.
     *
     * @param salaire le salaire du programmeur.
     */
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    /**
     * Obtient la prime du programmeur.
     *
     * @return la prime du programmeur.
     */
    public double getPrime() {
        return prime;
    }

    /**
     * Définit la prime du programmeur.
     *
     * @param prime la prime du programmeur.
     */
    public void setPrime(double prime) {
        this.prime = prime;
    }

    /**
     * Obtient le pseudo du programmeur.
     *
     * @return le pseudo du programmeur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Définit le pseudo du programmeur.
     *
     * @param pseudo le pseudo du programmeur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
