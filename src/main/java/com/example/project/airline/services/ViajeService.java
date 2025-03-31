package com.example.project.airline.services;

import com.example.project.airline.DTO.ViajeResponseDTO;
import com.example.project.airline.models.Viaje;
import com.example.project.airline.repositories.ViajeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService {

    private final ViajeRepository viajeRepository;

    public ViajeService(ViajeRepository viajeRepository) {
        this.viajeRepository = viajeRepository;
    }

    // 📌 Ahora devuelve una lista de DTOs en lugar de entidades completas
    public List<ViajeResponseDTO> consultarViajesDisponibles() {
        return viajeRepository.findAll()
                .stream()
                .map(viaje -> new ViajeResponseDTO(
                        viaje.getCategoria(),
                        viaje.getCiudadDestino(),
                        viaje.getFechaIda(),
                        viaje.getPrecioBase(),
                        viaje.getImagen()
                ))
                .collect(Collectors.toList());
    }

    // 📌 Método para guardar un nuevo viaje (sin cambios, sigue usando la entidad)
    public Viaje crearViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }
}
