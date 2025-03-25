package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_ticket", unique = true, nullable = false)
    private String codigoTicket;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "precio_total")
    private BigDecimal precioTotal;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    public enum EstadoTicket {
        ACTIVO,
        CANCELADO
    }

    @PrePersist
    public void generarCodigo() {
        this.codigoTicket = "TICKET-" + System.currentTimeMillis();
        this.fechaEmision = LocalDateTime.now();
        this.precioTotal = reserva.getPrecioTotal();
        this.estado = EstadoTicket.ACTIVO;
    }
}
