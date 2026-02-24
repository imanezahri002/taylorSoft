package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Couleur;

import java.util.Optional;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Long> {
    Optional<Couleur> findByNom(String nom);
    boolean existsByNom(String nom);
    Optional<Couleur> findByCodeHex(String codeHex);
    boolean existsByCodeHex(String codeHex);
}

