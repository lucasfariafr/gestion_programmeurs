package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionsBDDImpl implements ActionsBDD {

    private static final String SELECT_ALL = "SELECT * FROM Programmeur";
    private static final String SELECT_BY_ID = "SELECT * FROM Programmeur WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM Programmeur WHERE id = ?";
    private static final String INSERT_PROGRAMMEUR = "INSERT INTO Programmeur (nom, prenom, anNaissance, salaire, prime, pseudo) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SALAIRE = "UPDATE Programmeur SET salaire = ? WHERE id = ?";

    @Override
    public List<Programmeur> afficherProgrammeurs() throws SQLException {
        List<Programmeur> programmeurs = new ArrayList<>();
        try (Connection connection = Start.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                programmeurs.add(mapperProgrammeur(resultSet));
            }
        }
        return programmeurs;
    }

    @Override
    public Programmeur afficherProgrammeur(int id) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapperProgrammeur(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public boolean supprimerProgrammeur(int id) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean ajouterProgrammeur(Programmeur programmeur) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROGRAMMEUR)) {
            preparedStatement.setString(1, programmeur.getNom());
            preparedStatement.setString(2, programmeur.getPrenom());
            preparedStatement.setInt(3, programmeur.getAnNaissance());
            preparedStatement.setDouble(4, programmeur.getSalaire());
            preparedStatement.setDouble(5, programmeur.getPrime());
            preparedStatement.setString(6, programmeur.getPseudo());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean modifierSalaire(int id, double nouveauSalaire) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SALAIRE)) {
            preparedStatement.setDouble(1, nouveauSalaire);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    private Programmeur mapperProgrammeur(ResultSet resultSet) throws SQLException {
        return new Programmeur(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getInt("anNaissance"),
                resultSet.getDouble("salaire"),
                resultSet.getDouble("prime"),
                resultSet.getString("pseudo")
        );
    }
}
