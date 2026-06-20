package com.eseo.fraude_api.repository;

import com.eseo.fraude_api.entity.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @brief Repository JPA pour les épreuves.
 * @author Noé Thierry Tchikpo
 */
@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
}