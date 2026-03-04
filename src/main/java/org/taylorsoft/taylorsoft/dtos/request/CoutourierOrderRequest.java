package org.taylorsoft.taylorsoft.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.taylorsoft.taylorsoft.entity.enums.CoutourierOrderStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoutourierOrderRequest {

    @NotNull(message = "Le statut est obligatoire")
    private CoutourierOrderStatus status;

    @NotNull(message = "L'ID du couturier est obligatoire")
    private Long couturierId;

    @NotNull(message = "L'ID du fournisseur est obligatoire")
    private Long fournisseurId;

    @NotEmpty(message = "Au moins un item est obligatoire")
    @Valid
    private List<CoutourierOrderItemRequest> items;
}


