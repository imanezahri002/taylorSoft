package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.taylorsoft.taylorsoft.dtos.request.RegisterRequest;
import org.taylorsoft.taylorsoft.dtos.response.AuthResponse;
import org.taylorsoft.taylorsoft.entity.User;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "statut", constant = "ACTIF")
    @Mapping(target = "profilePicture", ignore = true)
    User registerRequestToUser(RegisterRequest request);

    @Mapping(source = "id", target = "userId")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "type", constant = "Bearer")
    AuthResponse userToAuthResponse(User user);
}

