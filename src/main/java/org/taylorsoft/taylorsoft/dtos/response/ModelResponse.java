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
public class ModelResponse {

    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private TissuResponse tissu;
    private List<ModelColorResponse> couleurs;
    private boolean active;
}

