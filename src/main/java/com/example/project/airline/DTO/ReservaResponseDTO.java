package com.example.project.airline.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ReservaResponseDTO {
    private String nombreCliente;  // Cambiado de id a nombreCliente
    private String categoriaViaje;
    private String ciudadDestino;
    private LocalDate fechaIda;
    private String horarioDesignado;
    private List<String> horasSalida;
    private int cantidadPersonas;
    private BigDecimal precioTotal;
    private String codigoReserva;
}

