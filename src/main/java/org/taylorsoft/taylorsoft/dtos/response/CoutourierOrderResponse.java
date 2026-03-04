package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrderResponse {

    private Long id;
    private CoutourierOrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double prixTotal;

    // Informations du Couturier
    private Long couturierId;
    private String coutourierNom;
    private String coutourierEmail;

    // Informations du Fournisseur
    private Long fournisseurId;
    private String fournisseurNom;
    private String fournisseurEmail;

    // Liste des items
    private List<CoutourierOrderItemResponse> items;
}


