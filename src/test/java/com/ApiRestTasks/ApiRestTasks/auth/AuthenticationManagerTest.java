package com.ApiRestTasks.ApiRestTasks.auth;

import com.ApiRestTasks.ApiRestTasks.model.User;
import com.ApiRestTasks.ApiRestTasks.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AuthenticationManagerTest {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Test
    void authShouldNotStackOverflow() {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("testuser", "pw")
        );
        // If this line is reached, no StackOverflowError happened
        System.out.println(auth.isAuthenticated()); // should print true
    }
}
