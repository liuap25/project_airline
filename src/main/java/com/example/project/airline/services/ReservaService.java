package com.example.project.airline.services;

import com.example.project.airline.models.Reserva;
import com.example.project.airline.repositories.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public Reserva realizarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);  // Guarda la reserva en la base de datos
    }
}