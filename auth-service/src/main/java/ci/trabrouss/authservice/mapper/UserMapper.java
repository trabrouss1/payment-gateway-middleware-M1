package ci.trabrouss.authservice.mapper;

import ci.trabrouss.authservice.dto.UserCreateDTO;
import ci.trabrouss.authservice.dto.UserResponseDTO;
import ci.trabrouss.authservice.dto.UserUpdateDTO;
import ci.trabrouss.authservice.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(User users);

    User toEntity(UserCreateDTO entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserUpdateDTO dto, @MappingTarget User user);
}
