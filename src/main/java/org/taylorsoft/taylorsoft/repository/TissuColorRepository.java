package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.TissuColor;

import java.util.List;

@Repository
public interface TissuColorRepository extends JpaRepository<TissuColor, Long> {
    List<TissuColor> findByTissuId(Long tissuId);
    List<TissuColor> findByCouleurId(Long couleurId);
    List<TissuColor> findByFournisseurId(Long fournisseurId);
    List<TissuColor> findByActiveTrue();
}


