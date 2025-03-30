package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private String ciudadDestino;
    
    @Column(name = "fecha_ida")
    private LocalDate fechaIda;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    @Column(name = "imagen", length = 500) 
    private String imagen;

    @OneToMany(mappedBy = "viajeAsignado")
    private List<Avion> avionesDisponibles;

    public BigDecimal calcularPrecio(int cantidadPersonas) {
        return precioBase.multiply(BigDecimal.valueOf(cantidadPersonas));
    }
}