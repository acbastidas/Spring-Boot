package com.example.miaplicacion;

import com.example.miaplicacion.model.Producto;
import com.example.miaplicacion.repository.ProductoRepository;
import com.example.miaplicacion.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        List<Producto> productos = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.listarProductos();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.size()).isEqualTo(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Producto de prueba");

        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void obtenerProductoPorId() {
        when(productoRepository.findById("1")).thenReturn(Optional.of(producto));

        Producto resultado = productoService.obtenerProductoPorId("1");

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Producto de prueba");

        verify(productoRepository, times(1)).findById("1");
    }

    @Test
    void crearProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Producto de prueba");

        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void eliminarProducto() {
        doNothing().when(productoRepository).deleteById("1");

        productoService.deleteById("1");

        verify(productoRepository, times(1)).deleteById("1");
    }
}