package com.taller.parcial3.controller;

import com.taller.parcial3.dto.ProductoDTO;
import com.taller.parcial3.model.Producto;
import com.taller.parcial3.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/stock/{cantidad}")
    public List<ProductoDTO> productosPorStock(@PathVariable int cantidad) {
        return productoService.listarStockMenorA(cantidad)
                .stream()
                .map(p -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProducto(p.getIdProducto());
                    dto.setNombre(p.getNombre());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setPrecio(p.getPrecio());
                    dto.setStock(p.getStock());
                    return dto;
                })
                .toList();
    }

    @GetMapping("/listar")
    public List<ProductoDTO> listarTodos() {
        return productoService.listarTodos()
                .stream()
                .map(p -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProducto(p.getIdProducto());
                    dto.setNombre(p.getNombre());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setPrecio(p.getPrecio());
                    dto.setStock(p.getStock());
                    return dto;
                })
                .toList();
    }
}
