package org.example;

import java.sql.SQLException;
import java.util.List;

public interface ActionsBDD {
    List<Programmeur> afficherProgrammeurs() throws SQLException;

    boolean supprimerProgrammeur(int id) throws SQLException;
}
