package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.TypeTissu;

import java.util.Optional;

@Repository
public interface TypeTissuRepository extends JpaRepository<TypeTissu, Long> {
    Optional<TypeTissu> findByNom(String nom);
    boolean existsByNom(String nom);
}

