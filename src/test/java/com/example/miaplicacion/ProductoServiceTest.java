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

import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void listarProductos() {
        // ✅ Usamos Collections.singletonList() para evitar el error
        when(productoRepository.findAll()).thenReturn(Collections.singletonList(new Producto(1L, "Producto1")));

        assertEquals(1, productoService.listarProductos().size());
    }

    @Test
    void obtenerProductoPorId() {
        Producto producto = new Producto(1L, "Producto1");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        assertEquals(producto, productoService.obtenerProductoPorId(1L));
    }

    @Test
    void crearProducto() {
        Producto producto = new Producto(1L, "Producto1");
        when(productoRepository.save(producto)).thenReturn(producto);

        assertEquals(producto, productoService.crearProducto(producto));
        verify(productoRepository, times(1)).save(producto);
    }
}