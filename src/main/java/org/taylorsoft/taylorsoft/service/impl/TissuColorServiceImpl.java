package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.TissuColorRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuColorResponse;
import org.taylorsoft.taylorsoft.entity.Couleur;
import org.taylorsoft.taylorsoft.entity.Fournisseur;
import org.taylorsoft.taylorsoft.entity.Tissu;
import org.taylorsoft.taylorsoft.entity.TissuColor;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.TissuColorMapper;
import org.taylorsoft.taylorsoft.repository.CouleurRepository;
import org.taylorsoft.taylorsoft.repository.FournisseurRepository;
import org.taylorsoft.taylorsoft.repository.TissuColorRepository;
import org.taylorsoft.taylorsoft.repository.TissuRepository;
import org.taylorsoft.taylorsoft.service.TissuColorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TissuColorServiceImpl implements TissuColorService {

    private final TissuColorRepository tissuColorRepository;
    private final CouleurRepository couleurRepository;
    private final TissuRepository tissuRepository;
    private final FournisseurRepository fournisseurRepository;
    private final TissuColorMapper tissuColorMapper;

    @Override
    public TissuColorResponse create(TissuColorRequest request) {
        // Vérifier que la couleur existe
        Couleur couleur = couleurRepository.findById(request.getCouleurId())
                .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + request.getCouleurId()));

        // Vérifier que le tissu existe
        Tissu tissu = tissuRepository.findById(request.getTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + request.getTissuId()));

        // Vérifier que le fournisseur existe
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé avec l'ID: " + request.getFournisseurId()));

        TissuColor tissuColor = tissuColorMapper.toEntity(request);
        tissuColor.setCouleur(couleur);
        tissuColor.setTissu(tissu);
        tissuColor.setFournisseur(fournisseur);

        TissuColor savedTissuColor = tissuColorRepository.save(tissuColor);
        return tissuColorMapper.toResponse(savedTissuColor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuColorResponse> getAll() {
        return tissuColorRepository.findAll().stream()
                .map(tissuColorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TissuColorResponse getById(Long id) {
        TissuColor tissuColor = tissuColorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TissuColor non trouvé avec l'ID: " + id));
        return tissuColorMapper.toResponse(tissuColor);
    }

    @Override
    public TissuColorResponse update(Long id, TissuColorRequest request) {
        TissuColor tissuColor = tissuColorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TissuColor non trouvé avec l'ID: " + id));

        // Vérifier que la couleur existe
        Couleur couleur = couleurRepository.findById(request.getCouleurId())
                .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + request.getCouleurId()));

        // Vérifier que le tissu existe
        Tissu tissu = tissuRepository.findById(request.getTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + request.getTissuId()));

        // Vérifier que le fournisseur existe
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé avec l'ID: " + request.getFournisseurId()));

        tissuColorMapper.updateEntityFromRequest(request, tissuColor);
        tissuColor.setCouleur(couleur);
        tissuColor.setTissu(tissu);
        tissuColor.setFournisseur(fournisseur);

        TissuColor updatedTissuColor = tissuColorRepository.save(tissuColor);
        return tissuColorMapper.toResponse(updatedTissuColor);
    }

    @Override
    public void delete(Long id) {
        if (!tissuColorRepository.existsById(id)) {
            throw new ResourceNotFoundException("TissuColor non trouvé avec l'ID: " + id);
        }
        tissuColorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuColorResponse> getByTissuId(Long tissuId) {
        return tissuColorRepository.findByTissuId(tissuId).stream()
                .map(tissuColorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuColorResponse> getByCouleurId(Long couleurId) {
        return tissuColorRepository.findByCouleurId(couleurId).stream()
                .map(tissuColorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuColorResponse> getByFournisseurId(Long fournisseurId) {
        return tissuColorRepository.findByFournisseurId(fournisseurId).stream()
                .map(tissuColorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TissuColorResponse> getActiveOnly() {
        return tissuColorRepository.findByActiveTrue().stream()
                .map(tissuColorMapper::toResponse)
                .collect(Collectors.toList());
    }
}


