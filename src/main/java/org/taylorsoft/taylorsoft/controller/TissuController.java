package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.TissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuResponse;
import org.taylorsoft.taylorsoft.service.TissuService;

import java.util.List;

@RestController
@RequestMapping("/api/tissu")
@RequiredArgsConstructor
public class TissuController {

    private final TissuService tissuService;

    /**
     * Créer un nouveau tissu
     */
    @PostMapping
    public ResponseEntity<TissuResponse> create(@Valid @RequestBody TissuRequest request) {
        TissuResponse response = tissuService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les tissus
     */
    @GetMapping
    public ResponseEntity<List<TissuResponse>> getAll() {
        List<TissuResponse> responses = tissuService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer un tissu par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TissuResponse> getById(@PathVariable Long id) {
        TissuResponse response = tissuService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un tissu
     */
    @PutMapping("/{id}")
    public ResponseEntity<TissuResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TissuRequest request) {
        TissuResponse response = tissuService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un tissu
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tissuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

