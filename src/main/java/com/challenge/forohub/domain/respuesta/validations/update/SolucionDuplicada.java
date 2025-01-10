package com.challenge.forohub.domain.respuesta.validations.update;

import com.challenge.forohub.domain.respuesta.Respuesta;
import com.challenge.forohub.domain.respuesta.dto.ActualizarRespuestaDTO;
import com.challenge.forohub.domain.respuesta.repository.RespuestaRepository;
import com.challenge.forohub.domain.respuesta.validations.create.ValidarRespuestaCreada;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolucionDuplicada implements ValidarRespuestaActualizada {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRespository topicoRepository;

    @Override
    public void validate(ActualizarRespuestaDTO data, Long respuestaId) {
        if (data.solucion()) {
            Respuesta respuesta = respuestaRepository.getReferenceById(respuestaId);
            var topicoResuelto = topicoRepository.getReferenceById(respuesta.getTopico().getId());
            if (topicoResuelto.getEstado() == Estado.CLOSED) {
                throw new ValidationException("No se puede marcar como solución una respuesta en un tópico cerrado");
            }
        }
    }
}
