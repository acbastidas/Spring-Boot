package com.example.miaplicacion.service;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Cambiar el tipo de retorno a Flux<Producto> en lugar de List<Producto>
    public Flux<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Cambiar el tipo de retorno a Mono<Producto> y usar Mono para orElse
    public Mono<Producto> obtenerProductoPorId(String id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.just(new Producto("idPorDefecto", "Producto por defecto"))); // Retorna un valor por
    }

    // Cambiar el tipo de retorno a Mono<Producto>
    public Mono<Producto> crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(String id) {
        productoRepository.deleteById(id).subscribe();
    }
}
