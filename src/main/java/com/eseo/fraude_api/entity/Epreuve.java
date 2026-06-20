package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @brief Entité représentant une épreuve.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "epreuves")
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le code ECUE est obligatoire")
    private String codeECUE;

    @NotNull(message = "La date de passage est obligatoire")
    private LocalDate datePassage;

    private LocalTime heure;
    private float duree;

    @Enumerated(EnumType.STRING)
    private Modalite modalite;

    public Epreuve() {}

    public Epreuve(String codeECUE, LocalDate datePassage,
                   LocalTime heure, float duree, Modalite modalite) {
        this.codeECUE = codeECUE;
        this.datePassage = datePassage;
        this.heure = heure;
        this.duree = duree;
        this.modalite = modalite;
    }

    public Long getId() { return id; }
    public String getCodeECUE() { return codeECUE; }
    public void setCodeECUE(String codeECUE) { this.codeECUE = codeECUE; }
    public LocalDate getDatePassage() { return datePassage; }
    public void setDatePassage(LocalDate datePassage) {
        this.datePassage = datePassage;
    }
    public LocalTime getHeure() { return heure; }
    public void setHeure(LocalTime heure) { this.heure = heure; }
    public float getDuree() { return duree; }
    public void setDuree(float duree) { this.duree = duree; }
    public Modalite getModalite() { return modalite; }
    public void setModalite(Modalite modalite) { this.modalite = modalite; }
}