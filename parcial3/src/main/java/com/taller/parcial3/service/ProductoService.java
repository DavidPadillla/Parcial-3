package com.taller.parcial3.service;

import com.taller.parcial3.model.Producto;
import com.taller.parcial3.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> listarStockMenorA(int cantidad) {
        return productoRepository.findByStockLessThan(cantidad);
    }

    public Producto guardar(Producto p) {
        return productoRepository.save(p);
    }

    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

}
