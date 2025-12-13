package ipss.ev3.controller;

import ipss.ev3.model.Practica;
import ipss.ev3.service.PracticaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    
    @Autowired
    private PracticaService practicaService;
    
    /**
     * POST /api/profesores/practicas
     * Crear una nueva práctica (profesor)
     */
    @PostMapping("/practicas")
    public ResponseEntity<?> crearPractica(@Valid @RequestBody Practica practica) {
        try {
            Practica nuevaPractica = practicaService.crearPractica(practica);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("mensaje", "Práctica creada correctamente", "data", nuevaPractica));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", "Error al crear la práctica: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/profesores/practicas
     * Listar todas las prácticas
     */
    @GetMapping("/practicas")
    public ResponseEntity<?> obtenerTodasLasPracticas() {
        try {
            List<Practica> practicas = practicaService.obtenerTodasLasPracticas();
            if (practicas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No hay prácticas registradas en el sistema"));
            }
            return ResponseEntity.ok(
                Map.of("mensaje", "Se encontraron " + practicas.size() + " práctica(s)", "data", practicas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", "Error al obtener las prácticas: " + e.getMessage()));
        }
    }
    
    /**
     * PUT /api/profesores/practicas/{id}
     * Actualizar una práctica existente
     */
    @PutMapping("/practicas/{id}")
    public ResponseEntity<?> actualizarPractica(@PathVariable String id, @Valid @RequestBody Practica practica) {
        try {
            return practicaService.actualizarPractica(id, practica)
                .map(practicaActualizada -> ResponseEntity.ok(
                    Map.of("mensaje", "Práctica actualizada correctamente", "data", practicaActualizada)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontró la práctica con ID: " + id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", "Error al actualizar la práctica: " + e.getMessage()));
        }
    }
    
    /**
     * DELETE /api/profesores/practicas/{id}
     * Eliminar una práctica
     */
    @DeleteMapping("/practicas/{id}")
    public ResponseEntity<?> eliminarPractica(@PathVariable String id) {
        try {
            boolean eliminado = practicaService.eliminarPractica(id);
            if (eliminado) {
                return ResponseEntity.ok(
                    Map.of("mensaje", "Práctica eliminada correctamente"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontró la práctica con ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", "Error al eliminar la práctica: " + e.getMessage()));
        }
    }
}
