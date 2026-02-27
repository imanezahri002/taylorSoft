package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TissuColorResponse {

    private Long id;
    private String photo;
    private Double quantity;
    private Boolean active;

    // Couleur
    private Long couleurId;
    private String couleurNom;
    private String couleurCodeHex;

    // Tissu
    private Long tissuId;
    private String tissuReference;
    private String tissuNom;

    // Fournisseur
    private Long fournisseurId;
    private String fournisseurNom;
    private String fournisseurEmail;
}


