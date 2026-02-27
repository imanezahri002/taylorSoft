package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.TissuColorRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuColorResponse;
import org.taylorsoft.taylorsoft.service.TissuColorService;

import java.util.List;

@RestController
@RequestMapping("/api/tissu-color")
@RequiredArgsConstructor
public class TissuColorController {

    private final TissuColorService tissuColorService;

    /**
     * Créer un nouveau TissuColor
     */
    @PostMapping
    public ResponseEntity<TissuColorResponse> create(@Valid @RequestBody TissuColorRequest request) {
        TissuColorResponse response = tissuColorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les TissuColor
     */
    @GetMapping
    public ResponseEntity<List<TissuColorResponse>> getAll() {
        List<TissuColorResponse> responses = tissuColorService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer un TissuColor par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TissuColorResponse> getById(@PathVariable Long id) {
        TissuColorResponse response = tissuColorService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un TissuColor
     */
    @PutMapping("/{id}")
    public ResponseEntity<TissuColorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TissuColorRequest request) {
        TissuColorResponse response = tissuColorService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un TissuColor
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tissuColorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupérer les TissuColor par Tissu ID
     */
    @GetMapping("/tissu/{tissuId}")
    public ResponseEntity<List<TissuColorResponse>> getByTissuId(@PathVariable Long tissuId) {
        List<TissuColorResponse> responses = tissuColorService.getByTissuId(tissuId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les TissuColor par Couleur ID
     */
    @GetMapping("/couleur/{couleurId}")
    public ResponseEntity<List<TissuColorResponse>> getByCouleurId(@PathVariable Long couleurId) {
        List<TissuColorResponse> responses = tissuColorService.getByCouleurId(couleurId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer les TissuColor par Fournisseur ID
     */
    @GetMapping("/fournisseur/{fournisseurId}")
    public ResponseEntity<List<TissuColorResponse>> getByFournisseurId(@PathVariable Long fournisseurId) {
        List<TissuColorResponse> responses = tissuColorService.getByFournisseurId(fournisseurId);
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer uniquement les TissuColor actifs
     */
    @GetMapping("/active")
    public ResponseEntity<List<TissuColorResponse>> getActiveOnly() {
        List<TissuColorResponse> responses = tissuColorService.getActiveOnly();
        return ResponseEntity.ok(responses);
    }
}

