package com.example.project.airline.controllers;

import com.example.project.airline.models.Viaje;
import com.example.project.airline.services.ViajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Viaje>> consultarViajesDisponibles() {
        List<Viaje> viajes = viajeService.consultarViajesDisponibles();
        return ResponseEntity.ok(viajes);
    }
}


