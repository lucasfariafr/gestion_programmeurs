package com.programmeur.webapp.controller;

import com.programmeur.webapp.model.Programmeur;
import com.programmeur.webapp.service.ProgrammeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur pour la gestion des programmeurs. Cette classe gère les requêtes HTTP liées
 * aux opérations de consultation, modification, ajout et suppression des programmeurs.
 */
@Controller
public class ProgrammeurController {

    @Autowired
    private ProgrammeurService programmeurService;

    /**
     * Redirige la requête initiale vers la page de répertoire des programmeurs.
     *
     * @return La redirection vers la page /repertoire.
     */
    @GetMapping("/")
    public String onStart() {
        return "redirect:/repertoire";
    }

    /**
     * Affiche la liste de tous les programmeurs.
     * Cette méthode ajoute la liste des programmeurs à un modèle et charge la vue.
     *
     * @param model Le modèle pour passer les données à la vue.
     * @return La vue "layout", avec le contenu de la page "repertoire.html".
     */
    @GetMapping("/repertoire")
    public String afficherProgrammeurs(Model model) {
        model.addAttribute("programmeurs", programmeurService.recuperer());
        model.addAttribute("pageContent", "repertoire.html");
        return "layout";
    }

    /**
     * Supprime un programmeur basé sur son identifiant.
     *
     * @param id L'identifiant du programmeur à supprimer.
     * @return Une réponse HTTP avec un message de succès.
     */
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> supprimerProgrammeur(@PathVariable Long id) {
        programmeurService.supprimer(id);
        return ResponseEntity.ok("Suppression réussie.");
    }

    /**
     * Modifie un programmeur existant en fonction de son identifiant.
     *
     * @param id          L'identifiant du programmeur à modifier.
     * @param programmeur L'objet Programmeur contenant les nouvelles informations.
     * @return Une réponse HTTP avec un message de succès.
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<String> modifierProgrammeur(@PathVariable Long id, @RequestBody Programmeur programmeur) {
        programmeurService.modifier(id, programmeur);
        return ResponseEntity.ok("Programmeur mis à jour avec succès.");
    }

    /**
     * Récupère un programmeur par son identifiant et le retourne dans le corps de la réponse.
     *
     * @param id L'identifiant du programmeur à récupérer.
     * @return Le programmeur trouvé.
     */
    @GetMapping("/programmeur/{id}")
    @ResponseBody
    public Programmeur recupererProgrammeur(@PathVariable Long id) {
        return programmeurService.recupererParId(id);
    }

    /**
     * Affiche le formulaire d'inscription pour ajouter un nouveau programmeur.
     *
     * @param model Le modèle pour passer l'objet Programmeur à la vue.
     * @return La vue "layout", avec le contenu de la page "inscription.html".
     */
    @GetMapping("/inscription")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("programmeur", new Programmeur());
        model.addAttribute("pageContent", "inscription.html");
        return "layout";
    }

    /**
     * Inscrit un nouveau programmeur avec les informations fournies par l'utilisateur.
     * Cette méthode vérifie que tous les champs requis sont remplis et ajoute le programmeur à la base de données.
     *
     * @param nom         Le nom du programmeur.
     * @param prenom      Le prénom du programmeur.
     * @param anNaissance L'année de naissance du programmeur.
     * @param salaire     Le salaire du programmeur.
     * @param prime       La prime du programmeur.
     * @param pseudo      Le pseudo du programmeur.
     * @param model       Le modèle pour passer des messages d'erreur ou rediriger.
     * @return La vue "inscription" si des champs sont manquants, ou redirige vers le répertoire des programmeurs.
     */
    @PostMapping("/inscrire")
    public String inscrireProgrammeur(@RequestParam String nom,
                                      @RequestParam String prenom,
                                      @RequestParam int anNaissance,
                                      @RequestParam double salaire,
                                      @RequestParam double prime,
                                      @RequestParam String pseudo,
                                      Model model) {

        if (nom.isEmpty() || prenom.isEmpty() || pseudo.isEmpty()) {
            model.addAttribute("erreur", "Tous les champs doivent être remplis.");
            return "inscription";
        }

        // Création d'un objet Programmeur avec les données fournies
        Programmeur programmeur = new Programmeur();
        programmeur.setNom(nom);
        programmeur.setPrenom(prenom);
        programmeur.setAnNaissance(anNaissance);
        programmeur.setSalaire(salaire);
        programmeur.setPrime(prime);
        programmeur.setPseudo(pseudo);

        // Ajout du programmeur à la base de données
        programmeurService.ajouterProgrammeur(programmeur);
        return "redirect:/repertoire";
    }

    /**
     * Affiche les statistiques des programmeurs.
     *
     * @param model Le modèle pour passer les données à la vue.
     * @return La vue "layout", avec le contenu de la page "statistique.html".
     */
    @GetMapping("/statistique")
    public String afficherKPI(Model model) {
        model.addAttribute("pageContent", "statistique.html");
        return "layout";
    }

    /**
     * Récupère les données statistiques des programmeurs pour affichage sous forme de JSON.
     *
     * @return La liste des programmeurs sous forme de JSON.
     */
    @GetMapping("/data")
    @ResponseBody
    public List<Programmeur> recupererKPI() {
        return programmeurService.recuperer();
    }
}
