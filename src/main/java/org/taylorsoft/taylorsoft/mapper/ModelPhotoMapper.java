package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.ModelPhotoRequest;
import org.taylorsoft.taylorsoft.dtos.response.ModelPhotoResponse;
import org.taylorsoft.taylorsoft.entity.ModelPhoto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelPhotoMapper {

    ModelPhotoResponse toResponse(ModelPhoto modelPhoto);

    List<ModelPhotoResponse> toResponseList(List<ModelPhoto> modelPhotos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modelColor", ignore = true)
    @Mapping(target = "order", ignore = true)
    ModelPhoto toEntity(ModelPhotoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modelColor", ignore = true)
    @Mapping(target = "order", ignore = true)
    void updateEntityFromRequest(ModelPhotoRequest request, @MappingTarget ModelPhoto modelPhoto);
}

