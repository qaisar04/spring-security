package kz.baltabayev.springsecurityexample.mapper;

import kz.baltabayev.springsecurityexample.model.dto.UserRequest;
import kz.baltabayev.springsecurityexample.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toModel(UserRequest userRequest);

}
