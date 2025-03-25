package com.example.project.airline.services;

import com.example.project.airline.models.Aeropuerto;
import com.example.project.airline.repositories.AeropuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AeropuertoService {
    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    public List<Aeropuerto> obtenerTodos() {
        return aeropuertoRepository.findAll();
    }

    public Optional<Aeropuerto> obtenerPorId(Long id) {
        return aeropuertoRepository.findById(id);
    }

    public Aeropuerto guardar(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }

    public void actualizarHorarios(Long id, LocalTime apertura, LocalTime cierre) {
        Aeropuerto aeropuerto = aeropuertoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));
        aeropuerto.actualizarHorarios(apertura, cierre);
        aeropuertoRepository.save(aeropuerto);
    }
}