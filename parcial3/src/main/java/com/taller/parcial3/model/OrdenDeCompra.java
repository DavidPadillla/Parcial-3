package com.taller.parcial3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrden;

    @ManyToMany
    private List<Producto> productos;

    private double subtotal;

    private double impuestos;

    private double envio;

    private double total;
}
