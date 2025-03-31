package com.example.project.airline.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
    // La clave debe estar codificada en Base64
    private static final String SECRET_KEY = "dHVDbGF2ZVNlY3JldGFEZUFsTWVub3MzMkNhcmFjdGVyZXNTZWd1cm9zISEh";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generar un token JWT
    public String generarToken(Long clienteId) {
        return Jwts.builder()
                .subject(String.valueOf(clienteId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }
    
    // Obtener el ID del cliente desde el token JWT
    public Long getClienteIdFromToken(String token) {
        String subject = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return Long.parseLong(subject);
    }

    // 📌 Nuevo método para extraer el token del header Authorization
    public String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Quitar "Bearer "
        }
        return null;
    }
}
