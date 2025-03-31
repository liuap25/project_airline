package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "aviones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private int capacidad;
    private String horarioDesignado;  // "Mañana" o "Tarde"

    @ElementCollection
    private List<String> horasSalida;

    @ManyToOne
    @JoinColumn(name = "viaje_id")
    @JsonBackReference
    private Viaje viajeAsignado;

    public boolean consultarDisponibilidad() {
        return viajeAsignado == null;
    }
}

