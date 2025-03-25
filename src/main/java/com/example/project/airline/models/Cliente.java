package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    public void actualizarDatos(String nuevoEmail, String nuevoTelefono) {
        this.email = nuevoEmail;
        this.telefono = nuevoTelefono;
    }
}
