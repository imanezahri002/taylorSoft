package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Tissu;

import java.util.Optional;

@Repository
public interface TissuRepository extends JpaRepository<Tissu, Long> {
    Optional<Tissu> findByReference(String reference);
    boolean existsByReference(String reference);
}

