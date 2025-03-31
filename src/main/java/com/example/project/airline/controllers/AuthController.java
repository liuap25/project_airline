package com.example.project.airline.controllers;

import com.example.project.airline.security.TokenBlacklist;
import com.example.project.airline.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenBlacklist tokenBlacklist;
    private final JwtUtil jwtUtil;

    public AuthController(TokenBlacklist tokenBlacklist, JwtUtil jwtUtil) {
        this.tokenBlacklist = tokenBlacklist;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);

        if (token == null || tokenBlacklist.isTokenBlacklisted(token)) {
            return ResponseEntity.badRequest().body("{\"error\": \"Token inválido o ya revocado.\"}");
        }

        tokenBlacklist.addToken(token);
        return ResponseEntity.ok("{\"message\": \"Sesión cerrada correctamente.\"}");
    }
}
