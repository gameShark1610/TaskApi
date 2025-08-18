package com.ApiRestTasks.ApiRestTasks.mapper;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.model.User;


public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
