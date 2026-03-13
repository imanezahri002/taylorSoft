package org.taylorsoft.taylorsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.taylorsoft.taylorsoft.entity.CommandeClient;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;

import java.util.List;

@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {

    /**
     * Trouver toutes les commandes d'un client
     */
    List<CommandeClient> findByClientId(Long clientId);

    /**
     * Trouver les commandes par statut
     */
    List<CommandeClient> findByStatut(CommandeStatus statut);

    /**
     * Trouver les commandes d'un client avec un statut spécifique
     */
    List<CommandeClient> findByClientIdAndStatut(Long clientId, CommandeStatus statut);
}

