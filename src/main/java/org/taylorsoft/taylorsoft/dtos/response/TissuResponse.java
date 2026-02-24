package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TissuResponse {

    private Long id;
    private String reference;
    private String nom;
    private Double largeur;
    private Boolean active;
    private Long typeTissuId;
    private String typeTissuNom;
}

