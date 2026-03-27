package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderItemRequest;
import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderRequest;
import org.taylorsoft.taylorsoft.dtos.response.CoutourierOrderResponse;
import org.taylorsoft.taylorsoft.entity.*;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.mapper.CoutourierOrderMapper;
import org.taylorsoft.taylorsoft.repository.CoutourierOrderRepository;
import org.taylorsoft.taylorsoft.repository.CoutourierRepository;
import org.taylorsoft.taylorsoft.repository.FournisseurRepository;
import org.taylorsoft.taylorsoft.repository.TissuColorRepository;
import org.taylorsoft.taylorsoft.service.CoutourierOrderService;
import org.taylorsoft.taylorsoft.service.StockService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CoutourierOrderServiceImpl implements CoutourierOrderService {

    private final CoutourierOrderRepository repository;
    private final CoutourierRepository coutourierRepository;
    private final FournisseurRepository fournisseurRepository;
    private final TissuColorRepository tissuColorRepository;
    private final CoutourierOrderMapper mapper;
    private final StockService stockService;

    @Override
    public CoutourierOrderResponse create(CoutourierOrderRequest request) {
        // Vérifier que le couturier existe
        Coutourier coutourier = coutourierRepository.findById(request.getCouturierId())
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé"));

        // Vérifier que le fournisseur existe
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé"));

        // Créer la commande avec le statut défini à EN_ATTENTE par défaut
        CoutourierOrder order = new CoutourierOrder();
        order.setStatus(CoutourierOrderStatus.EN_ATTENTE);
        order.setCoutourier(coutourier);
        order.setFournisseur(fournisseur);

        // Traiter les items et calculer les prix
        double prixTotalCommande = 0.0;

        for (CoutourierOrderItemRequest itemRequest : request.getItems()) {
            // Récupérer le TissuColor
            TissuColor tissuColor = tissuColorRepository.findById(itemRequest.getTissuColorId())
                    .orElseThrow(() -> new ResourceNotFoundException("TissuColor non trouvé avec l'ID: " + itemRequest.getTissuColorId()));

            // Créer l'item
            CoutourierOrderItem item = new CoutourierOrderItem();
            item.setCoutourierOrder(order);
            item.setTissuColor(tissuColor);
            item.setNombreMetres(itemRequest.getNombreMetres());

            // Calculer le prix total pour cet item
            double prixTotalItem = itemRequest.getNombreMetres() * tissuColor.getTissu().getPrixMetre();
            item.setPrixTotalMetres(prixTotalItem);

            // Ajouter au prix total de la commande
            prixTotalCommande += prixTotalItem;

            // Ajouter l'item à la commande
            order.getItems().add(item);
        }

        // Définir le prix total de la commande
        order.setPrixTotal(prixTotalCommande);

        return mapper.toResponse(repository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoutourierOrderResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CoutourierOrderResponse getById(Long id) {
        CoutourierOrder order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID: " + id));
        return mapper.toResponse(order);
    }

    @Override
    public CoutourierOrderResponse update(Long id, CoutourierOrderRequest request) {
        CoutourierOrder order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée"));

        Coutourier coutourier = coutourierRepository.findById(request.getCouturierId())
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé"));

        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé"));

        mapper.updateEntityFromRequest(request, order);
        order.setCoutourier(coutourier);
        order.setFournisseur(fournisseur);

        // Supprimer les anciens items (orphanRemoval les supprimera automatiquement)
        order.getItems().clear();

        // Recalculer les items et les prix
        double prixTotalCommande = 0.0;

        for (CoutourierOrderItemRequest itemRequest : request.getItems()) {
            TissuColor tissuColor = tissuColorRepository.findById(itemRequest.getTissuColorId())
                    .orElseThrow(() -> new ResourceNotFoundException("TissuColor non trouvé avec l'ID: " + itemRequest.getTissuColorId()));

            CoutourierOrderItem item = new CoutourierOrderItem();
            item.setCoutourierOrder(order);
            item.setTissuColor(tissuColor);
            item.setNombreMetres(itemRequest.getNombreMetres());

            double prixTotalItem = itemRequest.getNombreMetres() * tissuColor.getTissu().getPrixMetre();
            item.setPrixTotalMetres(prixTotalItem);

            prixTotalCommande += prixTotalItem;

            order.getItems().add(item);
        }

        order.setPrixTotal(prixTotalCommande);

        return mapper.toResponse(repository.save(order));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Commande non trouvée");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoutourierOrderResponse> getByCouturierId(Long couturierId) {
        return repository.findByCoutourier_Id(couturierId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoutourierOrderResponse> getByFournisseurId(Long fournisseurId) {
        return repository.findByFournisseur_Id(fournisseurId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoutourierOrderResponse> getByStatus(CoutourierOrderStatus status) {
        return repository.findByStatus(status).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CoutourierOrderResponse updateStatus(Long id, CoutourierOrderStatus status) {
        CoutourierOrder order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée"));

        CoutourierOrderStatus oldStatus = order.getStatus();
        order.setStatus(status);

        CoutourierOrder savedOrder = repository.save(order);

        // Si le statut passe à LIVREE, mettre à jour le stock automatiquement
        if (status == CoutourierOrderStatus.LIVREE && oldStatus != CoutourierOrderStatus.LIVREE) {
            stockService.updateStockFromOrder(savedOrder);
        }

        return mapper.toResponse(savedOrder);
    }
}



