package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Coutourier;

import java.util.Optional;

@Repository
public interface CoutourierRepository extends JpaRepository<Coutourier, Long> {
    Optional<Coutourier> findByEmail(String email);
}

