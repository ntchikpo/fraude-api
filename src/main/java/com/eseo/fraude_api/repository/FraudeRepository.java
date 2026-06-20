package com.eseo.fraude_api.repository;

import com.eseo.fraude_api.entity.Fraude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @brief Repository JPA pour les fraudes.
 * @author Noé Thierry Tchikpo
 */
@Repository
public interface FraudeRepository extends JpaRepository<Fraude, Long> {
}