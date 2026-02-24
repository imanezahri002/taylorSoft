package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.TypeTissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeTissuResponse;
import org.taylorsoft.taylorsoft.entity.TypeTissu;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.TypeTissuMapper;
import org.taylorsoft.taylorsoft.repository.TypeTissuRepository;
import org.taylorsoft.taylorsoft.service.TypeTissuService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeTissuServiceImpl implements TypeTissuService {

    private final TypeTissuRepository typeTissuRepository;
    private final TypeTissuMapper typeTissuMapper;

    @Override
    @Transactional
    public TypeTissuResponse create(TypeTissuRequest request) {
        // Vérifier si le nom existe déjà
        if (typeTissuRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Un type de tissu avec ce nom existe déjà");
        }

        // Mapper la requête vers l'entité
        TypeTissu typeTissu = typeTissuMapper.toEntity(request);

        // Sauvegarder
        TypeTissu savedTypeTissu = typeTissuRepository.save(typeTissu);

        // Retourner la réponse
        return typeTissuMapper.toResponse(savedTypeTissu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeTissuResponse> getAll() {
        List<TypeTissu> typeTissus = typeTissuRepository.findAll();
        return typeTissuMapper.toResponseList(typeTissus);
    }

    @Override
    @Transactional(readOnly = true)
    public TypeTissuResponse getById(Long id) {
        TypeTissu typeTissu = typeTissuRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Type de tissu non trouvé avec l'ID: " + id));

        return typeTissuMapper.toResponse(typeTissu);
    }

    @Override
    @Transactional
    public TypeTissuResponse update(Long id, TypeTissuRequest request) {
        // Vérifier que le type de tissu existe
        TypeTissu typeTissu = typeTissuRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Type de tissu non trouvé avec l'ID: " + id));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est le même)
        if (!typeTissu.getNom().equals(request.getNom()) &&
            typeTissuRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Un type de tissu avec ce nom existe déjà");
        }

        // Mettre à jour l'entité
        typeTissuMapper.updateEntityFromRequest(request, typeTissu);

        // Sauvegarder
        TypeTissu updatedTypeTissu = typeTissuRepository.save(typeTissu);

        // Retourner la réponse
        return typeTissuMapper.toResponse(updatedTypeTissu);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Vérifier que le type de tissu existe
        if (!typeTissuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Type de tissu non trouvé avec l'ID: " + id);
        }

        // Supprimer
        typeTissuRepository.deleteById(id);
    }
}

