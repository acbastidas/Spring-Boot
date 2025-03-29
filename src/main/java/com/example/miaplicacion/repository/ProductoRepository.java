package com.example.miaplicacion.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.example.miaplicacion.model.Producto;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}
