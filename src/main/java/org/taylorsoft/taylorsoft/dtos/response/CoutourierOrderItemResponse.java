package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrderItemResponse {

    private Long id;
    private Double nombreMetres;
    private Double prixTotalMetres;

    // Informations du TissuColor
    private Long tissuColorId;
    private String tissuNom;
    private String couleurNom;
    private String tissuColorPhoto;
}


