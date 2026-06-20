package com.eseo.fraude_api.repository;

import com.eseo.fraude_api.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @brief Repository JPA pour les étudiants.
 * @author Noé Thierry Tchikpo
 */
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByNumeroApprenant(String numeroApprenant);
    List<Etudiant> findByNomContainingIgnoreCase(String nom);
    List<Etudiant> findByPrenomContainingIgnoreCase(String prenom);
}