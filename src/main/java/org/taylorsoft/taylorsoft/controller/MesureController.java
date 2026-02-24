package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.MesureRequest;
import org.taylorsoft.taylorsoft.dtos.response.MesureResponse;
import org.taylorsoft.taylorsoft.service.MesureService;

import java.util.List;

@RestController
@RequestMapping("/api/mesures")
@RequiredArgsConstructor
public class MesureController {

    private final MesureService mesureService;

    /**
     * Créer une nouvelle mesure
     */
    @PostMapping
    public ResponseEntity<MesureResponse> create(@Valid @RequestBody MesureRequest request) {
        MesureResponse response = mesureService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer toutes les mesures
     */
    @GetMapping
    public ResponseEntity<List<MesureResponse>> getAll() {
        List<MesureResponse> responses = mesureService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer une mesure par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MesureResponse> getById(@PathVariable Long id) {
        MesureResponse response = mesureService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Récupérer une mesure par ID du client
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<MesureResponse> getByClientId(@PathVariable Long clientId) {
        MesureResponse response = mesureService.getByClientId(clientId);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour une mesure
     */
    @PutMapping("/{id}")
    public ResponseEntity<MesureResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody MesureRequest request) {
        MesureResponse response = mesureService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer une mesure
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mesureService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

