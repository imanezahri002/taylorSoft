package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Couleur;
import org.taylorsoft.taylorsoft.entity.Model;
import org.taylorsoft.taylorsoft.entity.ModelColor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelColorRepository extends JpaRepository<ModelColor, Long> {

    List<ModelColor> findByModel(Model model);

    List<ModelColor> findByModelId(Long modelId);

    List<ModelColor> findByCouleur(Couleur couleur);

    List<ModelColor> findByCouleurId(Long couleurId);

    Optional<ModelColor> findByModelAndCouleur(Model model, Couleur couleur);

    void deleteByModelId(Long modelId);

    boolean existsByModelIdAndCouleurId(Long modelId, Long couleurId);
}

