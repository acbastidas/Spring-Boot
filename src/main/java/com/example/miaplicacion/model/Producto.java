package com.example.miaplicacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // ✅ Constructor vacío (Spring lo necesita)
    public Producto() {
    }

    // ✅ Constructor con parámetros
    public Producto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
