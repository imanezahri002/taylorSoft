package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.TissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuResponse;
import org.taylorsoft.taylorsoft.entity.Tissu;
import org.taylorsoft.taylorsoft.entity.TypeTissu;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.TissuMapper;
import org.taylorsoft.taylorsoft.repository.TissuRepository;
import org.taylorsoft.taylorsoft.repository.TypeTissuRepository;
import org.taylorsoft.taylorsoft.service.TissuService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TissuServiceImpl implements TissuService {

    private final TissuRepository tissuRepository;
    private final TypeTissuRepository typeTissuRepository;
    private final TissuMapper tissuMapper;

    @Override
    public TissuResponse create(TissuRequest request) {
        // Vérifier si la référence existe déjà
        if (tissuRepository.existsByReference(request.getReference())) {
            throw new RuntimeException("Un tissu avec cette référence existe déjà");
        }

        // Vérifier que le type de tissu existe
        TypeTissu typeTissu = typeTissuRepository.findById(request.getTypeTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Type de tissu non trouvé avec l'ID: " + request.getTypeTissuId()));

        Tissu tissu = tissuMapper.toEntity(request);
        tissu.setTypeTissu(typeTissu);

        Tissu savedTissu = tissuRepository.save(tissu);
        return tissuMapper.toResponse(savedTissu);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuResponse> getAll() {
        return tissuRepository.findAll().stream()
                .map(tissuMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TissuResponse getById(Long id) {
        Tissu tissu = tissuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + id));
        return tissuMapper.toResponse(tissu);
    }

    @Override
    public TissuResponse update(Long id, TissuRequest request) {
        Tissu tissu = tissuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + id));

        // Vérifier si la référence existe déjà pour un autre tissu
        if (!tissu.getReference().equals(request.getReference()) &&
                tissuRepository.existsByReference(request.getReference())) {
            throw new RuntimeException("Un tissu avec cette référence existe déjà");
        }

        // Vérifier que le type de tissu existe
        TypeTissu typeTissu = typeTissuRepository.findById(request.getTypeTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Type de tissu non trouvé avec l'ID: " + request.getTypeTissuId()));

        tissuMapper.updateEntityFromRequest(request, tissu);
        tissu.setTypeTissu(typeTissu);

        Tissu updatedTissu = tissuRepository.save(tissu);
        return tissuMapper.toResponse(updatedTissu);
    }

    @Override
    public void delete(Long id) {
        if (!tissuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + id);
        }
        tissuRepository.deleteById(id);
    }
}

