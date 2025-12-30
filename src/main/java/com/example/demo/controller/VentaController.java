package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VentaDTO;
import com.example.demo.service.IVentaService;

@RestController
@RequestMapping("/api/ventas")

public class VentaController {
    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas(){
        return ResponseEntity.ok(ventaService.getVenta());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO dto){
        VentaDTO creada = ventaService.createVenta(dto);
        return ResponseEntity.created(URI.create("/api/ventas/"+creada.getId()))
            .body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> update(@PathVariable Long id, @RequestBody VentaDTO dto){
        return ResponseEntity.ok(ventaService.updateVenta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
