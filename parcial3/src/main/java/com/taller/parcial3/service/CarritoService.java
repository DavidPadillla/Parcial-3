package com.taller.parcial3.service;

import com.taller.parcial3.model.Carrito;
import com.taller.parcial3.model.Producto;
import com.taller.parcial3.model.Usuario;
import com.taller.parcial3.repository.CarritoRepository;
import com.taller.parcial3.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public Carrito crearCarrito(Usuario usuario, List<Integer> idsProductos) {
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);

        double subtotal = 0;
        double impuestos = 0;

        List<Producto> productos = idsProductos.stream()
                .map(id -> productoRepository.findById(id).orElseThrow())
                .toList();

        for (Producto p : productos) {
            p.setStock(p.getStock() - 1);
            productoRepository.save(p);
            subtotal += p.getPrecio();
        }

        impuestos = subtotal * 0.19;
        carrito.setProductos(productos);
        carrito.setSubtotal(subtotal);
        carrito.setImpuestos(impuestos);

        return carritoRepository.save(carrito);
    }

    public Carrito buscarCarrito(Integer id) {
        return carritoRepository.findById(id).orElse(null);
    }
}
