package kz.baltabayev.springsecurityexample.mapper;

import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.model.entity.User;
import kz.baltabayev.springsecurityexample.model.type.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        imports = Role.class,
        componentModel = "spring"
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role" , expression = "java(Role.USER)")
    User toModel(UserRequest userRequest);

}
