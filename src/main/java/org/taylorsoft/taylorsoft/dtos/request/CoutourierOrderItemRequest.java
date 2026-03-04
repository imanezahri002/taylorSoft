package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrderItemRequest {

    @NotNull(message = "L'ID du tissu-couleur est obligatoire")
    private Long tissuColorId;

    @NotNull(message = "Le nombre de mètres est obligatoire")
    @Positive(message = "Le nombre de mètres doit être positif")
    private Double nombreMetres;
}

