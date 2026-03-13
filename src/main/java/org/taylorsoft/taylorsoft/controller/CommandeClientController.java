package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.CommandeClientRequest;
import org.taylorsoft.taylorsoft.dtos.response.CommandeClientResponse;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;
import org.taylorsoft.taylorsoft.service.CommandeClientService;

import java.util.List;

@RestController
@RequestMapping("/api/commande-client")
@RequiredArgsConstructor
public class CommandeClientController {

    private final CommandeClientService commandeClientService;

    /**
     * Créer une nouvelle commande client
     */
    @PostMapping
    public ResponseEntity<CommandeClientResponse> create(@Valid @RequestBody CommandeClientRequest request) {
        CommandeClientResponse response = commandeClientService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les commandes
     */
    @GetMapping
    public ResponseEntity<List<CommandeClientResponse>> getAll() {
        List<CommandeClientResponse> responses = commandeClientService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une commande par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommandeClientResponse> getById(@PathVariable Long id) {
        CommandeClientResponse response = commandeClientService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer toutes les commandes d'un client
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CommandeClientResponse>> getByClientId(@PathVariable Long clientId) {
        List<CommandeClientResponse> responses = commandeClientService.getByClientId(clientId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les commandes par statut
     */
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<CommandeClientResponse>> getByStatut(@PathVariable CommandeStatus statut) {
        List<CommandeClientResponse> responses = commandeClientService.getByStatut(statut);
        return ResponseEntity.ok(responses);
    }

    /**
     * Mettre à jour le statut d'une commande
     */
    @PatchMapping("/{id}/statut")
    public ResponseEntity<CommandeClientResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam CommandeStatus statut) {
        CommandeClientResponse response = commandeClientService.updateStatut(id, statut);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une commande
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

