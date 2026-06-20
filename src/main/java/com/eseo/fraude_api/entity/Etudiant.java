package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * @brief Entité représentant un étudiant.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le numéro apprenant est obligatoire")
    @Column(unique = true)
    private String numeroApprenant;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Enumerated(EnumType.STRING)
    private Cursus cursus;

    public Etudiant() {}

    public Etudiant(String numeroApprenant, String nom,
                    String prenom, Cursus cursus) {
        this.numeroApprenant = numeroApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.cursus = cursus;
    }

    public Long getId() { return id; }
    public String getNumeroApprenant() { return numeroApprenant; }
    public void setNumeroApprenant(String n) { this.numeroApprenant = n; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Cursus getCursus() { return cursus; }
    public void setCursus(Cursus cursus) { this.cursus = cursus; }
}