package com.example.miaplicacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.miaplicacion.model.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
}
