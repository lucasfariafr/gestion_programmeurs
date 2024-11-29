package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionsBDDImpl implements ActionsBDD {
    private static final String SELECT_ALL = "SELECT * FROM Programmeur";
    private static final String DELETE = "DELETE FROM Programmeur WHERE id = ?";

    @Override
    public List<Programmeur> afficherProgrammeurs() throws SQLException {
        List<Programmeur> programmeurs = new ArrayList<>();
        try (Connection connection = Start.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                programmeurs.add(new Programmeur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getInt("anNaissance"),
                        resultSet.getDouble("salaire"),
                        resultSet.getDouble("prime"),
                        resultSet.getString("prime")
                ));
            }
        }
        return programmeurs;
    }

    @Override
    public boolean supprimerProgrammeur(int id) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
