package com.example.project.airline.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudadTuristica;
}
