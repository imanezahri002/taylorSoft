package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderRequest;
import org.taylorsoft.taylorsoft.dtos.response.CoutourierOrderResponse;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;
import org.taylorsoft.taylorsoft.service.CoutourierOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/coutourier-orders")
@RequiredArgsConstructor
public class CoutourierOrderController {

    private final CoutourierOrderService coutourierOrderService;

    /**
     * Créer une nouvelle commande couturier
     */
    @PostMapping
    public ResponseEntity<CoutourierOrderResponse> create(@Valid @RequestBody CoutourierOrderRequest request) {
        CoutourierOrderResponse response = coutourierOrderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les commandes
     */
    @GetMapping
    public ResponseEntity<List<CoutourierOrderResponse>> getAll() {
        List<CoutourierOrderResponse> responses = coutourierOrderService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une commande par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoutourierOrderResponse> getById(@PathVariable Long id) {
        CoutourierOrderResponse response = coutourierOrderService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour une commande
     */
    @PutMapping("/{id}")
    public ResponseEntity<CoutourierOrderResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CoutourierOrderRequest request) {
        CoutourierOrderResponse response = coutourierOrderService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une commande
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coutourierOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les commandes d'un couturier
     */
    @GetMapping("/coutourier/{couturierId}")
    public ResponseEntity<List<CoutourierOrderResponse>> getByCouturierId(@PathVariable Long couturierId) {
        List<CoutourierOrderResponse> responses = coutourierOrderService.getByCouturierId(couturierId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les commandes d'un fournisseur
     */
    @GetMapping("/fournisseur/{fournisseurId}")
    public ResponseEntity<List<CoutourierOrderResponse>> getByFournisseurId(@PathVariable Long fournisseurId) {
        List<CoutourierOrderResponse> responses = coutourierOrderService.getByFournisseurId(fournisseurId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les commandes par statut
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<CoutourierOrderResponse>> getByStatus(@PathVariable CoutourierOrderStatus status) {
        List<CoutourierOrderResponse> responses = coutourierOrderService.getByStatus(status);
        return ResponseEntity.ok(responses);
    }

    /**
     * Mettre à jour le statut d'une commande
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<CoutourierOrderResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam CoutourierOrderStatus status) {
        CoutourierOrderResponse response = coutourierOrderService.updateStatus(id, status);
        return ResponseEntity.ok(response);
    }
}

