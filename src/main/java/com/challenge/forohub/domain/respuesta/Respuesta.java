package com.challenge.forohub.domain.respuesta;

import com.challenge.forohub.domain.respuesta.dto.ActualizarRespuestaDTO;
import com.challenge.forohub.domain.respuesta.dto.CrearRespuestaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha de creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "ultima actualizacion")
    private LocalDateTime ultimaActualizacion;

    private Boolean solucion;
    private Boolean borrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    public Respuesta(CrearRespuestaDTO respuestaDTO, Usuario usuario, Topico topico) {
        this.mensaje = CrearRespuestaDTO.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaActualizacion = LocalDateTime.now();
        this.solucion = false;
        this.borrado = false;
        this.usuario = usuario;
        this.topico = topico;

    }

    public void actualizarRespuesta(ActualizarRespuestaDTO respuestaDTO) {
        if(actualizarRespuestaDTO.mensaje() != null){
            this.mensaje = actualizarRespuestaDTO.mensaje();
        }
        if (actualizarRespuestaDTO.solucion() != null) {
            this.solucion = actualizarRespuestaDTO.solucion();
        }
        this.ultimaActualizacion = LocalDateTime.now();
    }
    public void eliminarRespuesta() {
        this.borrado = true;
    }

}
