package app.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Cette classe permet de se connecter à la base de données et de lancer le menu principal de l'application.
 */
public class Start {

    private static final String URL = "jdbc:mysql://localhost:3306/programmeursdb";
    private static final String USER = "lfaria";
    private static final String PASSWORD = "root";

    /**
     * Établit une connexion à la base de données MySQL.
     *
     * @return la connexion établie à la base de données.
     * @throws SQLException si une erreur se produit lors de la connexion à la base de données.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Lance le menu de l'application.
     *
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        new Menu().afficherMenu();
    }
}
