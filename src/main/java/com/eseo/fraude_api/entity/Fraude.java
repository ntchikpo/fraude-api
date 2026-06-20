package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @brief Entité abstraite représentant une fraude.
 * Utilise l'héritage JPA JOINED : chaque sous-classe
 * a sa propre table liée à cette table principale.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "fraudes")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Fraude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date de relevé est obligatoire")
    private LocalDate dateReleve;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "Le contenu est obligatoire")
    private String contenu;

    public Fraude() {}

    public Fraude(LocalDate dateReleve, String description, String contenu) {
        this.dateReleve = dateReleve;
        this.description = description;
        this.contenu = contenu;
    }

    public Long getId() { return id; }
    public LocalDate getDateReleve() { return dateReleve; }
    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
}