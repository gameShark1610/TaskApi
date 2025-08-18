package com.ApiRestTasks.ApiRestTasks.service.impl;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.mapper.TaskMapper;
import com.ApiRestTasks.ApiRestTasks.mapper.UserMapper;
import com.ApiRestTasks.ApiRestTasks.model.User;
import com.ApiRestTasks.ApiRestTasks.repository.UserRepository;
import com.ApiRestTasks.ApiRestTasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user= userRepository.findByUsername(username);
        UserResponseDTO userResponseDTO= UserMapper.toResponseDTO(user);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }
}
