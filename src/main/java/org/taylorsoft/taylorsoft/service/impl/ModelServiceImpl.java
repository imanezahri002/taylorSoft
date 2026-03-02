package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.ModelColorRequest;
import org.taylorsoft.taylorsoft.dtos.request.ModelPhotoRequest;
import org.taylorsoft.taylorsoft.dtos.request.ModelRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelResponse;
import org.taylorsoft.taylorsoft.entity.*;
import org.taylorsoft.taylorsoft.exception.InvalidOperationException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.ModelMapper;
import org.taylorsoft.taylorsoft.repository.*;
import org.taylorsoft.taylorsoft.service.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelColorRepository modelColorRepository;
    private final ModelPhotoRepository modelPhotoRepository;
    private final TissuRepository tissuRepository;
    private final CoutourierRepository coutourierRepository;
    private final CouleurRepository couleurRepository;
    private final CategorieRepository categorieRepository;
    private final ModelMapper modelMapper;

    @Override
    public ModelResponse create(ModelRequest request) {
        // 1. Vérifier que le tissu existe
        Tissu tissu = tissuRepository.findById(request.getTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + request.getTissuId()));

        // 2. Vérifier que le couturier existe
        Coutourier coutourier = coutourierRepository.findById(request.getCouturierId())
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé avec l'ID: " + request.getCouturierId()));

        // 3. Vérifier que la catégorie existe
        Categorie categorie = categorieRepository.findById(request.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID: " + request.getCategorieId()));

        // 4. Validation métier des couleurs et photos
        validateCouleursAndPhotos(request.getCouleurs());

        // 5. Créer le modèle
        Model model = modelMapper.toEntity(request);
        model.setTissu(tissu);
        model.setCoutourier(coutourier);
        model.setCategorie(categorie);

        // 6. Créer les couleurs et photos
        for (ModelColorRequest colorRequest : request.getCouleurs()) {
            Couleur couleur = couleurRepository.findById(colorRequest.getCouleurId())
                    .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + colorRequest.getCouleurId()));

            ModelColor modelColor = new ModelColor();
            modelColor.setModel(model);
            modelColor.setCouleur(couleur);
            modelColor.setPhotos(new ArrayList<>());

            for (ModelPhotoRequest photoRequest : colorRequest.getPhotos()) {
                ModelPhoto photo = new ModelPhoto();
                photo.setPhotoUrl(photoRequest.getPhotoUrl());
                photo.setPrincipal(photoRequest.getPrincipal());
                photo.setOrder(photoRequest.getOrder());
                photo.setModelColor(modelColor);

                modelColor.getPhotos().add(photo);
            }

            model.getModelColors().add(modelColor);
        }

        // 7. UN SEUL SAVE - Cascade automatique
        Model savedModel = modelRepository.save(model);

        return modelMapper.toResponse(savedModel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelResponse> getAll() {
        return modelRepository.findAll().stream()
                .map(modelMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ModelResponse getById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle non trouvé avec l'ID: " + id));
        return modelMapper.toResponse(model);
    }

    @Override
    public ModelResponse update(Long id, ModelRequest request) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Modèle non trouvé avec l'ID: " + id));

        // Vérifications
        Tissu tissu = tissuRepository.findById(request.getTissuId())
                .orElseThrow(() -> new ResourceNotFoundException("Tissu non trouvé avec l'ID: " + request.getTissuId()));

        Coutourier coutourier = coutourierRepository.findById(request.getCouturierId())
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé avec l'ID: " + request.getCouturierId()));

        Categorie categorie = categorieRepository.findById(request.getCategorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID: " + request.getCategorieId()));

        // Validation
        validateCouleursAndPhotos(request.getCouleurs());

        // Mettre à jour
        modelMapper.updateEntityFromRequest(request, model);
        model.setTissu(tissu);
        model.setCoutourier(coutourier);
        model.setCategorie(categorie);

        // Supprimer les anciennes couleurs (orphanRemoval supprime les photos)
        model.getModelColors().clear();

        // Créer les nouvelles couleurs et photos
        for (ModelColorRequest couleurRequest : request.getCouleurs()) {
            Couleur couleur = couleurRepository.findById(couleurRequest.getCouleurId())
                    .orElseThrow(() -> new ResourceNotFoundException("Couleur non trouvée avec l'ID: " + couleurRequest.getCouleurId()));

            ModelColor modelColor = new ModelColor();
            modelColor.setModel(model);
            modelColor.setCouleur(couleur);
            modelColor.setPhotos(new ArrayList<>());

            for (ModelPhotoRequest photoRequest : couleurRequest.getPhotos()) {
                ModelPhoto photo = new ModelPhoto();
                photo.setPhotoUrl(photoRequest.getPhotoUrl());
                photo.setPrincipal(photoRequest.getPrincipal());
                photo.setOrder(photoRequest.getOrder());
                photo.setModelColor(modelColor);

                modelColor.getPhotos().add(photo);
            }

            model.getModelColors().add(modelColor);
        }

        // UN SEUL SAVE
        Model updatedModel = modelRepository.save(model);

        return modelMapper.toResponse(updatedModel);
    }

    @Override
    public void delete(Long id) {
        if (!modelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Modèle non trouvé avec l'ID: " + id);
        }

        // UN SEUL DELETE - Cascade automatique
        modelRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelResponse> getByTissuId(Long tissuId) {
        return modelRepository.findByTissuId(tissuId).stream()
                .map(modelMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelResponse> getByCouturierId(Long couturierId) {
        Coutourier coutourier = coutourierRepository.findById(couturierId)
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé avec l'ID: " + couturierId));

        return modelRepository.findByCoutourier(coutourier).stream()
                .map(modelMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelResponse> getActiveOnly() {
        return modelRepository.findByActiveTrue().stream()
                .map(modelMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ========== Méthodes privées de validation ==========

    /**
     * Valide les couleurs et leurs photos
     * - Au moins une couleur
     * - Chaque couleur doit avoir entre 1 et 4 photos
     * - Exactement 1 photo principale par couleur
     */
    private void validateCouleursAndPhotos(List<ModelColorRequest> couleurs) {
        if (couleurs == null || couleurs.isEmpty()) {
            throw new InvalidOperationException("Au moins une couleur est requise pour le modèle");
        }

        for (int i = 0; i < couleurs.size(); i++) {
            ModelColorRequest couleur = couleurs.get(i);

            if (couleur.getPhotos() == null || couleur.getPhotos().isEmpty()) {
                throw new InvalidOperationException("La couleur " + (i + 1) + " doit avoir au moins une photo");
            }

            if (couleur.getPhotos().size() > 4) {
                throw new InvalidOperationException("La couleur " + (i + 1) + " ne peut avoir plus de 4 photos. Nombre actuel: " + couleur.getPhotos().size());
            }

            // Compter les photos principales
            long countPrincipale = couleur.getPhotos().stream()
                    .filter(photo -> photo.getPrincipal() != null && photo.getPrincipal())
                    .count();

            if (countPrincipale != 1) {
                throw new InvalidOperationException("La couleur " + (i + 1) + " doit avoir exactement 1 photo principale. Nombre actuel: " + countPrincipale);
            }
        }
    }
}

