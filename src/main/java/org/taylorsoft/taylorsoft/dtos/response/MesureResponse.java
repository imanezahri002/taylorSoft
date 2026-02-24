package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesureResponse {

    private Long id;
    private Long clientId;
    private String clientNom;
    private String clientPrenom;
    private Double tourCou;
    private Double poitrine;
    private Double taille;
    private Double manche;
    private Double longueurDos;
    private Double jambe;
    private Double epaule;
    private Double bras;
}

