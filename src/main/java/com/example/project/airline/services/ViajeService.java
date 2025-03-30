package com.example.project.airline.services;

import com.example.project.airline.models.Viaje;
import com.example.project.airline.repositories.ViajeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViajeService {

    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    public List<Viaje> consultarViajesDisponibles() {
        return viajeRepository.findAll();
    }

    // 📌 Método para guardar un nuevo viaje
    public Viaje crearViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }
}