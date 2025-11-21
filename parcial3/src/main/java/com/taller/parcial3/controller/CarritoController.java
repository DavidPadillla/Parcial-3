package com.taller.parcial3.controller;

import com.taller.parcial3.dto.CarritoResponse;
import com.taller.parcial3.dto.CrearCarritoRequest;
import com.taller.parcial3.dto.ProductoDTO;
import com.taller.parcial3.model.Carrito;
import com.taller.parcial3.model.Usuario;
import com.taller.parcial3.service.CarritoService;
import com.taller.parcial3.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;
    private final UsuarioService usuarioService;

    @PostMapping("/crear")
    public CarritoResponse crearCarrito(Authentication auth,
                                        @RequestBody CrearCarritoRequest request) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).orElseThrow();

        Carrito carrito = carritoService.crearCarrito(usuario, request.getProductosIds());

        CarritoResponse resp = new CarritoResponse();
        resp.setIdCarrito(carrito.getIdCarrito());
        resp.setIdUsuario(usuario.getIdUsuario());
        resp.setSubtotal(carrito.getSubtotal());
        resp.setImpuestos(carrito.getImpuestos());

        resp.setProductos(
                carrito.getProductos().stream().map(p -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProducto(p.getIdProducto());
                    dto.setNombre(p.getNombre());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setPrecio(p.getPrecio());
                    dto.setStock(p.getStock());
                    return dto;
                }).toList()
        );

        return resp;
    }

    @GetMapping("/{idCarrito}")
    public CarritoResponse obtenerCarrito(Authentication auth,
                                          @PathVariable Integer idCarrito) {

        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName()).orElseThrow();

        Carrito carrito = carritoService.buscarCarrito(idCarrito);

        if (carrito == null || !carrito.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("No autorizado para ver este carrito");
        }

        CarritoResponse resp = new CarritoResponse();
        resp.setIdCarrito(carrito.getIdCarrito());
        resp.setIdUsuario(usuario.getIdUsuario());
        resp.setSubtotal(carrito.getSubtotal());
        resp.setImpuestos(carrito.getImpuestos());

        resp.setProductos(
                carrito.getProductos().stream().map(p -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProducto(p.getIdProducto());
                    dto.setNombre(p.getNombre());
                    dto.setDescripcion(p.getDescripcion());
                    dto.setPrecio(p.getPrecio());
                    dto.setStock(p.getStock());
                    return dto;
                }).toList()
        );

        return resp;
    }
}
