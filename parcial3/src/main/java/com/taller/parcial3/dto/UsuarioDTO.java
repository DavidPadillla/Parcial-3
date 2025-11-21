package com.taller.parcial3.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String correoElectronico;
    private String direccion;
    private String metodoDePago;
}
