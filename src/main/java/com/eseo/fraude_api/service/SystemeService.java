package com.eseo.fraude_api.service;

import com.eseo.fraude_api.entity.*;
import com.eseo.fraude_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @brief Service central gérant formulaires, épreuves,
 * étudiants et statistiques.
 * Inspiré de la classe Systeme.java originale.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Service
public class SystemeService {

    @Autowired
    private FormulaireRepository formulaireRepository;

    @Autowired
    private EpreuveRepository epreuveRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    // FORMULAIRES

    /** @brief Récupère tous les formulaires. */
    public List<Formulaire> getAllFormulaires() {
        return formulaireRepository.findAll();
    }

    /** @brief Récupère un formulaire par id. */
    public Formulaire getFormulaireById(Long id) {
        return formulaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulaire introuvable"));
    }

    /** @brief Crée un formulaire pour une épreuve. */
    public Formulaire creerFormulaire(Long epreuveId) {
        Epreuve epreuve = epreuveRepository.findById(epreuveId)
                .orElseThrow(() -> new RuntimeException("Épreuve introuvable"));
        return formulaireRepository.save(new Formulaire(epreuve));
    }

    /** @brief Ajoute un étudiant à un formulaire. */
    public Formulaire ajouterEtudiant(Long formulaireId, Long etudiantId) {
        Formulaire f = getFormulaireById(formulaireId);
        Etudiant e = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
        f.ajouterEtudiant(e);
        return formulaireRepository.save(f);
    }

    /** @brief Supprime un formulaire. */
    public void supprimerFormulaire(Long id) {
        if (!formulaireRepository.existsById(id))
            throw new RuntimeException("Formulaire introuvable");
        formulaireRepository.deleteById(id);
    }

    /** @brief Formulaires par épreuve. */
    public List<Formulaire> formulairesParEpreuve(Long epreuveId) {
        return formulaireRepository.findByEpreuveId(epreuveId);
    }

    /** @brief Formulaires par étudiant. */
    public List<Formulaire> formulairesParEtudiant(Long etudiantId) {
        return formulaireRepository.findByEtudiantsId(etudiantId);
    }


    // EPREUVES

    /** @brief Récupère toutes les épreuves. */
    public List<Epreuve> getAllEpreuves() {
        return epreuveRepository.findAll();
    }

    /** @brief Récupère une épreuve par id. */
    public Epreuve getEpreuveById(Long id) {
        return epreuveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Épreuve introuvable"));
    }

    /** @brief Ajoute une épreuve. */
    public Epreuve ajouterEpreuve(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    /** @brief Supprime une épreuve. */
    public void supprimerEpreuve(Long id) {
        if (!epreuveRepository.existsById(id))
            throw new RuntimeException("Épreuve introuvable");
        epreuveRepository.deleteById(id);
    }

    //
    // ETUDIANTS
    //

    /** @brief Récupère tous les étudiants. */
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    /** @brief Récupère un étudiant par id. */
    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
    }

    /** @brief Recherche par numéro apprenant. */
    public Etudiant rechercherParNumero(String numero) {
        return etudiantRepository.findByNumeroApprenant(numero)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
    }

    /** @brief Recherche par nom. */
    public List<Etudiant> rechercherParNom(String nom) {
        return etudiantRepository.findByNomContainingIgnoreCase(nom);
    }

    /** @brief Recherche par prénom. */
    public List<Etudiant> rechercherParPrenom(String prenom) {
        return etudiantRepository.findByPrenomContainingIgnoreCase(prenom);
    }

    /** @brief Ajoute un étudiant. */
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    /** @brief Modifie un étudiant. */
    public Etudiant modifierEtudiant(Long id, Etudiant newEtudiant) {
        Etudiant e = getEtudiantById(id);
        e.setNom(newEtudiant.getNom());
        e.setPrenom(newEtudiant.getPrenom());
        e.setNumeroApprenant(newEtudiant.getNumeroApprenant());
        e.setCursus(newEtudiant.getCursus());
        return etudiantRepository.save(e);
    }

    /** @brief Supprime un étudiant. */
    public void supprimerEtudiant(Long id) {
        if (!etudiantRepository.existsById(id))
            throw new RuntimeException("Étudiant introuvable");
        etudiantRepository.deleteById(id);
    }


    // STATISTIQUES


    /**
     * @brief Retourne les statistiques globales du système.
     * @return Map contenant les statistiques
     */
    public Map<String, Object> getStatistiques() {
        List<Formulaire> formulaires = getAllFormulaires();
        int totalFormulaires = formulaires.size();
        int totalFraudes = formulaires.stream()
                .mapToInt(f -> f.getFraudes().size()).sum();
        double moyenne = totalFormulaires == 0 ? 0 :
                (double) totalFraudes / totalFormulaires;
        double ecartType = 0;
        if (totalFormulaires > 0) {
            double somme = formulaires.stream()
                    .mapToDouble(f -> Math.pow(
                            f.getFraudes().size() - moyenne, 2))
                    .sum();
            ecartType = Math.sqrt(somme / totalFormulaires);
        }

        return Map.of(
                "totalFormulaires", totalFormulaires,
                "totalEtudiants", etudiantRepository.count(),
                "totalFraudes", totalFraudes,
                "moyenneFraudesParFormulaire", moyenne,
                "ecartType", ecartType
        );
    }

    // FRAUDES


    @Autowired
    private FraudeRepository fraudeRepository;

    /**
     * @brief Récupère toutes les fraudes.
     */
    public List<Fraude> getAllFraudes() {
        return fraudeRepository.findAll();
    }

    /**
     * @brief Ajoute une fraude et la lie à un formulaire.
     * @param formulaireId id du formulaire
     * @param fraude la fraude à ajouter
     * @return le formulaire mis à jour
     */
    public Formulaire ajouterFraude(Long formulaireId, Fraude fraude) {
        Fraude savedFraude = fraudeRepository.save(fraude);
        Formulaire f = getFormulaireById(formulaireId);
        f.ajouterFraude(savedFraude);
        return formulaireRepository.save(f);
    }

    /**
     * @brief Supprime une fraude.
     */
    public void supprimerFraude(Long id) {
        if (!fraudeRepository.existsById(id))
            throw new RuntimeException("Fraude introuvable");
        fraudeRepository.deleteById(id);
    }
}