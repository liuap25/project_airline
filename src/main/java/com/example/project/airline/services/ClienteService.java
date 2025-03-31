package com.example.project.airline.services;

import com.example.project.airline.models.Cliente;
import com.example.project.airline.repositories.ClienteRepository;
import com.example.project.airline.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 🔹 Registrar cliente
    public Cliente registrarCliente(Cliente cliente) {
        cliente.setPassword(passwordEncoder.encode(cliente.getPassword())); // Encripta la contraseña
        return clienteRepository.save(cliente);
    }

    // 🔹 Autenticar cliente y generar token JWT
    public String autenticarCliente(String email, String password) {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                return jwtUtil.generarToken(cliente.getId()); // Genera un token con el ID del cliente
            }
        }
        return null; // Retorna null si la autenticación falla
    }
}
