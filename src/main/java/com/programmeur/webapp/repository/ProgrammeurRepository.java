package com.programmeur.webapp.repository;

import com.programmeur.webapp.model.Programmeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository pour la gestion des programmeurs dans la base de données.
 * Cette interface étend JpaRepository pour fournir des opérations CRUD sur la table des programmeurs.
 */
@Repository
public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {

    /**
     * Supprime un programmeur basé sur son identifiant.
     * Cette méthode remplace la méthode par défaut de JpaRepository deleteById(Long id),
     * qui attend un type Long pour l'identifiant.
     *
     * @param id L'identifiant du programmeur à supprimer.
     */
    void deleteById(Long id);
}
