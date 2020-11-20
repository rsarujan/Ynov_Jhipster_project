package com.pro.ynov.com.repository;

import com.pro.ynov.com.domain.Exemplaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Exemplaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
}
