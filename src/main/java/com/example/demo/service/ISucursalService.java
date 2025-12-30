package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SucursalDTO;

public interface ISucursalService {
    List<SucursalDTO> getSucursales();
    SucursalDTO createSucursal(SucursalDTO sucursal);
    SucursalDTO updateSucursal(Long id, SucursalDTO sucursal);
    void deleteSucursal(Long id);
}
