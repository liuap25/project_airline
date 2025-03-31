package com.example.project.airline.controllers;

import com.example.project.airline.models.Avion;
import com.example.project.airline.services.AvionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aviones")
public class AvionController {

    private final AvionService avionService;


    public AvionController(AvionService avionService) {
        this.avionService = avionService;
    }

    // Endpoint para registrar un avión
    @PostMapping("/registrar")
    public Avion registrarAvion(@RequestBody Avion avion) {
        return avionService.registrarAvion(avion);
    }

    // Endpoint para asignar un viaje a un avión
    @PostMapping("/{avionId}/asignar-viaje")
    public Avion asignarViaje(@PathVariable Long avionId, @RequestBody Map<String, Long> viajeRequest) {
        Long viajeId = viajeRequest.get("viajeId");
        return avionService.asignarViaje(avionId, viajeId);
    }

    @GetMapping("/mostrar")
    public List<Avion> obtenerTodosLosAviones() {
        return avionService.obtenerTodosLosAviones();
    }
}
