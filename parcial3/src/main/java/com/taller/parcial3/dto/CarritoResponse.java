package com.taller.parcial3.dto;

import lombok.Data;
import java.util.List;

@Data
public class CarritoResponse {

    private Integer idCarrito;
    private Integer idUsuario;
    private List<ProductoDTO> productos;
    private double subtotal;
    private double impuestos;
}
