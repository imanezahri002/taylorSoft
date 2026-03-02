package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.ModelRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelResponse;
import org.taylorsoft.taylorsoft.service.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    /**
     * Créer un nouveau modèle avec ses couleurs et photos
     */
    @PostMapping
    public ResponseEntity<ModelResponse> create(@Valid @RequestBody ModelRequest request) {
        ModelResponse response = modelService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les modèles
     */
    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAll() {
        List<ModelResponse> responses = modelService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer un modèle par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable Long id) {
        ModelResponse response = modelService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un modèle
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ModelRequest request) {
        ModelResponse response = modelService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un modèle
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les modèles par tissu
     */
    @GetMapping("/tissu/{tissuId}")
    public ResponseEntity<List<ModelResponse>> getByTissuId(@PathVariable Long tissuId) {
        List<ModelResponse> responses = modelService.getByTissuId(tissuId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les modèles par couturier
     */
    @GetMapping("/couturier/{couturierId}")
    public ResponseEntity<List<ModelResponse>> getByCouturierId(@PathVariable Long couturierId) {
        List<ModelResponse> responses = modelService.getByCouturierId(couturierId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer uniquement les modèles actifs
     */
    @GetMapping("/active")
    public ResponseEntity<List<ModelResponse>> getActiveOnly() {
        List<ModelResponse> responses = modelService.getActiveOnly();
        return ResponseEntity.ok(responses);
    }
}

