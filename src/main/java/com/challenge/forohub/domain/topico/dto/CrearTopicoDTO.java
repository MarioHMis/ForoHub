package com.challenge.forohub.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearTopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long usuaroId,
        @NotNull Long cursoId
) {
}
