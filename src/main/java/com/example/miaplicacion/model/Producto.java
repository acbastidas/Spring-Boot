package com.example.miaplicacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos") // Define la colección en MongoDB
public class Producto {

    @Id
    private String id; // MongoDB usa String como ID en lugar de Long
    private String nombre;

    // ✅ Constructor vacío (Spring lo necesita)
    public Producto() {
    }

    // ✅ Constructor con parámetros
    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // ✅ Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
