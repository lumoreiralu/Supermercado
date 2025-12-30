package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

import exception.NotFoundException;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> getProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO createProducto(ProductoDTO producto) {
        Producto prod = Producto.builder()
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();
        return Mapper.toDTO(repo.save(prod));

    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoDTO producto) {
        //buscamos que exista el producto
        Producto prod = repo.findById(id)
        .orElseThrow(()->new NotFoundException("Producto no encontrado"));

        prod.setNombre(producto.getNombre());
        prod.setCantidad(producto.getCantidad());
        prod.setCategoria(producto.getCategoria());
        prod.setPrecio(producto.getPrecio());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void deleteProducto(Long id) {
        //buscamos que exista el producto
        if(!repo.existsById(id))
        throw new NotFoundException("Producto no encontrado");

        repo.deleteById(id);
    }

}
