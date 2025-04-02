package com.example.miaplicacion.controller;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Flux<Producto> getAllProductos() {
        // Devuelve un Flux en lugar de List
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> getProductoById(@PathVariable String id) {
        // Devuelve un Mono en lugar de Producto
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> createProducto(@RequestBody Producto producto) {
        // Devuelve un Mono que encapsula el producto creado
        return productoService.crearProducto(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
        // El método deleteById sigue siendo correcto, sin cambios
        productoService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
