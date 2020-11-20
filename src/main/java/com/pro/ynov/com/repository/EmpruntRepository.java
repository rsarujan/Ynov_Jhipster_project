package com.pro.ynov.com.repository;

import com.pro.ynov.com.domain.Emprunt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Emprunt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    @Query("select emprunt from Emprunt emprunt where emprunt.user.login = ?#{principal.username}")
    List<Emprunt> findByUserIsCurrentUser();
}
