package ru.telegram.usersdb.mapper;

import org.mapstruct.Mapper;
import ru.telegram.usersdb.model.dto.UsersDTO;
import ru.telegram.usersdb.model.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(UsersDTO usersDTO);
    UsersDTO toDTO(Users users);
}
