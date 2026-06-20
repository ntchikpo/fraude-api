package com.eseo.fraude_api.repository;

import com.eseo.fraude_api.entity.Formulaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @brief Repository JPA pour les formulaires.
 * @author Noé Thierry Tchikpo
 */
@Repository
public interface FormulaireRepository extends JpaRepository<Formulaire, Long> {
    List<Formulaire> findByEpreuveId(Long epreuveId);
    List<Formulaire> findByEtudiantsId(Long etudiantId);
}