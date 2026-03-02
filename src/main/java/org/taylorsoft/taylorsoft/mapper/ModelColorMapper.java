package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.ModelColorRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelColorResponse;
import org.taylorsoft.taylorsoft.entity.ModelColor;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ModelPhotoMapper.class, CouleurMapper.class})
public interface ModelColorMapper {

    @Mapping(source = "couleur", target = "couleur")
    @Mapping(source = "photos", target = "photos")
    ModelColorResponse toResponse(ModelColor modelColor);

    List<ModelColorResponse> toResponseList(List<ModelColor> modelColors);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "couleur", ignore = true)
    @Mapping(target = "photos", ignore = true)
    ModelColor toEntity(ModelColorRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "couleur", ignore = true)
    @Mapping(target = "photos", ignore = true)
    void updateEntityFromRequest(ModelColorRequest request, @MappingTarget ModelColor modelColor);
}

