package com.taller.parcial3.service;

import com.taller.parcial3.model.Comentario;
import com.taller.parcial3.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public List<Comentario> listarDesdeFecha(String fecha) {
        return comentarioRepository.findByFechaGreaterThanEqual(fecha);
    }

    public void guardar(Comentario comentario) {
        comentarioRepository.save(comentario);
    }
}
