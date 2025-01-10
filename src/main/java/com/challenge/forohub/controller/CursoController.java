package com.challenge.forohub.controller;

import com.challenge.forohub.domain.curso.Curso;
import com.challenge.forohub.domain.curso.dto.ActualizarCursoDTO;
import com.challenge.forohub.domain.curso.dto.CrearCursoDTO;
import com.challenge.forohub.domain.curso.dto.DetalleCursoDTO;
import com.challenge.forohub.domain.curso.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearerkey")
@Tag(name = "Curso", description = "Puede pertenecer a uno de las muchas categoris definidas")

public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Crear un curso", description = "Crea un curso con los datos proporcionados")
    public ResponseEntity<DetalleCursoDTO> crearTopico(@RequestBody @Valid CrearCursoDTO crearCursoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = new Curso(crearCursoDTO);
        repository.save(curso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalleCursoDTO(curso));
    }

    @GetMapping( "/all")
    @Operation(summary = "Listar todos los cursos", description = "Lista todos los cursos")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursos(Pageable pageable) {
        return ResponseEntity.ok(repository.findAllByActivoTrue(pageable).map(DetalleCursoDTO::new));
    }

    @GetMapping("/activos")
    @Operation(summary = "Listar cursos activos", description = "Lista todos los cursos activos")
    public ResponseEntity<Page<DetalleCursoDTO>> listarCursosActivos(Pageable pageable) {
        var pagina = repository.findAllByActivoTrue(pageable).map(DetalleCursoDTO::new);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso", description = "Obtiene un curso por su id")
    public ResponseEntity<DetalleCursoDTO> obtenerCurso(@PathVariable Long id) {
       var idCurso = new DetalleCursoDTO(repository.findById(id).orElseThrow());
        return ResponseEntity.ok(idCurso);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un curso", description = "Actualiza un curso con los datos proporcionados")
    public ResponseEntity<DetalleCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO actualizarCruzoDTO, @PathVariable Long id) {
        Curso curso = repository.getReferenceById(id);
        curso.actualizarCurso(actualizarCruzoDTO);

        var datosDelCurso = new DetalleCursoDTO(
            curso.getId(),
            curso.getNombre(),
            curso.getCategoria(),
            curso.getActivo()
        );
        return ResponseEntity.ok(datosDelCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso por su id")
    public ResponseEntity<?> eliminarCuso(@PathVariable Long id) {
        Curso curso = repository.getReferenceById(id);
        curso.eliminarCurso();
        return ResponseEntity.noContent().build();
    }

}

