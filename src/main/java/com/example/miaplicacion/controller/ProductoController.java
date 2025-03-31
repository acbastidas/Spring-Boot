package com.example.miaplicacion.controller;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable String id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
    }
}