package com.example.project.airline.controllers;

import com.example.project.airline.models.Ticket;
import com.example.project.airline.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/consultar/{codigoTicket}")
    public ResponseEntity<Ticket> consultarTicket(@PathVariable String codigoTicket) {
        Ticket ticket = ticketService.consultarTicket(codigoTicket);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}