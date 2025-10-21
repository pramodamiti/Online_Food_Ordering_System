package com.foodOrder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodOrder.config.JwtUtil;
import com.foodOrder.dto.LoginDto;
import com.foodOrder.entity.User;
import com.foodOrder.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Signup method
    public User signup(User user) {
        // Check if user exists
        if (userRepository.findByName(user.getName()) != null) {
            throw new RuntimeException("User already exists");
        }
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save user
        return userRepository.save(user);
    }
    // Login method

    public String login(LoginDto loginDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        User user = userRepository.findByEmail(loginDto.getEmail());
        System.out.println("Authenticated user: " + user);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        System.out.println(jwtUtil.generateToken(user));
        return jwtUtil.generateToken(user);
    }
}
