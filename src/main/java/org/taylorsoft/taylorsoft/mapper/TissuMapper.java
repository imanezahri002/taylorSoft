package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.TissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuResponse;
import org.taylorsoft.taylorsoft.entity.Tissu;

@Mapper(componentModel = "spring")
public interface TissuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "typeTissu", ignore = true)
    Tissu toEntity(TissuRequest request);

    @Mapping(source = "typeTissu.id", target = "typeTissuId")
    @Mapping(source = "typeTissu.nom", target = "typeTissuNom")
    TissuResponse toResponse(Tissu tissu);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "typeTissu", ignore = true)
    void updateEntityFromRequest(TissuRequest request, @MappingTarget Tissu tissu);
}

