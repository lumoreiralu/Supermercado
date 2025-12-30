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

import com.example.demo.dto.SucursalDTO;
import com.example.demo.service.ISucursalService;

@RestController
@RequestMapping("/api/sucursales")

public class SucursalController {
    @Autowired //inyeccion al service que vamos a usar 
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getSucursales(){
        return ResponseEntity.ok(sucursalService.getSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> create(@RequestBody SucursalDTO dto){
        SucursalDTO creada = sucursalService.createSucursal(dto);
        return ResponseEntity.created(URI.create("/api/sucursales/" + creada.getId()))
            .body(creada);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> update(@PathVariable Long id, @RequestBody SucursalDTO dto ){
        return ResponseEntity.ok(sucursalService.updateSucursal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }


}
