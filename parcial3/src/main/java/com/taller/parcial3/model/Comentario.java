package com.taller.parcial3.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario usuario;

    private String comentario;

    private String fecha;
}
