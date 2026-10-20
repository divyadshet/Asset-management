package com.example.assetmanagement.controller;

import com.example.assetmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.example.assetmanagement.repository.UserRepository;
import com.example.assetmanagement.security.JwtProvider;
import com.example.assetmanagement.entity.User;
import com.example.assetmanagement.entity.Role;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody Map<String, String> request) {
        try {
            String idToken = request.get("idToken");
            String jwtToken = authService.authenticateGoogleUser(idToken);
            return ResponseEntity.ok(Map.of("token", jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/test-token/{email}")
    public ResponseEntity<?> testToken(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName("Test Admin");
            newUser.setRole(Role.ROLE_ADMIN); // Grants admin privileges for testing CRUD
            return userRepository.save(userRepository.save(newUser));
        });

        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
