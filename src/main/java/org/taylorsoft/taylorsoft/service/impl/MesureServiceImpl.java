package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.MesureRequest;
import org.taylorsoft.taylorsoft.dtos.response.MesureResponse;
import org.taylorsoft.taylorsoft.entity.Client;
import org.taylorsoft.taylorsoft.entity.Mesure;
import org.taylorsoft.taylorsoft.exception.DuplicateResourceException;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.MesureMapper;
import org.taylorsoft.taylorsoft.repository.ClientRepository;
import org.taylorsoft.taylorsoft.repository.MesureRepository;
import org.taylorsoft.taylorsoft.service.MesureService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesureServiceImpl implements MesureService {

    private final MesureRepository mesureRepository;
    private final MesureMapper mesureMapper;
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public MesureResponse create(MesureRequest request) {

        // Vérifier que le client existe
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client non trouvé avec l'ID: " + request.getClientId()
                ));

        // Vérifier si une mesure existe déjà pour ce client (OneToOne)
        if (mesureRepository.existsByClientId(request.getClientId())) {
            throw new DuplicateResourceException(
                    "Ce client possède déjà une mesure"
            );
        }

        // Mapper request → entity
        Mesure mesure = mesureMapper.toEntity(request);

        // Associer le client
        mesure.setClient(client);

        // Sauvegarder
        Mesure savedMesure = mesureRepository.save(mesure);

        // Retourner response
        return mesureMapper.toResponse(savedMesure);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesureResponse> getAll() {
        List<Mesure> mesures = mesureRepository.findAll();
        return mesureMapper.toResponseList(mesures);
    }

    @Override
    @Transactional(readOnly = true)
    public MesureResponse getById(Long id) {
        Mesure mesure = mesureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Mesure non trouvée avec l'ID: " + id
                ));

        return mesureMapper.toResponse(mesure);
    }

    @Override
    @Transactional(readOnly = true)
    public MesureResponse getByClientId(Long clientId) {
        Mesure mesure = mesureRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aucune mesure trouvée pour le client ID: " + clientId
                ));

        return mesureMapper.toResponse(mesure);
    }

    @Override
    @Transactional
    public MesureResponse update(Long id, MesureRequest request) {

        // Vérifier que la mesure existe
        Mesure mesure = mesureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Mesure non trouvée avec l'ID: " + id
                ));

        // Mettre à jour les champs
        mesureMapper.updateEntityFromRequest(request, mesure);

        // Sauvegarder
        Mesure updatedMesure = mesureRepository.save(mesure);

        return mesureMapper.toResponse(updatedMesure);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!mesureRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Mesure non trouvée avec l'ID: " + id
            );
        }

        mesureRepository.deleteById(id);
    }
}