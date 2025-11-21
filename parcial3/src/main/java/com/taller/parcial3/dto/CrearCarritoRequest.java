package com.taller.parcial3.dto;

import lombok.Data;

import java.util.List;

@Data
public class CrearCarritoRequest {

    private List<Integer> productosIds;
}
