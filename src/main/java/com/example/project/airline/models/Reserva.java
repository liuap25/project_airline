package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "aeropuerto_id")
    private Aeropuerto aeropuerto;

    private int cantidadPersonas;
    private String horarioPreferido;
    private String horaPartida;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avionAsignado;

    @Column(name = "precio_total")
    private BigDecimal precioTotal;

    public void calcularPrecioTotal() {
        this.precioTotal = viaje.calcularPrecio(cantidadPersonas);
    }
}
