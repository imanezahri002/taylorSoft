package org.taylorsoft.taylorsoft.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientResponse {

    private Long id;
    private LocalDateTime dateCommande;
    private CommandeStatus statut;
    private Double total;
    private Long clientId;
    private String clientNom;
    private String clientPrenom;
    private List<CommandeClientItemResponse> items;
}

