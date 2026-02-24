package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.CouleurRequest;
import org.taylorsoft.taylorsoft.dtos.response.CouleurResponse;
import org.taylorsoft.taylorsoft.service.CouleurService;

import java.util.List;

@RestController
@RequestMapping("/api/couleur")
@RequiredArgsConstructor
public class CouleurController {

    private final CouleurService couleurService;

    /**
     * Créer une nouvelle couleur
     */
    @PostMapping
    public ResponseEntity<CouleurResponse> create(@Valid @RequestBody CouleurRequest request) {
        CouleurResponse response = couleurService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les couleurs
     */
    @GetMapping
    public ResponseEntity<List<CouleurResponse>> getAll() {
        List<CouleurResponse> responses = couleurService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une couleur par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CouleurResponse> getById(@PathVariable Long id) {
        CouleurResponse response = couleurService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour une couleur
     */
    @PutMapping("/{id}")
    public ResponseEntity<CouleurResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CouleurRequest request) {
        CouleurResponse response = couleurService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une couleur
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        couleurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

