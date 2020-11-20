package com.pro.ynov.com.repository;

import com.pro.ynov.com.domain.Auteur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Auteur entity.
 */
@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {

    @Query(value = "select distinct auteur from Auteur auteur left join fetch auteur.livres",
        countQuery = "select count(distinct auteur) from Auteur auteur")
    Page<Auteur> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct auteur from Auteur auteur left join fetch auteur.livres")
    List<Auteur> findAllWithEagerRelationships();

    @Query("select auteur from Auteur auteur left join fetch auteur.livres where auteur.id =:id")
    Optional<Auteur> findOneWithEagerRelationships(@Param("id") Long id);
}
