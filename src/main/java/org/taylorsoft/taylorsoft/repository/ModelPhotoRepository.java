package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.ModelColor;
import org.taylorsoft.taylorsoft.entity.ModelPhoto;
import org.taylorsoft.taylorsoft.entity.enums.Ordre;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelPhotoRepository extends JpaRepository<ModelPhoto, Long> {

    List<ModelPhoto> findByModelColor(ModelColor modelColor);

    List<ModelPhoto> findByModelColorId(Long modelColorId);

    List<ModelPhoto> findByPrincipalTrue();

    List<ModelPhoto> findByModelColorAndPrincipalTrue(ModelColor modelColor);

    Optional<ModelPhoto> findByModelColorIdAndPrincipalTrue(Long modelColorId);

    List<ModelPhoto> findByModelColorIdOrderByOrderAsc(Long modelColorId);

    List<ModelPhoto> findByModelColorOrderByOrderAsc(ModelColor modelColor);

    Optional<ModelPhoto> findByModelColorAndOrder(ModelColor modelColor, Ordre order);

    void deleteByModelColorId(Long modelColorId);

    long countByModelColorId(Long modelColorId);

    long countByModelColorIdAndPrincipalTrue(Long modelColorId);
}

