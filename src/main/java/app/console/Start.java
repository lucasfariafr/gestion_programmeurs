package app.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Start {

    private static final String URL = "jdbc:mysql://localhost:3306/programmeursdb";
    private static final String USER = "lfaria";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        new Menu().afficherMenu();
    }
}