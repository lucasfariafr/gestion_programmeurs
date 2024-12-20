package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final ActionsBDD actionsBDD = new ActionsBDDImpl();

    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherOptionsMenu();
            choix = lireEntier(scanner);

            switch (choix) {
                case 1 -> afficherProgrammeurs();
                case 2 -> afficherProgrammeur(scanner);
                case 3 -> supprimerProgrammeur(scanner);
                case 4 -> ajouterProgrammeur(scanner);
                case 5 -> modifierSalaire(scanner);
                case 6 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 6);
    }

    private void afficherOptionsMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Afficher tous les programmeurs");
        System.out.println("2. Afficher un programmeur");
        System.out.println("3. Supprimer un programmeur");
        System.out.println("4. Ajouter un programmeur");
        System.out.println("5. Modifier le salaire");
        System.out.println("6. Quitter le programme");
        System.out.print("Quel est votre choix ? : ");
    }

    private void afficherProgrammeurs() {
        try {
            if (!estVide()) {
                actionsBDD.afficherProgrammeurs().forEach(System.out::println);
            } else {
                System.out.println("Aucun programmeur enregistré.");
            }
            ;
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage des programmeurs : " + e.getMessage());
        }
    }

    private void afficherProgrammeur(Scanner scanner) {
        if (!estVide()) {
            int id;

            System.out.print("Id du programmeur à afficher : ");

            do {
                id = lireEntier(scanner);

                try {
                    Programmeur programmeur = actionsBDD.afficherProgrammeur(id);
                    if (programmeur != null) {
                        System.out.println(programmeur);
                        break;
                    } else {
                        System.out.print("Programmeur introuvable. Veuillez réessayer : ");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la récupération du programmeur : " + e.getMessage());
                }
            } while (true);
        } else {
            System.out.println("Aucun programmeur enregistré.");
        }
    }

    private void supprimerProgrammeur(Scanner scanner) {
        if (!estVide()) {
            int id;

            System.out.print("Id du programmeur à supprimer : ");

            do {
                id = lireEntier(scanner);

                try {
                    if (actionsBDD.supprimerProgrammeur(id)) {
                        System.out.println("Suppression reussie !");
                        break;
                    } else {
                        System.out.print("Programmeur introuvable. Veuillez réessayer : ");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la suppression du programmeur : " + e.getMessage());
                }
            } while (true);
        } else {
            System.out.println("Aucun programmeur enregistré.");
        }
    }

    private void ajouterProgrammeur(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Nom : ");
        String nom = lireString(scanner);

        System.out.print("Prénom : ");
        String prenom = lireString(scanner);

        System.out.print("Année de naissance : ");
        int anNaissance = lireEntier(scanner);
        scanner.nextLine();

        System.out.print("Salaire : ");
        double salaire = lireDouble(scanner);
        scanner.nextLine();

        System.out.print("Prime : ");
        double prime = lireDouble(scanner);
        scanner.nextLine();

        System.out.print("Pseudo : ");
        String pseudo = lireString(scanner);

        Programmeur programmeur = new Programmeur(0, nom, prenom, anNaissance, salaire, prime, pseudo);

        try {
            System.out.println(actionsBDD.ajouterProgrammeur(programmeur) ? "Ajout réussi !" : "Échec de l'ajout.");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    private void modifierSalaire(Scanner scanner) {
        if (!estVide()) {
            int id;

            System.out.print("Id du programmeur : ");

            do {
                id = lireEntier(scanner);

                try {
                    Programmeur programmeur = actionsBDD.afficherProgrammeur(id);

                    if (programmeur != null) {
                        System.out.print("Nouveau salaire : ");
                        double nouveauSalaire = lireDouble(scanner);

                        if (actionsBDD.modifierSalaire(id, nouveauSalaire)) {
                            System.out.println("Mise à jour réussie.");
                        } else {
                            System.out.println("Échec de la mise à jour.");
                        }
                        break;
                    } else {
                        System.out.print("Programmeur introuvable. Veuillez réessayer : ");
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
                }
            } while (true);
        } else {
            System.out.println("Aucun programmeur enregistré.");
        }
    }

    private String lireString(Scanner scanner) {
        String texte;
        while (true) {
            texte = scanner.nextLine();
            if (texte.matches("^[a-zA-Z]+$")) {
                return texte;
            } else {
                System.out.print("Veuillez entrer seulement des lettres : ");
            }
        }
    }

    private int lireEntier(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Veuillez entrer un entier valide : ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double lireDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Veuillez entrer un nombre valide : ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private boolean estVide() {
        try {
            List<Programmeur> programmeurs = actionsBDD.afficherProgrammeurs();
            return programmeurs.isEmpty();
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de la base de données : " + e.getMessage());
            return true;
        }
    }
}
