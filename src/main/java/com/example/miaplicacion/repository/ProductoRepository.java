package com.example.miaplicacion.repository;

import com.example.miaplicacion.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}
