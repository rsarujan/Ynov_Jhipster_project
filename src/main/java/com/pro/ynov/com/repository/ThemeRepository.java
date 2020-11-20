package com.pro.ynov.com.repository;

import com.pro.ynov.com.domain.Theme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Theme entity.
 */
@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query(value = "select distinct theme from Theme theme left join fetch theme.livres",
        countQuery = "select count(distinct theme) from Theme theme")
    Page<Theme> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct theme from Theme theme left join fetch theme.livres")
    List<Theme> findAllWithEagerRelationships();

    @Query("select theme from Theme theme left join fetch theme.livres where theme.id =:id")
    Optional<Theme> findOneWithEagerRelationships(@Param("id") Long id);
}
