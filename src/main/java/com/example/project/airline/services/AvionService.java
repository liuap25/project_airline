package com.example.project.airline.services;

import com.example.project.airline.models.Avion;
import com.example.project.airline.repositories.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvionService {
    @Autowired
    private AvionRepository avionRepository;

    public List<Avion> obtenerTodos() {
        return avionRepository.findAll();
    }

    public Optional<Avion> obtenerPorId(Long id) {
        return avionRepository.findById(id);
    }

    public Avion guardar(Avion avion) {
        return avionRepository.save(avion);
    }

    public boolean consultarDisponibilidad(Long id) {
        Avion avion = avionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Avión no encontrado"));
        return avion.getViajeAsignado() == null;
    }
}
