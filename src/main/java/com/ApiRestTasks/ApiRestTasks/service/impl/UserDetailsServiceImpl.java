package com.ApiRestTasks.ApiRestTasks.service.impl;

import com.ApiRestTasks.ApiRestTasks.model.User;
import com.ApiRestTasks.ApiRestTasks.repository.UserRepository;
import com.ApiRestTasks.ApiRestTasks.security.UserPrincipal;
import com.ApiRestTasks.ApiRestTasks.service.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
UserDetailsServiceImpl is the service responsible for loading user data from the database and adapting it for Spring Security.
- Converts a User entity into a UserPrincipal (UserDetails) for authentication.
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        // Convert our User entity into a UserPrincipal
        return new UserPrincipal(user);
    }
}
