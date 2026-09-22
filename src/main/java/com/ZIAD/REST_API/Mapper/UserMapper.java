package com.ZIAD.REST_API.Mapper;

import com.ZIAD.REST_API.DTO.UserDto;
import com.ZIAD.REST_API.Entity.User;
import org.springframework.stereotype.Component;

//To Make Spring IOC Container Create A Spring Bean For This Class
@Component
public class UserMapper {
    public UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
    public User toEntity(UserDto  userDto){
        return new User(
                userDto.id(),
                userDto.firstName(),
                userDto.lastName(),
                userDto.email()
        );
    }
}
