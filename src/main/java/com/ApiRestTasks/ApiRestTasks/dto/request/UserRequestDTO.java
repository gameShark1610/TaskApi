package com.ApiRestTasks.ApiRestTasks.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(max = 10, message = "User must be less than 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 20,min =4, message = "User must be greater than 4 characters and less than 20")
    private String password;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @NotBlank(message = "Title is required") @Size(max = 10, message = "User must be less than 100 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Title is required") @Size(max = 10, message = "User must be less than 100 characters") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") @Size(max = 20, min = 4, message = "User must be greater than 4 characters and less than 20") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(max = 20, min = 4, message = "User must be greater than 4 characters and less than 20") String password) {
        this.password = password;
    }
}
