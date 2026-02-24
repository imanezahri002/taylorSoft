package org.taylorsoft.taylorsoft.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taylorsoft.taylorsoft.dtos.request.TypeEchantillonRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeEchantillonResponse;
import org.taylorsoft.taylorsoft.service.TypeEchantillonService;

import java.util.List;

@RestController
@RequestMapping("/api/type-echantillon")
@RequiredArgsConstructor
public class TypeEchantillonController {

    private final TypeEchantillonService typeEchantillonService;

    /**
     * Créer un nouveau type d'échantillon
     */
    @PostMapping
    public ResponseEntity<TypeEchantillonResponse> create(@Valid @RequestBody TypeEchantillonRequest request) {
        TypeEchantillonResponse response = typeEchantillonService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Récupérer tous les types d'échantillon
     */
    @GetMapping
    public ResponseEntity<List<TypeEchantillonResponse>> getAll() {
        List<TypeEchantillonResponse> responses = typeEchantillonService.getAll();
        return ResponseEntity.ok(responses);
    }

    /**
     * Récupérer un type d'échantillon par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeEchantillonResponse> getById(@PathVariable Long id) {
        TypeEchantillonResponse response = typeEchantillonService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Mettre à jour un type d'échantillon
     */
    @PutMapping("/{id}")
    public ResponseEntity<TypeEchantillonResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TypeEchantillonRequest request) {
        TypeEchantillonResponse response = typeEchantillonService.update(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Supprimer un type d'échantillon
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeEchantillonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

