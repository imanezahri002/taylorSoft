package org.taylorsoft.taylorsoft.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;
import org.taylorsoft.taylorsoft.dtos.request.UserRequest;
import org.taylorsoft.taylorsoft.dtos.response.UserResponse;
import org.taylorsoft.taylorsoft.entity.User;
import org.taylorsoft.taylorsoft.entity.Client;
import org.taylorsoft.taylorsoft.entity.Coutourier;
import org.taylorsoft.taylorsoft.entity.Fournisseur;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "profilePicture", ignore = true)
    User requestToUser(UserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "profilePicture", ignore = true)
    void updateUserFromRequest(UserRequest request, @MappingTarget User user);

    @Mapping(target = "userType", expression = "java(user.getClass().getSimpleName())")
    UserResponse userToResponse(User user);

    default User instantiateByRole(org.taylorsoft.taylorsoft.entity.enums.Role role) {
        return switch (role) {
            case CLIENT -> new Client();
            case COUTOURIER -> new Coutourier();
            case FOURNISSEUR -> new Fournisseur();
            case ADMIN -> new User();
        };
    }
}

