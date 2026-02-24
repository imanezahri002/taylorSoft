package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesureRequest {

    @NotNull(message = "L'ID du client est obligatoire")
    private Long clientId;

    private Double tourCou;
    private Double poitrine;
    private Double taille;
    private Double manche;
    private Double longueurDos;
    private Double jambe;
    private Double epaule;
    private Double bras;
}

