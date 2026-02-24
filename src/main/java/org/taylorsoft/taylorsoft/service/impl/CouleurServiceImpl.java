package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.CouleurRequest;
import org.taylorsoft.taylorsoft.dtos.response.CouleurResponse;
import org.taylorsoft.taylorsoft.entity.Couleur;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.CouleurMapper;
import org.taylorsoft.taylorsoft.repository.CouleurRepository;
import org.taylorsoft.taylorsoft.service.CouleurService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouleurServiceImpl implements CouleurService {

    private final CouleurRepository couleurRepository;
    private final CouleurMapper couleurMapper;

    @Override
    @Transactional
    public CouleurResponse create(CouleurRequest request) {
        // Vérifier si le nom existe déjà
        if (couleurRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Une couleur avec ce nom existe déjà");
        }

        // Vérifier si le code hexadécimal existe déjà
        if (couleurRepository.existsByCodeHex(request.getCodeHex())) {
            throw new DuplicateResourceException("Une couleur avec ce code hexadécimal existe déjà");
        }

        // Mapper la requête vers l'entité
        Couleur couleur = couleurMapper.toEntity(request);

        // Sauvegarder
        Couleur savedCouleur = couleurRepository.save(couleur);

        // Retourner la réponse
        return couleurMapper.toResponse(savedCouleur);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CouleurResponse> getAll() {
        List<Couleur> couleurs = couleurRepository.findAll();
        return couleurMapper.toResponseList(couleurs);
    }

    @Override
    @Transactional(readOnly = true)
    public CouleurResponse getById(Long id) {
        Couleur couleur = couleurRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + id));

        return couleurMapper.toResponse(couleur);
    }

    @Override
    @Transactional
    public CouleurResponse update(Long id, CouleurRequest request) {
        // Vérifier que la couleur existe
        Couleur couleur = couleurRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + id));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est le même)
        if (!couleur.getNom().equals(request.getNom()) &&
            couleurRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Une couleur avec ce nom existe déjà");
        }

        // Vérifier si le nouveau code hexadécimal existe déjà (sauf si c'est le même)
        if (!couleur.getCodeHex().equals(request.getCodeHex()) &&
            couleurRepository.existsByCodeHex(request.getCodeHex())) {
            throw new DuplicateResourceException("Une couleur avec ce code hexadécimal existe déjà");
        }

        // Mettre à jour l'entité
        couleurMapper.updateEntityFromRequest(request, couleur);

        // Sauvegarder
        Couleur updatedCouleur = couleurRepository.save(couleur);

        // Retourner la réponse
        return couleurMapper.toResponse(updatedCouleur);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Vérifier que la couleur existe
        if (!couleurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + id);
        }

        // Supprimer
        couleurRepository.deleteById(id);
    }
}

