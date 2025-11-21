package com.taller.parcial3.controller;

import com.taller.parcial3.model.OrdenDeCompra;
import com.taller.parcial3.service.OrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orden")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public OrdenDeCompra crear(@RequestBody OrdenDeCompra orden) {
        return ordenService.guardar(orden);
    }

    @GetMapping("/{id}")
    public OrdenDeCompra buscar(@PathVariable Integer id) {
        return ordenService.buscar(id);
    }
}
