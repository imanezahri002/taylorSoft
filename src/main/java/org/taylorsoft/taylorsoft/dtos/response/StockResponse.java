package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {

    private Long id;
    private TissuColorResponse tissuColor;
    private Long couturierId;
    private String coutourierNom;
    private String coutourierPrenom;
    private Double quantiteOnHand;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<StockMouvementResponse> mouvements;
}

