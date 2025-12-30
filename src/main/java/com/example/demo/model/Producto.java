package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(para que vaya uno a uno -> secuencial)
    private Long id; 
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;


}
