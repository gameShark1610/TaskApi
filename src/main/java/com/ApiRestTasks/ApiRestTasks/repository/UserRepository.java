package com.ApiRestTasks.ApiRestTasks.repository;

import com.ApiRestTasks.ApiRestTasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // User findByUsername(String username): Returns a plain User, so .orElseThrow() doesn’t exist.
    // Using Optional<User> is best practice because it avoids nulls and allows orElseThrow().
    Optional<User>  findByUsername(String username);
}
