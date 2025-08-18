package com.ApiRestTasks.ApiRestTasks.service;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.model.User;
import com.ApiRestTasks.ApiRestTasks.repository.UserRepository;
import com.ApiRestTasks.ApiRestTasks.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void testCreateUser() {
        // Arrange
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setUsername("testuser");
        requestDTO.setPassword("plainpassword");

        User userEntity = new User();
        userEntity.setUsername("testuser");
        userEntity.setPassword("encodedpassword");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setPassword("encodedpassword");

        when(passwordEncoder.encode("plainpassword")).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        UserResponseDTO responseDTO = userService.createUser(requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals("testuser", responseDTO.getUsername());

        verify(passwordEncoder).encode("plainpassword");
        verify(userRepository).save(any(User.class));
    }


}
