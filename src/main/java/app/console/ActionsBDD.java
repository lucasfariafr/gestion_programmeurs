package app.console;

import com.programmeur.webapp.model.Programmeur;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface représentant les actions sur la base de données pour la gestion des programmeurs.
 */
public interface ActionsBDD {

    /**
     * Affiche tous les programmeurs présents dans la base de données.
     *
     * @return une liste de programmeurs.
     * @throws SQLException si une erreur survient lors de la récupération des données.
     */
    List<Programmeur> afficherProgrammeurs() throws SQLException;

    /**
     * Affiche un programmeur spécifique basé sur son identifiant.
     *
     * @param id l'identifiant du programmeur à afficher.
     * @return le programmeur correspondant à l'identifiant.
     * @throws SQLException si une erreur survient lors de la récupération des données.
     */
    Programmeur afficherProgrammeur(int id) throws SQLException;

    /**
     * Supprime un programmeur de la base de données en fonction de son identifiant.
     *
     * @param id l'identifiant du programmeur à supprimer.
     * @return true si la suppression a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de la suppression des données.
     */
    boolean supprimerProgrammeur(int id) throws SQLException;

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur l'objet programmeur à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de l'ajout des données.
     */
    boolean ajouterProgrammeur(Programmeur programmeur) throws SQLException;

    /**
     * Modifie le salaire d'un programmeur en fonction de son identifiant.
     *
     * @param id l'identifiant du programmeur dont le salaire sera modifié.
     * @param nouveauSalaire le nouveau salaire à attribuer au programmeur.
     * @return true si la modification a réussi, false sinon.
     * @throws SQLException si une erreur survient lors de la mise à jour des données.
     */
    boolean modifierSalaire(int id, double nouveauSalaire) throws SQLException;
}
