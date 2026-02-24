package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.TypeTissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeTissuResponse;
import org.taylorsoft.taylorsoft.service.TypeTissuService;

import java.util.List;

@RestController
@RequestMapping("/api/type-tissu")
@RequiredArgsConstructor
public class TypeTissuController {

    private final TypeTissuService typeTissuService;

    /**
     * Créer un nouveau type de tissu
     */
    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'FOURNISSEUR')")
    public ResponseEntity<TypeTissuResponse> create(@Valid @RequestBody TypeTissuRequest request) {
        TypeTissuResponse response = typeTissuService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les types de tissu
     */
    @GetMapping
    public ResponseEntity<List<TypeTissuResponse>> getAll() {
        List<TypeTissuResponse> responses = typeTissuService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer un type de tissu par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeTissuResponse> getById(@PathVariable Long id) {
        TypeTissuResponse response = typeTissuService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un type de tissu
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FOURNISSEUR')")
    public ResponseEntity<TypeTissuResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TypeTissuRequest request) {
        TypeTissuResponse response = typeTissuService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un type de tissu
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeTissuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

