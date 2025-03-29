package com.example.miaplicacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.miaplicacion.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
