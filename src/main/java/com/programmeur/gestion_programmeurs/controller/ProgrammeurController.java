package com.programmeur.gestion_programmeurs.controller;

import com.programmeur.gestion_programmeurs.model.Programmeur;
import com.programmeur.gestion_programmeurs.service.ProgrammeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProgrammeurController {

    @Autowired
    private ProgrammeurService programmeurService;

    @GetMapping("/")
    public String afficherProgrammeurs(Model model) {
        model.addAttribute("programmeurs", programmeurService.recuperer());
        return "programmeurs";
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerProgrammeur(@PathVariable Long id) {
        programmeurService.supprimer(id);
        return ResponseEntity.ok("Suppression réussie.");
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierProgrammeur(@PathVariable Long id, @RequestBody Programmeur programmeur) {
        programmeurService.modifier(id, programmeur);
        return ResponseEntity.ok("Programmeur mis à jour avec succès.");
    }
}
