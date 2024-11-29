package org.example;

public class Programmeur {
    private String nom;
    private String prenom;
    private String anNaissance;
    private String salaire;
    private String prime;
    private String pseudo;

    public Programmeur(String nom, String prenom, String anNaissance, String salaire, String prime, String pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + " | Prénom: " + prenom + " | Année de naissance: " + anNaissance +
                " | Salaire: " + salaire + " | Prime: " + prime + " | Pseudo: " + pseudo;
    }
}

