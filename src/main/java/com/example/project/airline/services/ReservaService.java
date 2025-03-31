package com.example.project.airline.services;

import com.example.project.airline.models.Reserva;
import com.example.project.airline.models.Cliente;
import com.example.project.airline.models.Viaje;
import com.example.project.airline.models.Avion;
import com.example.project.airline.repositories.ReservaRepository;
import com.example.project.airline.repositories.ClienteRepository;
import com.example.project.airline.repositories.ViajeRepository;
import com.example.project.airline.repositories.AvionRepository;
import com.example.project.airline.security.JwtUtil;
import com.example.project.airline.DTO.ReservaRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final ViajeRepository viajeRepository;
    private final AvionRepository avionRepository;
    private final JwtUtil jwtUtil; // Clase para manejar JWT

    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository,
                          ViajeRepository viajeRepository, AvionRepository avionRepository,
                          JwtUtil jwtUtil) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.viajeRepository = viajeRepository;
        this.avionRepository = avionRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public Reserva registrarReserva(ReservaRequest request, String token) {
        // Obtener ID del cliente desde el token JWT
        Long clienteId = jwtUtil.getClienteIdFromToken(token);
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Obtener viaje y avión
        Viaje viaje = viajeRepository.findById(request.getViajeId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        Avion avion = avionRepository.findById(request.getAvionId())
                .orElseThrow(() -> new RuntimeException("Avión no encontrado"));

        // Calcular precio total
        BigDecimal precioTotal = viaje.getPrecioBase().multiply(BigDecimal.valueOf(request.getCantidadPersonas()));

        // Generar código de reserva aleatorio
        String codigoReserva = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Crear la reserva
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setViaje(viaje);
        reserva.setAvion(avion);
        reserva.setCantidadPersonas(request.getCantidadPersonas());
        reserva.setPrecioTotal(precioTotal);
        reserva.setCodigoReserva(codigoReserva);

        return reservaRepository.save(reserva);
    }
}
