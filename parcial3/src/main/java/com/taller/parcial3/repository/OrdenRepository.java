package com.taller.parcial3.repository;

import com.taller.parcial3.model.OrdenDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<OrdenDeCompra, Integer> {
}
