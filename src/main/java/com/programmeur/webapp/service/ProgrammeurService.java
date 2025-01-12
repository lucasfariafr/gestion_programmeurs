package com.programmeur.webapp.service;

import com.programmeur.webapp.model.Programmeur;
import com.programmeur.webapp.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des programmeurs. Cette classe contient des méthodes
 * pour récupérer, ajouter, modifier et supprimer des programmeurs via le repository.
 */
@Service
public class ProgrammeurService {

    @Autowired
    private ProgrammeurRepository programmeurRepository;

    /**
     * Récupère tous les programmeurs de la base de données.
     *
     * @return Une liste de programmeurs.
     */
    public List<Programmeur> recuperer() {
        return programmeurRepository.findAll();
    }

    /**
     * Supprime un programmeur en fonction de son identifiant.
     * Cette méthode est annotée avec @Transactional pour garantir que l'opération est atomique.
     *
     * @param id L'identifiant du programmeur à supprimer.
     */
    @Transactional
    public void supprimer(Long id) {
        programmeurRepository.deleteById(id);
    }

    /**
     * Modifie un programmeur existant.
     * Si le programmeur n'est pas trouvé, une exception est levée.
     * Cette méthode est annotée avec @Transactional pour garantir que les modifications
     * se déroulent dans une transaction.
     *
     * @param id          L'identifiant du programmeur à modifier.
     * @param programmeur L'objet programmeur contenant les nouvelles informations.
     */
    @Transactional
    public void modifier(Long id, Programmeur programmeur) {
        // Recherche le programmeur existant
        Programmeur programmeurExist = programmeurRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Programmeur non trouvé"));

        // Mise à jour des informations du programmeur
        programmeurExist.setNom(programmeur.getNom());
        programmeurExist.setPrenom(programmeur.getPrenom());
        programmeurExist.setAnNaissance(programmeur.getAnNaissance());
        programmeurExist.setSalaire(programmeur.getSalaire());
        programmeurExist.setPrime(programmeur.getPrime());
        programmeurExist.setPseudo(programmeur.getPseudo());

        // Sauvegarde les changements
        programmeurRepository.save(programmeurExist);
    }

    /**
     * Récupère un programmeur par son identifiant.
     * Si le programmeur n'est pas trouvé, une exception est levée.
     *
     * @param id L'identifiant du programmeur.
     * @return Le programmeur trouvé.
     */
    public Programmeur recupererParId(Long id) {
        return programmeurRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Programmeur non trouvé"));
    }

    /**
     * Ajoute un nouveau programmeur à la base de données.
     *
     * @param programmeur L'objet programmeur à ajouter.
     */
    public void ajouterProgrammeur(Programmeur programmeur) {
        programmeurRepository.save(programmeur);
    }
}
