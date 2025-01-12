package app.console;

import com.programmeur.webapp.model.Programmeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface {@link ActionsBDD}, permettant de gérer les actions CRUD sur les programmeurs dans la base de données.
 * Cette classe gère les opérations de récupération, d'insertion, de suppression et de mise à jour des programmeurs.
 */
public class ActionsBDDImpl implements ActionsBDD {

    // Requêtes SQL
    private static final String SELECT_ALL = "SELECT * FROM programmeur";
    private static final String SELECT_BY_ID = "SELECT * FROM programmeur WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM programmeur WHERE id = ?";
    private static final String INSERT_PROGRAMMEUR = "INSERT INTO programmeur (nom, prenom, an_naissance, salaire, prime, pseudo) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SALAIRE = "UPDATE programmeur SET salaire = ? WHERE id = ?";

    /**
     * Récupère la liste de tous les programmeurs dans la base de données.
     *
     * @return une liste de programmeurs.
     * @throws SQLException si une erreur survient lors de la récupération des données.
     */
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

    /**
     * Récupère un programmeur spécifique en fonction de son identifiant.
     *
     * @param id l'identifiant du programmeur à récupérer.
     * @return le programmeur correspondant à l'identifiant, ou null si aucun programmeur n'est trouvé.
     * @throws SQLException si une erreur survient lors de la récupération des données.
     */
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

    /**
     * Supprime un programmeur de la base de données en fonction de son identifiant.
     *
     * @param id l'identifiant du programmeur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de la suppression des données.
     */
    @Override
    public boolean supprimerProgrammeur(int id) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * Ajoute un programmeur à la base de données.
     *
     * @param programmeur l'objet programmeur à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de l'ajout des données.
     */
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

    /**
     * Modifie le salaire d'un programmeur en fonction de son identifiant.
     *
     * @param id             l'identifiant du programmeur dont le salaire doit être modifié.
     * @param nouveauSalaire le nouveau salaire à attribuer au programmeur.
     * @return true si la mise à jour a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de la mise à jour des données.
     */
    @Override
    public boolean modifierSalaire(int id, double nouveauSalaire) throws SQLException {
        try (Connection connection = Start.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SALAIRE)) {
            preparedStatement.setDouble(1, nouveauSalaire);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    /**
     * Mappe un {@link ResultSet} en un objet {@link Programmeur}.
     *
     * @param resultSet le {@link ResultSet} contenant les données du programmeur.
     * @return un objet {@link Programmeur} correspondant aux données du {@link ResultSet}.
     * @throws SQLException si une erreur survient lors de l'extraction des données.
     */
    private Programmeur mapperProgrammeur(ResultSet resultSet) throws SQLException {
        return new Programmeur(
                resultSet.getInt("id"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getInt("an_naissance"),
                resultSet.getDouble("salaire"),
                resultSet.getDouble("prime"),
                resultSet.getString("pseudo")
        );
    }
}
