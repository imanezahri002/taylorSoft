package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Categorie;
import org.taylorsoft.taylorsoft.entity.Coutourier;
import org.taylorsoft.taylorsoft.entity.Model;
import org.taylorsoft.taylorsoft.entity.Tissu;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    // Recherche par nom
    Optional<Model> findByNom(String nom);
    boolean existsByNom(String nom);

    // Recherche par Tissu
    List<Model> findByTissu(Tissu tissu);
    List<Model> findByTissuId(Long tissuId);
    List<Model> findByTissuAndActiveTrue(Tissu tissu);

    // Recherche par Coutourier
    List<Model> findByCoutourier(Coutourier coutourier);
    List<Model> findByCoutourierId(Long couturierId);
    List<Model> findByCoutourierAndActiveTrue(Coutourier coutourier);

    // Recherche par Catégorie
    List<Model> findByCategorie(Categorie categorie);
    List<Model> findByCategorieId(Long categorieId);
    List<Model> findByCategorieAndActiveTrue(Categorie categorie);

    // Recherche par statut active
    List<Model> findByActiveTrue();
    List<Model> findByActiveFalse();

    // Recherche par visibilité
    List<Model> findByVisibilityTrue();
    List<Model> findByVisibilityFalse();

    // Recherche combinée
    List<Model> findByActiveTrueAndVisibilityTrue();

    // Recherche par prix
    List<Model> findByPrixLessThanEqual(Double prix);
    List<Model> findByPrixGreaterThanEqual(Double prix);
    List<Model> findByPrixBetween(Double prixMin, Double prixMax);

    // Suppression personnalisée
    void deleteByNom(String nom);
}

