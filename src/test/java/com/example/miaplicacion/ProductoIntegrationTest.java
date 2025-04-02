package com.example.miaplicacion;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll().block(); // Bloqueamos correctamente
        producto = new Producto(null, "Producto de prueba");
    }

    @Test
    void crearProductoYVerificarQueSeGuarda() {
        webTestClient.post().uri("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(producto), Producto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.nombre").isEqualTo("Producto de prueba");
    }

    @Test
    void obtenerProductoPorId() {
        Producto productoGuardado = productoRepository.save(producto).block();
        assertThat(productoGuardado).isNotNull(); // Verificación explícita

        webTestClient.get().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(productoGuardado.getId())
                .jsonPath("$.nombre").isEqualTo("Producto de prueba");
    }

    @Test
    void eliminarProductoYConfirmarQueNoExiste() {
        Producto productoGuardado = productoRepository.save(producto).block(); // Asignamos el resultado correctamente

        webTestClient.delete().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isNoContent();

        // Confirmar que ya no existe con GET
        webTestClient.get().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void listarProductos() {
        productoRepository.save(producto).block(); // Guardamos un producto antes de la prueba

        webTestClient.get().uri("/api/productos")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].nombre").isEqualTo("Producto de prueba");
    }
}
