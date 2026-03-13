package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.CommandeClientItem;

import java.util.List;

@Repository
public interface CommandeClientItemRepository extends JpaRepository<CommandeClientItem, Long> {

    /**
     * Trouver tous les items d'une commande
     */
    List<CommandeClientItem> findByCommandeClientId(Long commandeClientId);
}

