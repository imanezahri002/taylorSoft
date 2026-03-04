package org.taylorsoft.taylorsoft.service;

import org.taylorsoft.taylorsoft.entity.CoutourierOrder;
import org.taylorsoft.taylorsoft.entity.enums.MovementType;

public interface StockService {

    /**
     * Mettre à jour le stock quand une commande est livrée
     */
    void updateStockFromOrder(CoutourierOrder order);

    /**
     * Ajouter un mouvement de stock manuel
     */
    void addStockMovement(Long couturierId, Long tissuColorId, Double quantite, MovementType type, String description);
}

