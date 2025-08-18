package com.ApiRestTasks.ApiRestTasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
SecurityConfig class is where you:
- Configure authentication (who are you? → username/password, JWT, OAuth, etc.)
- Configure authorization (what can you access? → roles, permissions).
-Disable/enable Spring Security’s defaults (like the login form, CSRF, etc.).
- Register beans like PasswordEncoder.
*/
@Configuration
@EnableWebSecurity //Enables Spring Security’s web security support and allows you to customize it.
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configures CSRF protection.
                .csrf(csrf-> csrf.disable()) //Disables CSRF protection — this is common in stateless APIs (like those using JWT), where CSRF is not needed.
                //Defines access rules for HTTP requests.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() //  Allows unauthenticated access to any endpoint starting with /auth/ (e.g., login, register).
                        .anyRequest().authenticated() //Requires authentication for all other endpoints.
                )
                //Configures how sessions are handled.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //Tells Spring Security not to create or use HTTP sessions — perfect for JWT-based authentication.
        return http.build(); //Builds and returns the configured SecurityFilterChain.

    }

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
