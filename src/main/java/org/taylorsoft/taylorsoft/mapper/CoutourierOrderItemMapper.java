package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taylorsoft.taylorsoft.dtos.request.CoutourierOrderItemRequest;
import org.taylorsoft.taylorsoft.dtos.response.CoutourierOrderItemResponse;
import org.taylorsoft.taylorsoft.entity.CoutourierOrderItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoutourierOrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coutourierOrder", ignore = true)
    @Mapping(target = "tissuColor", ignore = true)
    @Mapping(target = "prixTotalMetres", ignore = true)
    CoutourierOrderItem toEntity(CoutourierOrderItemRequest request);

    @Mapping(source = "tissuColor.id", target = "tissuColorId")
    @Mapping(source = "tissuColor.tissu.nom", target = "tissuNom")
    @Mapping(source = "tissuColor.couleur.nom", target = "couleurNom")
    @Mapping(source = "tissuColor.photo", target = "tissuColorPhoto")
    CoutourierOrderItemResponse toResponse(CoutourierOrderItem item);

    List<CoutourierOrderItemResponse> toResponseList(List<CoutourierOrderItem> items);
}


