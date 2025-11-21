package com.taller.parcial3.controller;

import com.taller.parcial3.model.Comentario;
import com.taller.parcial3.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping("/fecha/{fecha}")
    public List<Comentario> listarDesde(@PathVariable String fecha) {
        return comentarioService.listarDesdeFecha(fecha);
    }
}
