package com.ApiRestTasks.ApiRestTasks.controller;

import com.ApiRestTasks.ApiRestTasks.dto.request.LoginRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.security.JwtTokenProvider;
import com.ApiRestTasks.ApiRestTasks.security.UserPrincipal;
import com.ApiRestTasks.ApiRestTasks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userRequestDTO)); //201 created
    }

    @PostMapping("/login")
    public ResponseEntity<?>  login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // If authentication succeeds, generate JWT
        String token = tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
