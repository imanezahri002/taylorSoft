package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.Ordre;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelPhotoResponse {

    private Long id;
    private String photoUrl;
    private Boolean principal;
    private Ordre order;
}

