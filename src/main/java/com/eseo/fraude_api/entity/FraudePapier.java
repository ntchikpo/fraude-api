package com.eseo.fraude_api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * @brief Fraude de type document papier non autorisé.
 * @author Noé Thierry Tchikpo
 * @version 1.0
 */
@Entity
@Table(name = "fraudes_papier")
public class FraudePapier extends Fraude {

    private double largeur;
    private double longueur;
    private boolean estPlie;

    public FraudePapier() {}

    public FraudePapier(LocalDate dateReleve, String description,
                        String contenu, double largeur,
                        double longueur, boolean estPlie) {
        super(dateReleve, description, contenu);
        this.largeur = largeur;
        this.longueur = longueur;
        this.estPlie = estPlie;
    }

    public double getLargeur() { return largeur; }
    public void setLargeur(double largeur) { this.largeur = largeur; }
    public double getLongueur() { return longueur; }
    public void setLongueur(double longueur) { this.longueur = longueur; }
    public boolean isEstPlie() { return estPlie; }
    public void setEstPlie(boolean estPlie) { this.estPlie = estPlie; }
}