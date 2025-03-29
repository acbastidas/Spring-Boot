package com.example.miaplicacion;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
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
        productoRepository.deleteAll().block(); // Limpiar la BD antes de cada prueba
        producto = new Producto(null, "Producto de prueba");
    }

    @Test
    void crearProducto() {
        webTestClient.post().uri("/api/productos")
                .bodyValue(producto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Producto.class)
                .value(p -> {
                    assertThat(p.getId()).isNotNull();
                    assertThat(p.getNombre()).isEqualTo("Producto de prueba");
                });
    }

    @Test
    void obtenerProductoPorId() {
        Producto productoGuardado = productoRepository.save(producto).block();
        assert productoGuardado != null;

        webTestClient.get().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .value(p -> assertThat(p.getNombre()).isEqualTo("Producto de prueba"));
    }

    @Test
    void eliminarProducto() {
        Producto productoGuardado = productoRepository.save(producto).block();
        assert productoGuardado != null;

        webTestClient.delete().uri("/api/productos/" + productoGuardado.getId())
                .exchange()
                .expectStatus().isNoContent();

        boolean existe = productoRepository.findById(productoGuardado.getId()).blockOptional().isPresent();
        assertThat(existe).isFalse();
    }
}
