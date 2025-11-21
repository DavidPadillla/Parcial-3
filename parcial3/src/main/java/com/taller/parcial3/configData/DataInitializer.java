package com.taller.parcial3.configData;

import com.taller.parcial3.model.*;
import com.taller.parcial3.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final ComentarioRepository comentarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepository.count() > 0) {
            return;
        }

        List<Usuario> usuarios = new ArrayList<>();

        Usuario u1 = new Usuario();
        u1.setNombre("Juan Pérez");
        u1.setCorreoElectronico("juan.perez@email.com");
        u1.setContrasena(passwordEncoder.encode("Qwerty123"));
        u1.setDireccion("Carrera 45 #10-20");
        u1.setMetodoDePago("Tarjeta de crédito");
        usuarios.add(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Ana Gómez");
        u2.setCorreoElectronico("ana.gomez@email.com");
        u2.setContrasena(passwordEncoder.encode("Pass456"));
        u2.setDireccion("Calle 21 #35-50");
        u2.setMetodoDePago("PayPal");
        usuarios.add(u2);

        Usuario u3 = new Usuario();
        u3.setNombre("Carlos Ruiz");
        u3.setCorreoElectronico("carlos.ruiz@email.com");
        u3.setContrasena(passwordEncoder.encode("Segura789"));
        u3.setDireccion("Avenida Principal #100");
        u3.setMetodoDePago("Transferencia bancaria");
        usuarios.add(u3);

        Usuario u4 = new Usuario();
        u4.setNombre("Sofía Martínez");
        u4.setCorreoElectronico("sofia.martinez@email.com");
        u4.setContrasena(passwordEncoder.encode("Clave987"));
        u4.setDireccion("Calle 8 #20-30");
        u4.setMetodoDePago("Efectivo");
        usuarios.add(u4);

        Usuario u5 = new Usuario();
        u5.setNombre("Diego Fernández");
        u5.setCorreoElectronico("diego.fernandez@email.com");
        u5.setContrasena(passwordEncoder.encode("Contra654"));
        u5.setDireccion("Carrera 77 #40-60");
        u5.setMetodoDePago("Tarjeta débito");
        usuarios.add(u5);

        usuarioRepository.saveAll(usuarios);


        List<Producto> productos = new ArrayList<>();

        productos.add(crearProducto("Laptop", "Portátil con pantalla Full HD y SSD de 512GB", 89999, 10));
        productos.add(crearProducto("Smartphone", "Teléfono con cámara de 108MP y carga rápida", 49950, 20));
        productos.add(crearProducto("Tablet", "Dispositivo con pantalla táctil de 10 pulgadas", 29999, 15));
        productos.add(crearProducto("Auriculares", "Audífonos inalámbricos con cancelación de ruido", 12999, 25));
        productos.add(crearProducto("Teclado", "Teclado mecánico con iluminación RGB", 8999, 30));



        productoRepository.saveAll(productos);


        List<Comentario> comentarios = new ArrayList<>();

        Comentario c1 = new Comentario();
        c1.setProducto(productos.get(0));
        c1.setUsuario(usuarios.get(0));
        c1.setComentario("Excelente rendimiento; muy rápida. ¡Me encanta!");
        c1.setFecha("1/05/2025");
        comentarios.add(c1);

        Comentario c2 = new Comentario();
        c2.setProducto(productos.get(1));
        c2.setUsuario(usuarios.get(1));
        c2.setComentario("Buena cámara pero la batería dura poco.");
        c2.setFecha("3/05/2025");
        comentarios.add(c2);


        comentarioRepository.saveAll(comentarios);

        System.out.println("✔ Datos iniciales cargados correctamente");
    }

    private Producto crearProducto(String nombre, String desc, double precio, int stock) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(desc);
        p.setPrecio(precio);
        p.setStock(stock);
        return p;
    }
}
