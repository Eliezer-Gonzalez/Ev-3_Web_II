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
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    
    @Autowired
    private PracticaService practicaService;
    
    // Endpoint para que el estudiante cree su práctica
    // Aquí valido los datos y si está todo ok lo guardo en la BD
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

    // Buscar las prácticas por nombre del estudiante
    // Uso el nombre completo como filtro para traer sus prácticas
    @GetMapping("/practicas/{nombreCompleto}")
    public ResponseEntity<?> obtenerPracticasPorEstudiante(@PathVariable String nombreCompleto) {
        try {
            List<Practica> practicas = practicaService.obtenerPracticasPorEstudiante(nombreCompleto);
            if (practicas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "No se encontraron prácticas para el estudiante: " + nombreCompleto));
            }
            return ResponseEntity.ok(
                Map.of("mensaje", "Se encontraron " + practicas.size() + " práctica(s)", "data", practicas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", "Error al buscar prácticas: " + e.getMessage()));
        }
    }
}
