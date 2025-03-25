package com.example.project.airline.services;

import com.example.project.airline.models.Ticket;
import com.example.project.airline.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket consultarTicket(String codigoTicket) {
        Optional<Ticket> ticket = ticketRepository.findByCodigoTicket(codigoTicket);
        return ticket.orElse(null); // Devuelve el ticket si existe, o null si no se encuentra
    }
}