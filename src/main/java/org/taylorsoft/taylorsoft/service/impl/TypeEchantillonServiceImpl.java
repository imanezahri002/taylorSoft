package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.TypeEchantillonRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeEchantillonResponse;
import org.taylorsoft.taylorsoft.entity.TypeEchantillon;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.TypeEchantillonMapper;
import org.taylorsoft.taylorsoft.repository.TypeEchantillonRepository;
import org.taylorsoft.taylorsoft.service.TypeEchantillonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeEchantillonServiceImpl implements TypeEchantillonService {

    private final TypeEchantillonRepository typeEchantillonRepository;
    private final TypeEchantillonMapper typeEchantillonMapper;

    @Override
    @Transactional
    public TypeEchantillonResponse create(TypeEchantillonRequest request) {
        // Vérifier si le nom existe déjà
        if (typeEchantillonRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Un type d'échantillon avec ce nom existe déjà");
        }

        // Mapper la requête vers l'entité
        TypeEchantillon typeEchantillon = typeEchantillonMapper.toEntity(request);

        // Sauvegarder
        TypeEchantillon savedTypeEchantillon = typeEchantillonRepository.save(typeEchantillon);

        // Retourner la réponse
        return typeEchantillonMapper.toResponse(savedTypeEchantillon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeEchantillonResponse> getAll() {
        List<TypeEchantillon> typeEchantillons = typeEchantillonRepository.findAll();
        return typeEchantillonMapper.toResponseList(typeEchantillons);
    }

    @Override
    @Transactional(readOnly = true)
    public TypeEchantillonResponse getById(Long id) {
        TypeEchantillon typeEchantillon = typeEchantillonRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Type d'échantillon non trouvé avec l'ID: " + id));

        return typeEchantillonMapper.toResponse(typeEchantillon);
    }

    @Override
    @Transactional
    public TypeEchantillonResponse update(Long id, TypeEchantillonRequest request) {
        // Vérifier que le type d'échantillon existe
        TypeEchantillon typeEchantillon = typeEchantillonRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Type d'échantillon non trouvé avec l'ID: " + id));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est le même)
        if (!typeEchantillon.getNom().equals(request.getNom()) &&
            typeEchantillonRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Un type d'échantillon avec ce nom existe déjà");
        }

        // Mettre à jour l'entité
        typeEchantillonMapper.updateEntityFromRequest(request, typeEchantillon);

        // Sauvegarder
        TypeEchantillon updatedTypeEchantillon = typeEchantillonRepository.save(typeEchantillon);

        // Retourner la réponse
        return typeEchantillonMapper.toResponse(updatedTypeEchantillon);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Vérifier que le type d'échantillon existe
        if (!typeEchantillonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Type d'échantillon non trouvé avec l'ID: " + id);
        }

        // Supprimer
        typeEchantillonRepository.deleteById(id);
    }
}

