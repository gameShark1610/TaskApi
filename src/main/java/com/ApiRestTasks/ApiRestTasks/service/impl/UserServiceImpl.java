package com.ApiRestTasks.ApiRestTasks.service.impl;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.mapper.TaskMapper;
import com.ApiRestTasks.ApiRestTasks.mapper.UserMapper;
import com.ApiRestTasks.ApiRestTasks.model.User;
import com.ApiRestTasks.ApiRestTasks.repository.UserRepository;
import com.ApiRestTasks.ApiRestTasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /*
    Using a single constructor for dependency injection is the best practice in Spring.
    Spring automatically injects the beans into this constructor without needing @Autowired.
    Benefits:
    1. Makes dependencies explicit.
    2. Allows fields to be final, improving immutability.
    3. Easier to test (can pass mock dependencies).
    4. Cleaner and less error-prone than field or setter injection.
     */
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }
}
