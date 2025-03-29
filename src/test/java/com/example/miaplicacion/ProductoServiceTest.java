package com.example.miaplicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.miaplicacion.service.ProductoService;
import com.example.miaplicacion.repository.ProductoRepository;
import com.example.miaplicacion.model.Producto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void listarProductos() {
        Producto producto = new Producto("1", "Producto1");

        // ✅ Cambiado para devolver un Flux<Producto>
        when(productoRepository.findAll()).thenReturn(Flux.just(producto));

        // ✅ Se usa block() para obtener los datos de Flux en la prueba
        assertEquals(1, productoService.listarProductos().collectList().block().size());
    }

    @Test
    void obtenerProductoPorId() {
        Producto producto = new Producto("1", "Producto1");

        // ✅ Cambiado para devolver un Mono<Producto>
        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        // ✅ Se usa block() para esperar el valor en la prueba
        assertEquals(producto, productoService.obtenerProductoPorId("1").block());
    }

    @Test
    void crearProducto() {
        Producto producto = new Producto("1", "Producto1");

        // ✅ Cambiado para devolver un Mono<Producto>
        when(productoRepository.save(producto)).thenReturn(Mono.just(producto));

        // ✅ Se usa block() para obtener el resultado
        assertEquals(producto, productoService.crearProducto(producto).block());

        verify(productoRepository, times(1)).save(producto);
    }
}
