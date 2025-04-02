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
        // Guardamos el producto en la base de datos
        Producto productoGuardado = productoRepository.save(producto).block();
        assertThat(productoGuardado).isNotNull(); // Verificamos que el producto fue guardado

        // Realizamos el DELETE
        webTestClient.delete().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isNoContent(); // Esperamos 204 No Content

        // Verificamos que el producto ya no existe, debería devolver 404 NOT_FOUND
        webTestClient.get().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isNotFound(); // Esperamos 404 NOT_FOUND

        // Verificación adicional para asegurarse de que el producto realmente ha sido
        // eliminado en la base de datos
        Producto productoEliminado = productoRepository.findById(productoGuardado.getId()).block();
        assertThat(productoEliminado).isNull(); // Debería ser null si el producto fue eliminado
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
