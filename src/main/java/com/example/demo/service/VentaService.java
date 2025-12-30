package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DetalleVentaDTO;
import com.example.demo.dto.VentaDTO;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Producto;
import com.example.demo.model.Sucursal;
import com.example.demo.model.Venta;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.repository.VentaRepository;

import exception.NotFoundException;

@Service
public class VentaService implements IVentaService{

    //inyeccion a cada resositorio que voy a utilizar
    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;
    

    @Override
    public List<VentaDTO> getVenta() {
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<>();

        VentaDTO dto;
        for(Venta v : ventas){
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }

        return ventasDTO;
    }

    @Override
    public VentaDTO createVenta(VentaDTO venta) {
        //Validaciones
        if(venta == null) throw new RuntimeException("La venta no puede ser null");
        if(venta.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if(venta.getDetalle() == null || venta.getDetalle().isEmpty()) 
                throw new RuntimeException("La venta debe incluir al menos un producto"); 
        
        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(venta.getIdSucursal()).orElse(null);
        if(suc == null){
            throw new NotFoundException("Sucursal no encontrada");
        }
        //Crear la venta
        Venta newVenta = new Venta();
        newVenta.setFecha(venta.getFecha());
        newVenta.setEstado(venta.getEstado());
        newVenta.setSucursal(suc);
        newVenta.setTotal(venta.getTotal());

        //lista de detalles(donde estan los productos)
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for(DetalleVentaDTO detDTO : venta.getDetalle()){
            //Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if(p == null)
                throw new RuntimeException("Producto no encontrado" + detDTO.getNombreProd());
            
            //crear el detalle de la venta
            DetalleVenta detalleVent = new DetalleVenta();
                detalleVent.setProducto(p);
                detalleVent.setPrecioUnitario(detDTO.getPrecio());
                detalleVent.setCantidadProducto(detDTO.getCantProd());
                detalleVent.setVenta(newVenta);

                detalles.add(detalleVent);
                totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());
        
        }

        //seteamos la lista de detalle Venta
        newVenta.setDetalle(detalles);

        //guardamos en la db
        newVenta = ventaRepo.save(newVenta);


        //mappeo para mostrar la venta creada
        VentaDTO ventaSalida = Mapper.toDTO(newVenta);

        return ventaSalida;


    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO venta) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("Venta no encontrada");
        
        if(venta.getFecha()!=null){
            v.setFecha(venta.getFecha());
        }
        if(venta.getEstado()!=null){
            v.setEstado(venta.getEstado());
        }
        if(venta.getTotal()!=null){
            v.setTotal(venta.getTotal());
        }
        if(venta.getIdSucursal()!=null){
            Sucursal suc = sucursalRepo.findById(venta.getIdSucursal()).orElse(null);
            if(suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);

        VentaDTO ventaModificada = Mapper.toDTO(v);
        
        return ventaModificada;
    
    
    
    }

    @Override
    public void deleteVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if(v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.deleteById(id);
        
    }

}
