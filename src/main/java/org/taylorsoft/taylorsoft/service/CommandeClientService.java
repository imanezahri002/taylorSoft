package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.dtos.request.CommandeClientRequest;
import org.taylorsoft.taylorsoft.dtos.response.CommandeClientResponse;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;

import java.util.List;

public interface CommandeClientService {

    /**
     * Créer une nouvelle commande client
     */
    CommandeClientResponse create(CommandeClientRequest request);

    /**
     * Récupérer toutes les commandes
     */
    List<CommandeClientResponse> getAll();

    /**
     * Récupérer une commande par ID
     */
    CommandeClientResponse getById(Long id);

    /**
     * Récupérer toutes les commandes d'un client
     */
    List<CommandeClientResponse> getByClientId(Long clientId);

    /**
     * Récupérer les commandes par statut
     */
    List<CommandeClientResponse> getByStatut(CommandeStatus statut);

    /**
     * Mettre à jour le statut d'une commande
     */
    CommandeClientResponse updateStatut(Long id, CommandeStatus statut);

    /**
     * Supprimer une commande
     */
    void delete(Long id);
}

