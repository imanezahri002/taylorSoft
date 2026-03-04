package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderRequest;
import org.taylorsoft.taylorsoft.dtos.response.CoutourierOrderResponse;
import org.taylorsoft.taylorsoft.entity.CoutourierOrder;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoutourierOrderItemMapper.class})
public interface CoutourierOrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "prixTotal", ignore = true)
    @Mapping(target = "coutourier", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    @Mapping(target = "items", ignore = true)
    CoutourierOrder toEntity(CoutourierOrderRequest request);

    @Mapping(source = "coutourier.id", target = "couturierId")
    @Mapping(source = "coutourier.nom", target = "coutourierNom")
    @Mapping(source = "coutourier.email", target = "coutourierEmail")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "fournisseur.nom", target = "fournisseurNom")
    @Mapping(source = "fournisseur.email", target = "fournisseurEmail")
    CoutourierOrderResponse toResponse(CoutourierOrder coutourierOrder);

    List<CoutourierOrderResponse> toResponseList(List<CoutourierOrder> coutourierOrders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "prixTotal", ignore = true)
    @Mapping(target = "coutourier", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    @Mapping(target = "items", ignore = true)
    void updateEntityFromRequest(CoutourierOrderRequest request, @MappingTarget CoutourierOrder coutourierOrder);
}


