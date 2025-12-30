package com.example.demo.mapper;

import java.util.stream.Collectors;

import com.example.demo.dto.DetalleVentaDTO;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.SucursalDTO;
import com.example.demo.dto.VentaDTO;
import com.example.demo.model.Producto;
import com.example.demo.model.Sucursal;
import com.example.demo.model.Venta;

public class Mapper {

    //PARA EVITAR DEVOLVER UNA ENTIDAD ENTERA

    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v){
        if(v == null) return null;

        var detalle = v.getDetalle().stream().map(det ->
            DetalleVentaDTO.builder()
                .id(det.getProducto().getId())
                .nombreProd(det.getProducto().getNombre())
                .cantProd(det.getCantidadProducto())
                .precio(det.getPrecioUnitario())
                .subtotal(det.getPrecioUnitario() * det.getCantidadProducto())
                .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .mapToDouble(DetalleVentaDTO::getSubtotal)
                .sum();

        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .estado(v.getEstado())
                .total(total)
                .idSucursal(v.getSucursal().getId())
                .detalle(detalle)
                .build();

    }

    //Mapeo de Sucursal a SucursalDTO

    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();

    }

}
