package com.taller.parcial3.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
}
