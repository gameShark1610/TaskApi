package com.ApiRestTasks.ApiRestTasks.service;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
