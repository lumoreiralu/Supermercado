package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //lo usa en el mapper

public class VentaDTO {
    private Long id; 
    private LocalDate fecha;
    private String estado;
    private Double total;
    private List<DetalleVentaDTO> detalle;
    private Long  idSucursal;

}
