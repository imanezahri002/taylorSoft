package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientItemResponse {

    private Long id;
    private Long modelColorId;
    private String modelNom;
    private String couleurNom;
    private Double quantite;
    private Double prixUnitaire;
    private Double sousTotal; // quantite * prixUnitaire
}

