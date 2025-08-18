package com.ApiRestTasks.ApiRestTasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
SecurityConfig class is where you:
- Configure authentication (who are you? → username/password, JWT, OAuth, etc.)
- Configure authorization (what can you access? → roles, permissions).
-Disable/enable Spring Security’s defaults (like the login form, CSRF, etc.).
- Register beans like PasswordEncoder.
*/
@Configuration
public class SecurityConfig {

    /*
    When you create a new encoder: new BCryptPasswordEncoder();
    It internally uses: new BCryptPasswordEncoder(-1);
    "-1" means: use the default strength.
    The strength is the "log rounds" (cost factor) for the hashing algorithm and by default, Spring uses 10 (which is a good balance between security and performance).
    More rounds = stronger hash, but also slower.
    - 4 = very weak, superfast.
    - 10 = recommended (default).
    - 12 or more = stronger, but can be slower for login if you have many users.
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(-1);
    }

}
