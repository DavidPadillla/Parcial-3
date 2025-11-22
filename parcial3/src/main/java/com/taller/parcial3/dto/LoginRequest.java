package com.taller.parcial3.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String correoElectronico;
    private String contrasena;
}
