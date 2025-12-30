package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.VentaDTO;

public interface IVentaService {
    List<VentaDTO> getVenta();
    VentaDTO createVenta(VentaDTO venta);
    VentaDTO updateVenta(Long id, VentaDTO venta);
    void deleteVenta(Long id);
}
