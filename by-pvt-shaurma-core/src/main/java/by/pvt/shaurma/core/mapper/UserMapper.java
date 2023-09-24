package by.pvt.shaurma.core.mapper;

import by.pvt.shaurma.api.dto.UserRequest;
import by.pvt.shaurma.api.dto.UserResponse;
import by.pvt.shaurma.core.entity.User;

public class UserMapper {
    public UserResponse mapToUserDto(User user){
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole());
        return dto;
    }

    public User mapToUserEntity(UserRequest dto){
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }
}
