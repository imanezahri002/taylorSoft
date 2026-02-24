package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.MesureRequest;
import org.taylorsoft.taylorsoft.dtos.response.MesureResponse;
import org.taylorsoft.taylorsoft.entity.Mesure;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesureMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.nom", target = "clientNom")
    @Mapping(source = "client.prenom", target = "clientPrenom")
    MesureResponse toResponse(Mesure mesure);

    List<MesureResponse> toResponseList(List<Mesure> mesures);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Mesure toEntity(MesureRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    void updateEntityFromRequest(MesureRequest request, @MappingTarget Mesure mesure);
}

