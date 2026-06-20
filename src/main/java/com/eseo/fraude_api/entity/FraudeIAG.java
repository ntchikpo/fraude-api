package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * @brief Fraude de type utilisation d'IA générative.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "fraudes_iag")
public class FraudeIAG extends Fraude {

    private String nomService;

    public FraudeIAG() {}

    public FraudeIAG(LocalDate dateReleve, String description,
                     String contenu, String nomService) {
        super(dateReleve, description, contenu);
        this.nomService = nomService;
    }

    public String getNomService() { return nomService; }
    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
}