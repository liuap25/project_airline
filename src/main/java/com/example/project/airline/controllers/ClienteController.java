package com.example.project.airline.controllers;

import com.example.project.airline.models.Cliente;
import com.example.project.airline.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.registrarCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }
}