package com.example.project.airline.security;

import com.example.project.airline.models.Cliente;
import com.example.project.airline.repositories.ClienteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ClienteRepository clienteRepository;
    private final TokenBlacklist tokenBlacklist;  // Inyectamos la lista negra

    public JwtAuthenticationFilter(JwtUtil jwtUtil, ClienteRepository clienteRepository, TokenBlacklist tokenBlacklist) {
        this.jwtUtil = jwtUtil;
        this.clienteRepository = clienteRepository;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Obtener el token del header Authorization
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // Quitar "Bearer "

            // Verificar si el token ha sido revocado
            if (tokenBlacklist.isTokenBlacklisted(token)) { 
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write("Token revocado, por favor inicie sesión nuevamente.");
                return;
            }

            try {
                Long clienteId = jwtUtil.getClienteIdFromToken(token);
                Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);

                if (clienteOpt.isPresent()) {
                    Cliente cliente = clienteOpt.get();
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(cliente, null, cliente.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                // Token inválido o expirado
                logger.error("Error al validar token JWT: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
