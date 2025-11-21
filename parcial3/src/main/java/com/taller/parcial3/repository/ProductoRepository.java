package com.taller.parcial3.repository;

import com.taller.parcial3.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByStockLessThan(int cantidad);
}
