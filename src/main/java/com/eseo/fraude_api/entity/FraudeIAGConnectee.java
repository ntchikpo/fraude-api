package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * @brief Fraude IAG avec accès réseau.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "fraudes_iag_connectee")
public class FraudeIAGConnectee extends FraudeIAG {

    private String adresseIP;

    public FraudeIAGConnectee() {}

    public FraudeIAGConnectee(LocalDate dateReleve, String description,
                              String contenu, String nomService,
                              String adresseIP) {
        super(dateReleve, description, contenu, nomService);
        this.adresseIP = adresseIP;
    }

    public String getAdresseIP() { return adresseIP; }
    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }
}