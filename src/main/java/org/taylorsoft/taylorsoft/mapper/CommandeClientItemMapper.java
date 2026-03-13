package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taylorsoft.taylorsoft.dtos.request.CommandeClientItemRequest;
import org.taylorsoft.taylorsoft.dtos.response.CommandeClientItemResponse;
import org.taylorsoft.taylorsoft.entity.CommandeClientItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandeClientItemMapper {

    @Mapping(target = "modelColorId", source = "modelColor.id")
    @Mapping(target = "modelNom", source = "modelColor.model.nom")
    @Mapping(target = "couleurNom", source = "modelColor.couleur.nom")
    @Mapping(target = "sousTotal", expression = "java(item.getQuantite() * item.getPrixUnitaire())")
    CommandeClientItemResponse toResponse(CommandeClientItem item);

    List<CommandeClientItemResponse> toResponseList(List<CommandeClientItem> items);
}

