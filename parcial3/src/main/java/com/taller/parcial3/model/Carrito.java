package com.taller.parcial3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrito;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Producto> productos;

    private double subtotal;

    private double impuestos;
}
