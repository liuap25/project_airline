package com.example.project.airline.services;

import com.example.project.airline.models.Cliente;
import com.example.project.airline.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);  // Guarda el cliente en la base de datos
    }
}