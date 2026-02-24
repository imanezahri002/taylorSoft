package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Mesure;

import java.util.Optional;

@Repository
public interface MesureRepository extends JpaRepository<Mesure, Long> {
    Optional<Mesure> findByClientId(Long clientId);
    boolean existsByClientId(Long clientId);
}

