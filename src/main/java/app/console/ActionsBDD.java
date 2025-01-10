package app.console;

import com.programmeur.gestion_programmeurs.model.Programmeur;

import java.sql.SQLException;
import java.util.List;

public interface ActionsBDD {

    List<Programmeur> afficherProgrammeurs() throws SQLException;

    Programmeur afficherProgrammeur(int id) throws SQLException;

    boolean supprimerProgrammeur(int id) throws SQLException;

    boolean ajouterProgrammeur(Programmeur programmeur) throws SQLException;

    boolean modifierSalaire(int id, double nouveauSalaire) throws SQLException;
}
