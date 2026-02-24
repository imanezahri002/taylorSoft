package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.TypeEchantillon;

import java.util.Optional;

@Repository
public interface TypeEchantillonRepository extends JpaRepository<TypeEchantillon, Long> {
    Optional<TypeEchantillon> findByNom(String nom);
    boolean existsByNom(String nom);
}

