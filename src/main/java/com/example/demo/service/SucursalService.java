package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SucursalDTO;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Sucursal;
import com.example.demo.repository.SucursalRepository;

import exception.NotFoundException;

@Service
public class SucursalService implements ISucursalService{

    @Autowired //inyeccion de dependencias
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> getSucursales() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();

    }

    @Override
    public SucursalDTO createSucursal(SucursalDTO sucursal) {
        Sucursal suc = Sucursal.builder()
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO updateSucursal(Long id, SucursalDTO sucursal) {
        Sucursal suc = repo.findById(id)
                .orElseThrow(()->new NotFoundException("Sucursal no encontrada"));
        suc.setNombre(sucursal.getNombre());
        suc.setDireccion(sucursal.getDireccion());

        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void deleteSucursal(Long id) {
        if(!repo.existsById(id))
            throw new NotFoundException("Sucursal no encontrada");

        repo.deleteById(id);
    }

}
