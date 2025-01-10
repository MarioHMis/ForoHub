package com.challenge.forohub.domain.respuesta.validations.create;

import com.challenge.forohub.domain.respuesta.dto.CrearRespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RespuestaUsuarioValida implements ValidarRespuestaCreada {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void  validate(CrearRespuestaDTO data) {
        var usuarioExiste = usuarioRepository.existsById(data.usuarioId());
        if (!usuarioExiste) {
            throw new RuntimeException("El usuario no existe");
        }

        var usuarioHabilitado = repository.findByID(data.usuarioId()).get().isEnabled();
        if (!usuarioHabilitado) {
            throw new RuntimeException("El usuario no está habilitado");
        }
    }

}
