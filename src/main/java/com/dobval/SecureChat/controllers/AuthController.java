package com.dobval.SecureChat.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dobval.SecureChat.model.AuthRequestDTO;
import com.dobval.SecureChat.model.User;
import com.dobval.SecureChat.repositories.UserRepository;
import com.dobval.SecureChat.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("User registered");
    }

    //TODO: Test GET req with cURL and authorization bearer token! Maybe new endpoint?
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO req) {
    	System.out.println("Login attempt for user: " + req.getUsername()); //logging
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtProvider.generateToken(auth);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

