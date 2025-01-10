package com.programmeur.gestion_programmeurs.repository;

import com.programmeur.gestion_programmeurs.model.Programmeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {
    void deleteById(Long id);
}
