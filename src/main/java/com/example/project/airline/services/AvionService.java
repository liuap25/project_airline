package com.example.project.airline.services;

import com.example.project.airline.models.Avion;
import com.example.project.airline.models.Viaje;
import com.example.project.airline.repositories.AvionRepository;
import com.example.project.airline.repositories.ViajeRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AvionService {

    private final AvionRepository avionRepository;
    private final ViajeRepository viajeRepository;


    public AvionService(AvionRepository avionRepository, ViajeRepository viajeRepository) {
        this.avionRepository = avionRepository;
        this.viajeRepository = viajeRepository;
    }

    // Método para registrar un avión
    public Avion registrarAvion(Avion avion) {
        return avionRepository.save(avion);
    }

    // Método para asignar un viaje a un avión
    public Avion asignarViaje(Long avionId, Long viajeId) {
        Avion avion = avionRepository.findById(avionId)
                .orElseThrow(() -> new IllegalArgumentException("Avión no encontrado"));

        Viaje viaje = viajeRepository.findById(viajeId)
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

        avion.setViajeAsignado(viaje);
        return avionRepository.save(avion);
    }
    
    public List<Avion> obtenerTodosLosAviones() {
        return avionRepository.findAll();
    }
}

