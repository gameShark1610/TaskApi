package com.ApiRestTasks.ApiRestTasks.security;

import com.ApiRestTasks.ApiRestTasks.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
Intercepts every HTTP request before it reaches the controller and looks for a JWT in the Authorization header.
Validates the token using JwtTokenProvider. If valid, loads the user and sets authentication in Spring Security.
Without this filter, Spring Security wouldn’t know which requests are authenticated.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt =  getJwtFromRequest(request); //Reads the Authorization header.
        if (jwt != null && tokenProvider.validateToken(jwt)) {
            String username = tokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //Wraps the user and authorities into Spring Security’s authentication object.
            UsernamePasswordAuthenticationToken authentication  = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //Tells Spring Security that this request is authenticated.
            //After this, controllers can use @AuthenticationPrincipal or SecurityContext safely.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); //Passes the request along the chain so the controller can handle it.

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization"); //HTTP requests can include headers (key-value pairs). If the client didn’t send the header, bearer will be null.
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7); // "Bearer " is 7 characters long. || substring(7) removes that prefix, leaving only the actual JWT string.
        }
        //Example: Example:
        //Authorization: Bearer abc.def.ghi
        //→ returns "abc.def.ghi"
        return null;
    }
}
