package com.example.project.airline.security;

import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {

    private final Set<String> revokedTokens = ConcurrentHashMap.newKeySet();

    // Método para revocar un token
    public void addToken(String token) {
        revokedTokens.add(token);
    }

    // Método para verificar si un token ha sido revocado
    public boolean isTokenBlacklisted(String token) {
        return revokedTokens.contains(token);
    }
}

