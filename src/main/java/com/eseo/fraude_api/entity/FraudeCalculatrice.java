package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * @brief Fraude de type calculatrice programmable.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "fraudes_calculatrice")
public class FraudeCalculatrice extends Fraude {

    private String marque;
    private String programmeStocke;

    public FraudeCalculatrice() {}

    public FraudeCalculatrice(LocalDate dateReleve, String description,
                              String contenu, String marque,
                              String programmeStocke) {
        super(dateReleve, description, contenu);
        this.marque = marque;
        this.programmeStocke = programmeStocke;
    }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getProgrammeStocke() { return programmeStocke; }
    public void setProgrammeStocke(String p) { this.programmeStocke = p; }
}