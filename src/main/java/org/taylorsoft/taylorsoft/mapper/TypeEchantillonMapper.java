package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.taylorsoft.taylorsoft.dtos.request.TypeEchantillonRequest;
import org.taylorsoft.taylorsoft.dtos.response.TypeEchantillonResponse;
import org.taylorsoft.taylorsoft.entity.TypeEchantillon;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeEchantillonMapper {

    TypeEchantillonResponse toResponse(TypeEchantillon typeEchantillon);

    List<TypeEchantillonResponse> toResponseList(List<TypeEchantillon> typeEchantillons);

    @Mapping(target = "id", ignore = true)
    TypeEchantillon toEntity(TypeEchantillonRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(TypeEchantillonRequest request, @MappingTarget TypeEchantillon typeEchantillon);
}

