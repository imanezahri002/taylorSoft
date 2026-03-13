 package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.CommandeClientItemRequest;
import org.taylorsoft.taylorsoft.dtos.request.CommandeClientRequest;
import org.taylorsoft.taylorsoft.dtos.response.CommandeClientResponse;
import org.taylorsoft.taylorsoft.entity.*;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.CommandeClientMapper;
import org.taylorsoft.taylorsoft.repository.ClientRepository;
import org.taylorsoft.taylorsoft.repository.CommandeClientRepository;
import org.taylorsoft.taylorsoft.repository.ModelColorRepository;
import org.taylorsoft.taylorsoft.service.CommandeClientService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final ClientRepository clientRepository;
    private final ModelColorRepository modelColorRepository;
    private final CommandeClientMapper commandeClientMapper;

    @Override
    @Transactional
    public CommandeClientResponse create(CommandeClientRequest request) {
        // Vérifier que le client existe
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID: " + request.getClientId()));

        // Créer la commande
        CommandeClient commande = CommandeClient.builder()
            .client(client)
            .dateCommande(LocalDateTime.now())
            .statut(request.getStatut() != null ? request.getStatut() : CommandeStatus.EN_ATTENTE)
            .items(new ArrayList<>())
            .build();

        // Créer les items et calculer le total
        double total = 0.0;
        for (CommandeClientItemRequest itemRequest : request.getItems()) {
            // Vérifier que le ModelColor existe
            ModelColor modelColor = modelColorRepository.findById(itemRequest.getModelColorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "ModelColor non trouvé avec l'ID: " + itemRequest.getModelColorId()));

            // Créer l'item
            CommandeClientItem item = CommandeClientItem.builder()
                .modelColor(modelColor)
                .quantite(itemRequest.getQuantite())
                .prixUnitaire(itemRequest.getPrixUnitaire())
                .commandeClient(commande)
                .build();

            commande.getItems().add(item);
            total += itemRequest.getQuantite() * itemRequest.getPrixUnitaire();
        }

        commande.setTotal(total);

        // Sauvegarder
        CommandeClient savedCommande = commandeClientRepository.save(commande);

        return commandeClientMapper.toResponse(savedCommande);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandeClientResponse> getAll() {
        List<CommandeClient> commandes = commandeClientRepository.findAll();
        return commandeClientMapper.toResponseList(commandes);
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeClientResponse getById(Long id) {
        CommandeClient commande = commandeClientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID: " + id));

        return commandeClientMapper.toResponse(commande);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandeClientResponse> getByClientId(Long clientId) {
        // Vérifier que le client existe
        if (!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException("Client non trouvé avec l'ID: " + clientId);
        }

        List<CommandeClient> commandes = commandeClientRepository.findByClientId(clientId);
        return commandeClientMapper.toResponseList(commandes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandeClientResponse> getByStatut(CommandeStatus statut) {
        List<CommandeClient> commandes = commandeClientRepository.findByStatut(statut);
        return commandeClientMapper.toResponseList(commandes);
    }

    @Override
    @Transactional
    public CommandeClientResponse updateStatut(Long id, CommandeStatus statut) {
        CommandeClient commande = commandeClientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID: " + id));

        commande.setStatut(statut);
        CommandeClient updatedCommande = commandeClientRepository.save(commande);

        return commandeClientMapper.toResponse(updatedCommande);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!commandeClientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Commande non trouvée avec l'ID: " + id);
        }

        commandeClientRepository.deleteById(id);
    }
}

