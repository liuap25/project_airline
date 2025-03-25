package com.example.project.airline.controllers;

import com.example.project.airline.models.Reserva;
import com.example.project.airline.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Reserva> realizarReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.realizarReserva(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }
}
