package com.challenge.forohub.domain.curso.dto;

import com.challenge.forohub.domain.curso.Categoria;

public record ActualizarCursoDTO(
        String nombre,
        Categoria categoria,
        Boolean activo
) {
}
