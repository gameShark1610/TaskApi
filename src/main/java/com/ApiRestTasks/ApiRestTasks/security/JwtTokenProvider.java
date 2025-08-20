package com.ApiRestTasks.ApiRestTasks.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
/*
Central class for all JWT operations in your app.
Responsibilities:
- Generate tokens when a user logs in.
- Validate tokens for authenticity and expiration.
- Extract username or user ID from a token.
*/
@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private long jwtExpirationMs;


    public String generateToken(UserPrincipal userPrincipal) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) //the identifier for the token (usually email or user ID).
                .setIssuedAt(now) //token creation timestamp.
                .setExpiration(expiry) //token expiry timestamp.
                .signWith(SignatureAlgorithm.HS256, jwtSecret) //cryptographically signs the token using your secret and algorithm.
                .compact(); //generates the final string token.
    }
    //Parses the JWT string.
    //Validates the signature using jwtSecret.
    //Extracts the username (or whatever you put in setSubject()).
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    /*
    Verifies the token is valid:
        - Signature matches the secret.
        - Token is not expired.
        - Token is well-formed.
        - Returns false if invalid or malformed.
    */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // expired, malformed, or bad signature
        }
    }
}
