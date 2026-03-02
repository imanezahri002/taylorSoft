package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelColorResponse {

    private Long id;
    private CouleurResponse couleur;
    // Photos associées
    private List<ModelPhotoResponse> photos;
}

