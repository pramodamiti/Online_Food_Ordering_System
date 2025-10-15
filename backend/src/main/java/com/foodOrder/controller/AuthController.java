package com.foodOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.config.JwtUtil;
import com.foodOrder.dto.LoginDto;
import com.foodOrder.entity.User;
import com.foodOrder.service.impl.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {

        System.err.println("user = " + user);
        return authService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.toString());
        return authService.login(loginDto);
    }
}
