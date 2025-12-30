package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);
}
