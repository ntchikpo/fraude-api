package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Entité représentant un formulaire de fraude.
 * Relie une épreuve, des étudiants et des fraudes.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "formulaires")
public class Formulaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreation;
    private LocalDate dateModification;

    /**
     * @brief Relation ManyToOne avec Epreuve.
     * Plusieurs formulaires peuvent concerner la même épreuve.
     */
    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    private Epreuve epreuve;

    /**
     * @brief Relation ManyToMany avec Etudiant.
     * Un formulaire peut concerner plusieurs étudiants
     * et un étudiant peut apparaître dans plusieurs formulaires.
     */
    @ManyToMany
    @JoinTable(
            name = "formulaire_etudiants",
            joinColumns = @JoinColumn(name = "formulaire_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    private List<Etudiant> etudiants = new ArrayList<>();

    /**
     * @brief Relation ManyToMany avec Fraude.
     */
    @ManyToMany
    @JoinTable(
            name = "formulaire_fraudes",
            joinColumns = @JoinColumn(name = "formulaire_id"),
            inverseJoinColumns = @JoinColumn(name = "fraude_id")
    )
    private List<Fraude> fraudes = new ArrayList<>();

    public Formulaire() {
        this.dateCreation = LocalDate.now();
        this.dateModification = LocalDate.now();
    }

    public Formulaire(Epreuve epreuve) {
        this.epreuve = epreuve;
        this.dateCreation = LocalDate.now();
        this.dateModification = LocalDate.now();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
        this.dateModification = LocalDate.now();
    }

    public void ajouterFraude(Fraude fraude) {
        fraudes.add(fraude);
        this.dateModification = LocalDate.now();
    }

    public Long getId() { return id; }
    public LocalDate getDateCreation() { return dateCreation; }
    public LocalDate getDateModification() { return dateModification; }
    public Epreuve getEpreuve() { return epreuve; }
    public void setEpreuve(Epreuve epreuve) { this.epreuve = epreuve; }
    public List<Etudiant> getEtudiants() { return etudiants; }
    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
    public List<Fraude> getFraudes() { return fraudes; }
    public void setFraudes(List<Fraude> fraudes) {
        this.fraudes = fraudes;
    }
}