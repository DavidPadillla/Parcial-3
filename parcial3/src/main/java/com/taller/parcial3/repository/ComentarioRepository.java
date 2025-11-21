package com.taller.parcial3.repository;

import com.taller.parcial3.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    List<Comentario> findByFechaGreaterThanEqual(String fecha);
}
