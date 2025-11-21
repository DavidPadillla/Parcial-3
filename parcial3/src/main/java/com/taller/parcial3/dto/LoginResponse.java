package com.taller.parcial3.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String correo;
    private Integer usuarioId;
}
