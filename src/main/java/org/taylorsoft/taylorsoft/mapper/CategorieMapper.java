package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.CategorieRequest;
import org.taylorsoft.taylorsoft.dtos.response.CategorieResponse;
import org.taylorsoft.taylorsoft.entity.Categorie;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    CategorieResponse toResponse(Categorie categorie);

    List<CategorieResponse> toResponseList(List<Categorie> categories);

    @Mapping(target = "id", ignore = true)
    Categorie toEntity(CategorieRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(CategorieRequest request, @MappingTarget Categorie categorie);
}

