package com.challenge.forohub.domain.respuesta.validations.update;

import com.challenge.forohub.domain.respuesta.dto.CrearRespuestaDTO;

public interface ValidarRespuestaActualizada {

        void validate(CrearRespuestaDTO data, Long respuestaId);

}
