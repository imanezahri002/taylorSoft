package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelRequest {

    @NotBlank(message = "Le nom du modèle est obligatoire")
    private String nom;

    @NotBlank(message = "La description du modèle est obligatoire")
    private String description;

    @NotNull(message = "Le tissu est obligatoire")
    private Long tissuId;

    @NotNull(message = "La catégorie est obligatoire")
    private Long categorieId;

    @NotNull(message = "Le couturier est obligatoire")
    private Long couturierId;

    @NotNull(message = "Le prix est obligatoire")
    private Double prix;

    @NotEmpty(message = "Au moins une couleur est obligatoire")
    @Valid
    private List<ModelColorRequest> couleurs;

    private boolean active = true;
}

