package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TissuRequest {

    @NotBlank(message = "La référence est obligatoire")
    private String reference;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    //add pric metre
    @NotNull(message = "Le prix par mètre est obligatoire")
    private Double prixMetre;

    private Double largeur;

    private Boolean active;

    @NotNull(message = "L'ID du type de tissu est obligatoire")
    private Long typeTissuId;
}

