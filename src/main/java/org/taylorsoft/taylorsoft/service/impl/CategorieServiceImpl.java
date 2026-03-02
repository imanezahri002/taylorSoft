package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.CategorieRequest;
import org.taylorsoft.taylorsoft.dtos.response.CategorieResponse;
import org.taylorsoft.taylorsoft.entity.Categorie;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.CategorieMapper;
import org.taylorsoft.taylorsoft.repository.CategorieRepository;
import org.taylorsoft.taylorsoft.service.CategorieService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    @Override
    public CategorieResponse create(CategorieRequest request) {
        // Vérifier si une catégorie avec le même nom existe déjà
        if (categorieRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Une catégorie avec le nom '" + request.getNom() + "' existe déjà");
        }

        Categorie categorie = categorieMapper.toEntity(request);
        Categorie savedCategorie = categorieRepository.save(categorie);

        return categorieMapper.toResponse(savedCategorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieResponse> getAll() {
        List<Categorie> categories = categorieRepository.findAll();
        return categorieMapper.toResponseList(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieResponse getById(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID: " + id));

        return categorieMapper.toResponse(categorie);
    }

    @Override
    public CategorieResponse update(Long id, CategorieRequest request) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID: " + id));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est le même)
        if (!categorie.getNom().equals(request.getNom()) && categorieRepository.existsByNom(request.getNom())) {
            throw new DuplicateResourceException("Une catégorie avec le nom '" + request.getNom() + "' existe déjà");
        }

        categorieMapper.updateEntityFromRequest(request, categorie);
        Categorie updatedCategorie = categorieRepository.save(categorie);

        return categorieMapper.toResponse(updatedCategorie);
    }

    @Override
    public void delete(Long id) {
        if (!categorieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Catégorie non trouvée avec l'ID: " + id);
        }

        categorieRepository.deleteById(id);
    }
}

