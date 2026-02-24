package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}

