package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductoDTO;

public interface IProductoService {
    List<ProductoDTO> getProductos();
    ProductoDTO createProducto(ProductoDTO producto);
    ProductoDTO updateProducto(Long id, ProductoDTO producto);
    void deleteProducto(Long id);
}
