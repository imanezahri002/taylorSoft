package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.CommandeStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientRequest {

    @NotNull(message = "L'ID du client est obligatoire")
    private Long clientId;

    private CommandeStatus statut; // Optionnel, EN_ATTENTE par défaut

    @NotEmpty(message = "La commande doit contenir au moins un item")
    @Valid
    private List<CommandeClientItemRequest> items;
}

