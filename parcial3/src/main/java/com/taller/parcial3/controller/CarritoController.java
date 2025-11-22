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
        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoService.crearCarrito(usuario, request.getProductosIds());

        CarritoResponse response = new CarritoResponse();
        response.setIdCarrito(carrito.getIdCarrito());
        response.setIdUsuario(usuario.getIdUsuario());
        response.setSubtotal(carrito.getSubtotal());
        response.setImpuestos(carrito.getImpuestos());
        response.setProductos(
                carrito.getProductos().stream()
                        .map(p -> {
                            ProductoDTO dto = new ProductoDTO();
                            dto.setIdProducto(p.getIdProducto());
                            dto.setNombre(p.getNombre());
                            dto.setDescripcion(p.getDescripcion());
                            dto.setPrecio(p.getPrecio());
                            dto.setStock(p.getStock());
                            return dto;
                        })
                        .toList()
        );
        return response;
    }

    @GetMapping("/{idCarrito}")
    public CarritoResponse obtenerCarrito(Authentication auth, @PathVariable Integer idCarrito) {
        Usuario usuario = usuarioService.buscarPorCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoService.buscarCarrito(idCarrito);

        if (carrito == null) {
            throw new RuntimeException("Carrito no encontrado");
        }

        if (!carrito.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
            throw new RuntimeException("No autorizado para ver este carrito");
        }

        CarritoResponse response = new CarritoResponse();
        response.setIdCarrito(carrito.getIdCarrito());
        response.setIdUsuario(usuario.getIdUsuario());
        response.setSubtotal(carrito.getSubtotal());
        response.setImpuestos(carrito.getImpuestos());
        response.setProductos(
                carrito.getProductos().stream()
                        .map(p -> {
                            ProductoDTO dto = new ProductoDTO();
                            dto.setIdProducto(p.getIdProducto());
                            dto.setNombre(p.getNombre());
                            dto.setDescripcion(p.getDescripcion());
                            dto.setPrecio(p.getPrecio());
                            dto.setStock(p.getStock());
                            return dto;
                        })
                        .toList()
        );
        return response;
    }
}