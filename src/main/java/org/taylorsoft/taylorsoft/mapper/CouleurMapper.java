package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.CouleurRequest;
import org.taylorsoft.taylorsoft.dtos.response.CouleurResponse;
import org.taylorsoft.taylorsoft.entity.Couleur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouleurMapper {

    CouleurResponse toResponse(Couleur couleur);

    List<CouleurResponse> toResponseList(List<Couleur> couleurs);

    @Mapping(target = "id", ignore = true)
    Couleur toEntity(CouleurRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(CouleurRequest request, @MappingTarget Couleur couleur);
}

