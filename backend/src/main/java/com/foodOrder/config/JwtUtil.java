package com.foodOrder.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.foodOrder.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Generate a proper 256-bit key
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mysupersecretkeythatislongenough1234567890".getBytes());

    private final long EXPIRATION = 1000 * 60 * 60 * 24; // 1 hour

    // Generate token from User entity
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                        .signWith(SECRET_KEY)
                        .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validate token using UserDetails
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Check if token expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
