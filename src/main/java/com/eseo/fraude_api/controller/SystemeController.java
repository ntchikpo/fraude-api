package com.eseo.fraude_api.controller;

import com.eseo.fraude_api.entity.*;
import com.eseo.fraude_api.service.SystemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @brief Contrôleur REST central du système de gestion des fraudes.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class SystemeController {

    @Autowired
    private SystemeService systemeService;


    // ETUDIANTS

    /** GET /api/etudiants */
    @GetMapping("/etudiants")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        return ResponseEntity.ok(systemeService.getAllEtudiants());
    }

    /** GET /api/etudiants/{id} */
    @GetMapping("/etudiants/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(systemeService.getEtudiantById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** GET /api/etudiants/search?nom=... */
    @GetMapping("/etudiants/search")
    public ResponseEntity<List<Etudiant>> searchEtudiant(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom,
            @RequestParam(required = false) String numero) {
        if (nom != null)
            return ResponseEntity.ok(systemeService.rechercherParNom(nom));
        if (prenom != null)
            return ResponseEntity.ok(systemeService.rechercherParPrenom(prenom));
        if (numero != null) {
            try {
                return ResponseEntity.ok(
                        List.of(systemeService.rechercherParNumero(numero)));
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(systemeService.getAllEtudiants());
    }

    /** POST /api/etudiants */
    @PostMapping("/etudiants")
    public ResponseEntity<Etudiant> ajouterEtudiant(
            @RequestBody Etudiant etudiant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(systemeService.ajouterEtudiant(etudiant));
    }

    /** PUT /api/etudiants/{id} */
    @PutMapping("/etudiants/{id}")
    public ResponseEntity<Etudiant> modifierEtudiant(
            @PathVariable Long id, @RequestBody Etudiant etudiant) {
        try {
            return ResponseEntity.ok(
                    systemeService.modifierEtudiant(id, etudiant));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE /api/etudiants/{id} */
    @DeleteMapping("/etudiants/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable Long id) {
        try {
            systemeService.supprimerEtudiant(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // EPREUVES


    /** GET /api/epreuves */
    @GetMapping("/epreuves")
    public ResponseEntity<List<Epreuve>> getAllEpreuves() {
        return ResponseEntity.ok(systemeService.getAllEpreuves());
    }

    /** GET /api/epreuves/{id} */
    @GetMapping("/epreuves/{id}")
    public ResponseEntity<Epreuve> getEpreuveById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(systemeService.getEpreuveById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /api/epreuves */
    @PostMapping("/epreuves")
    public ResponseEntity<Epreuve> ajouterEpreuve(
            @RequestBody Epreuve epreuve) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(systemeService.ajouterEpreuve(epreuve));
    }

    /** DELETE /api/epreuves/{id} */
    @DeleteMapping("/epreuves/{id}")
    public ResponseEntity<Void> supprimerEpreuve(@PathVariable Long id) {
        try {
            systemeService.supprimerEpreuve(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // FORMULAIRES


    /** GET /api/formulaires */
    @GetMapping("/formulaires")
    public ResponseEntity<List<Formulaire>> getAllFormulaires() {
        return ResponseEntity.ok(systemeService.getAllFormulaires());
    }

    /** GET /api/formulaires/{id} */
    @GetMapping("/formulaires/{id}")
    public ResponseEntity<Formulaire> getFormulaireById(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(systemeService.getFormulaireById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /api/formulaires?epreuveId=1 */
    @PostMapping("/formulaires")
    public ResponseEntity<Formulaire> creerFormulaire(
            @RequestParam Long epreuveId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(systemeService.creerFormulaire(epreuveId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /** POST /api/formulaires/{id}/etudiants/{etudiantId} */
    @PostMapping("/formulaires/{id}/etudiants/{etudiantId}")
    public ResponseEntity<Formulaire> ajouterEtudiantFormulaire(
            @PathVariable Long id, @PathVariable Long etudiantId) {
        try {
            return ResponseEntity.ok(
                    systemeService.ajouterEtudiant(id, etudiantId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE /api/formulaires/{id} */
    @DeleteMapping("/formulaires/{id}")
    public ResponseEntity<Void> supprimerFormulaire(@PathVariable Long id) {
        try {
            systemeService.supprimerFormulaire(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // STATISTIQUES


    /** GET /api/statistiques */
    @GetMapping("/statistiques")
    public ResponseEntity<Map<String, Object>> getStatistiques() {
        return ResponseEntity.ok(systemeService.getStatistiques());
    }
    // FRAUDES

    /** GET /api/fraudes */
    @GetMapping("/fraudes")
    public ResponseEntity<List<Fraude>> getAllFraudes() {
        return ResponseEntity.ok(systemeService.getAllFraudes());
    }

    /** POST /api/formulaires/{id}/fraudes/iag */
    @PostMapping("/formulaires/{id}/fraudes/iag")
    public ResponseEntity<Formulaire> ajouterFraudeIAG(
            @PathVariable Long id,
            @RequestBody FraudeIAG fraude) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(systemeService.ajouterFraude(id, fraude));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /api/formulaires/{id}/fraudes/iag-connectee */
    @PostMapping("/formulaires/{id}/fraudes/iag-connectee")
    public ResponseEntity<Formulaire> ajouterFraudeIAGConnectee(
            @PathVariable Long id,
            @RequestBody FraudeIAGConnectee fraude) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(systemeService.ajouterFraude(id, fraude));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /api/formulaires/{id}/fraudes/papier */
    @PostMapping("/formulaires/{id}/fraudes/papier")
    public ResponseEntity<Formulaire> ajouterFraudePapier(
            @PathVariable Long id,
            @RequestBody FraudePapier fraude) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(systemeService.ajouterFraude(id, fraude));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /api/formulaires/{id}/fraudes/calculatrice */
    @PostMapping("/formulaires/{id}/fraudes/calculatrice")
    public ResponseEntity<Formulaire> ajouterFraudeCalculatrice(
            @PathVariable Long id,
            @RequestBody FraudeCalculatrice fraude) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(systemeService.ajouterFraude(id, fraude));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE /api/fraudes/{id} */
    @DeleteMapping("/fraudes/{id}")
    public ResponseEntity<Void> supprimerFraude(@PathVariable Long id) {
        try {
            systemeService.supprimerFraude(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}