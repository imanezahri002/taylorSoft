package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelColorRequest {

    @NotNull(message = "La couleur est obligatoire")
    private Long couleurId;

    @NotEmpty(message = "Les photos sont obligatoires")
    @Size(min = 1, max = 4, message = "Un modèle-couleur doit avoir entre 1 et 4 photos")
    @Valid
    private List<ModelPhotoRequest> photos;
}

