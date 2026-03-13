package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taylorsoft.taylorsoft.dtos.response.CommandeClientResponse;
import org.taylorsoft.taylorsoft.entity.CommandeClient;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommandeClientItemMapper.class})
public interface CommandeClientMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientNom", source = "client.nom")
    @Mapping(target = "clientPrenom", source = "client.prenom")
    CommandeClientResponse toResponse(CommandeClient commande);

    List<CommandeClientResponse> toResponseList(List<CommandeClient> commandes);
}

