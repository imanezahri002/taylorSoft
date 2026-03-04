package org.taylorsoft.taylorsoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taylorsoft.taylorsoft.entity.*;
import org.taylorsoft.taylorsoft.entity.enums.MovementType;
import org.taylorsoft.taylorsoft.exception.ResourceNotFoundException;
import org.taylorsoft.taylorsoft.repository.CoutourierRepository;
import org.taylorsoft.taylorsoft.repository.StockRepository;
import org.taylorsoft.taylorsoft.repository.TissuColorRepository;
import org.taylorsoft.taylorsoft.service.StockService;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final TissuColorRepository tissuColorRepository;
    private final CoutourierRepository coutourierRepository;

    @Override
    public void updateStockFromOrder(CoutourierOrder order) {
        log.info("Mise à jour du stock pour la commande ID: {}", order.getId());

        Coutourier coutourier = order.getCoutourier();

        // Pour chaque item de la commande
        for (CoutourierOrderItem item : order.getItems()) {
            TissuColor tissuColor = item.getTissuColor();
            Double quantite = item.getNombreMetres();

            // Rechercher le stock existant pour ce tissuColor et ce couturier
            Stock stock = stockRepository
                    .findByTissuColor_IdAndCoutourier_Id(tissuColor.getId(), coutourier.getId())
                    .orElse(null);

            if (stock == null) {
                // Créer un nouveau stock
                log.info("Création d'un nouveau stock pour TissuColor ID: {} et Couturier ID: {}",
                        tissuColor.getId(), coutourier.getId());

                stock = Stock.builder()
                        .tissuColor(tissuColor)
                        .coutourier(coutourier)
                        .quantiteOnHand(quantite)
                        .build();
            } else {
                // Ajouter à la quantité existante
                log.info("Ajout de {} mètres au stock existant ID: {}", quantite, stock.getId());
                stock.setQuantiteOnHand(stock.getQuantiteOnHand() + quantite);
            }

            // Créer le mouvement de stock
            StockMouvement mouvement = StockMouvement.builder()
                    .stock(stock)
                    .quantite(quantite)
                    .type(MovementType.IN)
                    .dateMouvement(LocalDateTime.now())
                    .description("Commande #" + order.getId() + " livrée - " +
                            tissuColor.getTissu().getNom() + " " +
                            tissuColor.getCouleur().getNom())
                    .build();

            stock.getMouvements().add(mouvement);

            // Sauvegarder (cascade sauvegarde aussi le mouvement)
            stockRepository.save(stock);

            log.info("Stock mis à jour. Nouvelle quantité: {} mètres", stock.getQuantiteOnHand());
        }
    }

    @Override
    public void addStockMovement(Long couturierId, Long tissuColorId, Double quantite,
                                   MovementType type, String description) {

        Coutourier coutourier = coutourierRepository.findById(couturierId)
                .orElseThrow(() -> new ResourceNotFoundException("Couturier non trouvé"));

        TissuColor tissuColor = tissuColorRepository.findById(tissuColorId)
                .orElseThrow(() -> new ResourceNotFoundException("TissuColor non trouvé"));

        // Rechercher le stock existant
        Stock stock = stockRepository
                .findByTissuColor_IdAndCoutourier_Id(tissuColorId, couturierId)
                .orElseGet(() -> {
                    // Créer un nouveau stock si nécessaire
                    return Stock.builder()
                            .tissuColor(tissuColor)
                            .coutourier(coutourier)
                            .quantiteOnHand(0.0)
                            .build();
                });

        // Mettre à jour la quantité selon le type
        if (type == MovementType.IN) {
            stock.setQuantiteOnHand(stock.getQuantiteOnHand() + quantite);
        } else if (type == MovementType.OUT) {
            stock.setQuantiteOnHand(stock.getQuantiteOnHand() - quantite);
        }

        // Créer le mouvement
        StockMouvement mouvement = StockMouvement.builder()
                .stock(stock)
                .quantite(quantite)
                .type(type)
                .dateMouvement(LocalDateTime.now())
                .description(description)
                .build();

        stock.getMouvements().add(mouvement);
        stockRepository.save(stock);

        log.info("Mouvement de stock ajouté: {} {} mètres", type, quantite);
    }
}

