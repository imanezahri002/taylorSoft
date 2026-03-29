package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.Ordre;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelPhotoRequest {

    @NotNull(message = "L'indicateur principal est obligatoire")
    private Boolean principal;

    @NotNull
    private Ordre photo_order;
}

