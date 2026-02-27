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
public class TissuColorRequest {

    @NotBlank(message = "La photo est obligatoire")
    private String photo;

    @NotNull(message = "La quantité est obligatoire")
    private Double quantity;

    @NotNull(message = "Le statut actif est obligatoire")
    private Boolean active;

    @NotNull(message = "L'ID de la couleur est obligatoire")
    private Long couleurId;

    @NotNull(message = "L'ID du tissu est obligatoire")
    private Long tissuId;

    @NotNull(message = "L'ID du fournisseur est obligatoire")
    private Long fournisseurId;
}
