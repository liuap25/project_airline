package com.example.project.airline.controllers;

import com.example.project.airline.DTO.ReservaRequest;
import com.example.project.airline.DTO.ReservaResponseDTO;
import com.example.project.airline.services.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarReserva(@RequestBody ReservaRequest reservaRequest,
                                              @RequestHeader("Authorization") String token) {
        try {
            // Eliminar "Bearer " si está presente
            if (token.startsWith("Bearer ")) {
                token = token.substring(7); // Remueve "Bearer " y deja solo el token
            }

            ReservaResponseDTO reservaResponse = reservaService.registrarReserva(reservaRequest, token);
            return ResponseEntity.ok(reservaResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReservaResponseDTO>> obtenerTodasLasReservas() {
        List<ReservaResponseDTO> reservas = reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }
}


