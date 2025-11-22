package com.taller.parcial3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    @ManyToOne(optional = true)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
