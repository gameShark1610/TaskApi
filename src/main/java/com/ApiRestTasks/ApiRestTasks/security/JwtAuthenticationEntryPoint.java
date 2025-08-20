package com.ApiRestTasks.ApiRestTasks.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/*
Handles unauthenticated requests.
If a client sends a request without a valid JWT, Spring Security calls this class. It responds with HTTP 401 Unauthorized, optionally with a message.
*/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //Called automatically whenever an unauthenticated user tries to access a secured endpoint.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Respond with 401 Unauthorized and a message
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: " + authException.getMessage());
    }
}
