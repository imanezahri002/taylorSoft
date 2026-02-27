package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.TissuColorRequest;
import org.taylorsoft.taylorsoft.dtos.response.TissuColorResponse;
import org.taylorsoft.taylorsoft.entity.TissuColor;

@Mapper(componentModel = "spring")
public interface TissuColorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "couleur", ignore = true)
    @Mapping(target = "tissu", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    TissuColor toEntity(TissuColorRequest request);

    @Mapping(source = "couleur.id", target = "couleurId")
    @Mapping(source = "couleur.nom", target = "couleurNom")
    @Mapping(source = "couleur.codeHex", target = "couleurCodeHex")
    @Mapping(source = "tissu.id", target = "tissuId")
    @Mapping(source = "tissu.reference", target = "tissuReference")
    @Mapping(source = "tissu.nom", target = "tissuNom")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "fournisseur.nom", target = "fournisseurNom")
    @Mapping(source = "fournisseur.email", target = "fournisseurEmail")
    TissuColorResponse toResponse(TissuColor tissuColor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "couleur", ignore = true)
    @Mapping(target = "tissu", ignore = true)
    @Mapping(target = "fournisseur", ignore = true)
    void updateEntityFromRequest(TissuColorRequest request, @MappingTarget TissuColor tissuColor);
}


