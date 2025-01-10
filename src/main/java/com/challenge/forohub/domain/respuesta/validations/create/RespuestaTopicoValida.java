package com.challenge.forohub.domain.respuesta.validations.create;

import com.challenge.forohub.domain.respuesta.dto.CrearRespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaTopicoValida implements ValidarRespuestaCreada {

    @Autowired
    private TopicoRepository repository;

    @Override
    publicVoid validate(CrearRespuestaDTO data) {
        var topicoExiste = repository.existsById(data.topicoId());
        if (!topicoExiste) {
            throw new RuntimeException("El tópico no existe");
        }
        var topicoAbierto = repository.findById(data.topicoId());
        if(topicoAbierto != Estado.OPEN){
            throw new RuntimeException("El tópico no está abierto");
        }
    }
}
