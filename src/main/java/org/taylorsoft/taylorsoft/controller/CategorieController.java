package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.CategorieRequest;
import org.taylorsoft.taylorsoft.dtos.response.CategorieResponse;
import org.taylorsoft.taylorsoft.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    /**
     * Créer une nouvelle catégorie
     */
    @PostMapping
    public ResponseEntity<CategorieResponse> create(@Valid @RequestBody CategorieRequest request) {
        CategorieResponse response = categorieService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les catégories
     */
    @GetMapping
    public ResponseEntity<List<CategorieResponse>> getAll() {
        List<CategorieResponse> responses = categorieService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une catégorie par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategorieResponse> getById(@PathVariable Long id) {
        CategorieResponse response = categorieService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour une catégorie
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategorieResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategorieRequest request) {
        CategorieResponse response = categorieService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une catégorie
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

