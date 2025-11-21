package com.taller.parcial3.service;

import com.taller.parcial3.model.OrdenDeCompra;
import com.taller.parcial3.repository.OrdenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdenService {

    private final OrdenRepository ordenRepository;

    public OrdenDeCompra guardar(OrdenDeCompra orden) {
        return ordenRepository.save(orden);
    }

    public OrdenDeCompra buscar(Integer id) {
        return ordenRepository.findById(id).orElse(null);
    }
}
