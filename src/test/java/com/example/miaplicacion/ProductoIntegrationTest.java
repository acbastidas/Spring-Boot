package com.example.miaplicacion;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
        producto = new Producto(null, "Producto de prueba");
    }

    @Test
    void crearProducto() throws Exception {
        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nombre").value("Producto de prueba"));
    }

    @Test
    void obtenerProductoPorId() throws Exception {
        Producto productoGuardado = productoRepository.save(producto);

        mockMvc.perform(get("/api/productos/" + productoGuardado.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto de prueba"));
    }

    @Test
    void eliminarProducto() throws Exception {
        Producto productoGuardado = productoRepository.save(producto);

        mockMvc.perform(delete("/api/productos/" + productoGuardado.getId()))
                .andExpect(status().isNoContent());

        boolean existe = productoRepository.findById(productoGuardado.getId()).isPresent();
        assert !existe;
    }
}