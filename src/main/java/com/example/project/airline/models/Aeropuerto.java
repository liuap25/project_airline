package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "aeropuertos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aeropuerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String ubicacion;
    
    @Column(name = "horario_apertura")
    private LocalTime horarioApertura;

    @Column(name = "horario_cierre")
    private LocalTime horarioCierre;

    @OneToMany(mappedBy = "aeropuerto")
    private List<Reserva> reservas;

    public String consultarHorarios() {
        return "Apertura: " + horarioApertura + ", Cierre: " + horarioCierre;
    }

    public void actualizarHorarios(LocalTime nuevoHorarioApertura, LocalTime nuevoHorarioCierre) {
        this.horarioApertura = nuevoHorarioApertura;
        this.horarioCierre = nuevoHorarioCierre;
    }
}