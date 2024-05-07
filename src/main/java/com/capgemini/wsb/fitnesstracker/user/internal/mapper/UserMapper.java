package com.capgemini.wsb.fitnesstracker.user.internal.mapper;

import com.capgemini.wsb.fitnesstracker.user.api.entity.UserEntity;
import com.capgemini.wsb.fitnesstracker.user.internal.dto.BasicUserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    UserEntity toEntity(UserDto dto){
        return new UserEntity(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
    }

    public UserDto toDto(UserEntity user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    public BasicUserDto basicToDto(UserEntity user) {
        return new BasicUserDto(user.getId(),
                               user.getFirstName());
    }
}
