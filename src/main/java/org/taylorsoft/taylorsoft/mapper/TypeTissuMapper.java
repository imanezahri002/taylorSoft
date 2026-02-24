package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.TypeTissuRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeTissuResponse;
import org.taylorsoft.taylorsoft.entity.TypeTissu;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeTissuMapper {

    TypeTissuResponse toResponse(TypeTissu typeTissu);

    List<TypeTissuResponse> toResponseList(List<TypeTissu> typeTissus);

    @Mapping(target = "id", ignore = true)
    TypeTissu toEntity(TypeTissuRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(TypeTissuRequest request, @MappingTarget TypeTissu typeTissu);
}

