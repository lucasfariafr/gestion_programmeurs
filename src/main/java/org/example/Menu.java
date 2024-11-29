package org.example;

import java.util.Scanner;

public class Menu {
    private final ActionsBDD actionsBDD = new ActionsBDDImpl();

    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\nMenu :");
            System.out.println("1. Afficher tous les programmeurs");
            System.out.println("2. Afficher un programmeurs");
            System.out.println("3. Supprimer un programmeur");
            System.out.println("4. Ajouter un programmeur");
            System.out.println("5. Modifier le salaire");
            System.out.println("6. Quitter le programme");

            System.out.print("Quel est votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1 -> afficherProgrammeurs();
                case 3 -> supprimerProgrammeur(scanner);
                default -> System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private void afficherProgrammeurs() {
        try {
            for (Programmeur p : actionsBDD.afficherProgrammeurs()) {
                System.out.println(p.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void supprimerProgrammeur(Scanner scanner) {
        System.out.print("Id du programmeur à supprimer : ");
        int id = scanner.nextInt();
        try {
            if (actionsBDD.supprimerProgrammeur(id)) {
                System.out.println("Programmeur supprimé.");
            } else {
                System.out.println("Programmeur non trouvé.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
