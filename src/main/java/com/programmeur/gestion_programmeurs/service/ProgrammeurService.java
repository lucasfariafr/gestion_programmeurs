package com.programmeur.gestion_programmeurs.service;

import com.programmeur.gestion_programmeurs.model.Programmeur;
import com.programmeur.gestion_programmeurs.repository.ProgrammeurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammeurService {

    @Autowired
    private ProgrammeurRepository programmeurRepository;

    public List<Programmeur> recuperer() {
        return programmeurRepository.findAll();
    }

    @Transactional
    public void supprimer(Long id) {
        programmeurRepository.deleteById(id);
    }

    @Transactional
    public void modifier(Long id, Programmeur programmeur) {
        Programmeur programmeurExist = programmeurRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new RuntimeException("Programmeur non trouvé"));
        programmeurExist.setNom(programmeur.getNom());
        programmeurExist.setPrenom(programmeur.getPrenom());
        programmeurExist.setAnNaissance(programmeur.getAnNaissance());
        programmeurExist.setSalaire(programmeur.getSalaire());
        programmeurExist.setPrime(programmeur.getPrime());
        programmeurExist.setPseudo(programmeur.getPseudo());
        programmeurRepository.save(programmeurExist);
    }
}
