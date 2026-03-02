package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.ModelRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelResponse;
import org.taylorsoft.taylorsoft.entity.Model;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TissuMapper.class, ModelColorMapper.class})
public interface ModelMapper {

    @Mapping(source = "tissu", target = "tissu")
    @Mapping(source = "modelColors", target = "couleurs")
    ModelResponse toResponse(Model model);

    List<ModelResponse> toResponseList(List<Model> models);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tissu", ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "coutourier", ignore = true)
    @Mapping(target = "modelColors", ignore = true)
    @Mapping(target = "visibility", ignore = true)
    Model toEntity(ModelRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tissu", ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "coutourier", ignore = true)
    @Mapping(target = "modelColors", ignore = true)
    @Mapping(target = "visibility", ignore = true)
    void updateEntityFromRequest(ModelRequest request, @MappingTarget Model model);
}

