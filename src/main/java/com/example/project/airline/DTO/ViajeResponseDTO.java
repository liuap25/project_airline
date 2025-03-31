package com.example.project.airline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ViajeResponseDTO {
    private String categoria;
    private String ciudadDestino;
    private LocalDate fechaIda;
    private BigDecimal precioBase;
    private String imagen;
}
