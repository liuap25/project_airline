package com.example.project.airline.DTO;
import lombok.*;

@Getter
@Setter
public class ReservaRequest {
    private Long viajeId;
    private Long avionId;
    private int cantidadPersonas;
}