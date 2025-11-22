package com.taller.parcial3.configData;

import com.taller.parcial3.model.*;
import com.taller.parcial3.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        usuarios.add(crearUsuario("Juan Pérez", "juan.perez@email.com", "Qwerty123", "Carrera 45 #10-20", "Tarjeta de crédito"));
        usuarios.add(crearUsuario("Ana Gómez", "ana.gomez@email.com", "Pass456", "Calle 21 #35-50", "PayPal"));
        usuarios.add(crearUsuario("Carlos Ruiz", "carlos.ruiz@email.com", "Segura789", "Avenida Principal #100", "Transferencia bancaria"));
        usuarios.add(crearUsuario("Sofía Martínez", "sofia.martinez@email.com", "Clave987", "Calle 8 #20-30", "Efectivo"));
        usuarios.add(crearUsuario("Diego Fernández", "diego.fernandez@email.com", "Contra654", "Carrera 77 #40-60", "Tarjeta débito"));
        usuarioRepository.saveAll(usuarios);


        List<Producto> productos = new ArrayList<>();
        String[] nombres = {"Laptop", "Smartphone", "Tablet", "Auriculares", "Teclado", "Mouse", "Monitor", "Cámara", "Impresora", "Router"};
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            String nombre = nombres[i % 10] + " " + (i + 1);
            String desc = "Descripción del producto " + nombre;
            double precio = 10000 + (rand.nextDouble() * 90000);
            int stock = 5 + rand.nextInt(45);
            productos.add(crearProducto(nombre, desc, precio, stock));
        }
        productoRepository.saveAll(productos);


        List<Comentario> comentarios = new ArrayList<>();
        String[] textos = {
                "Excelente producto",
                "Buena calidad-precio",
                "Me encantó",
                "Cumple lo prometido",
                "Recomendado"
        };

        for (int i = 0; i < 50; i++) {
            Comentario c = new Comentario();
            c.setProducto(productos.get(i));
            c.setUsuario(usuarios.get(i % 5));
            c.setComentario(textos[i % 5] + " - comentario #" + (i + 1));
            c.setFecha((i % 30 + 1) + "/05/2025");
            comentarios.add(c);
        }
        comentarioRepository.saveAll(comentarios);

        System.out.println("✔ Datos iniciales cargados: 5 usuarios, 50 productos, 50 comentarios");
    }

    private Usuario crearUsuario(String nombre, String correo, String pass, String dir, String metodo) {
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreoElectronico(correo);
        u.setContrasena(passwordEncoder.encode(pass));
        u.setDireccion(dir);
        u.setMetodoDePago(metodo);
        return u;
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

