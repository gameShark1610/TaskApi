package com.ApiRestTasks.ApiRestTasks.controller;

import com.ApiRestTasks.ApiRestTasks.dto.request.UserRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.UserResponseDTO;
import com.ApiRestTasks.ApiRestTasks.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username)); //200 OK
    }
}
