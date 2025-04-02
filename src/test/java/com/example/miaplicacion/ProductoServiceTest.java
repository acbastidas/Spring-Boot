package com.example.miaplicacion;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import com.example.miaplicacion.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.*;

import java.util.Arrays;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto("1", "Producto de prueba");
    }

    @Test
    void listarProductos() {
        // Creamos una lista de productos y la convertimos a Flux
        var productos = Arrays.asList(
                new Producto("1", "Producto 1"),
                new Producto("2", "Producto 2"));

        // Simulamos el comportamiento del repositorio usando Flux
        when(productoRepository.findAll()).thenReturn(Flux.fromIterable(productos));

        // Llamamos al servicio para obtener el Flux<Producto>
        Flux<Producto> resultado = productoService.listarProductos();

        // Verificamos los resultados usando StepVerifier
        StepVerifier.create(resultado)
                .expectNextMatches(producto -> producto.getNombre().equals("Producto 1"))
                .expectNextMatches(producto -> producto.getNombre().equals("Producto 2"))
                .verifyComplete();

        // Verificamos que el repositorio haya sido llamado una vez
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void obtenerProductoPorId() {
        // Simulamos la consulta de un solo producto, el cual es devuelto envuelto en
        // Mono
        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        Mono<Producto> resultado = productoService.obtenerProductoPorId("1");

        // Verificamos que el producto devuelto sea el esperado
        StepVerifier.create(resultado)
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository, times(1)).findById("1");
    }

    @Test
    void crearProducto() {
        // Creamos un Mono que envuelve el producto
        when(productoRepository.save(producto)).thenReturn(Mono.just(producto));

        Mono<Producto> resultado = productoService.crearProducto(producto);

        // Verificamos que el producto devuelto sea el esperado
        StepVerifier.create(resultado)
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository, times(1)).save(producto);
    }
}
