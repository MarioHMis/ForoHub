package com.challenge.forohub.domain.curso.dto;

import com.challenge.forohub.domain.curso.Categoria;
import com.challenge.forohub.domain.curso.Curso;

public record DetalleCursoDTO(Long id, String nombre, Categoria categoria, Boolean activo) {
    public DetalleCursoDTO(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria(), curso.getActivo());
    }
}
