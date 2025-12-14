package ipss.ev3.service;

import ipss.ev3.model.Practica;
import ipss.ev3.repository.PracticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PracticaService {
    
    @Autowired
    private PracticaRepository practicaRepository;

    // Guardar una nueva práctica en la BD
    public Practica crearPractica(Practica practica) {
        return practicaRepository.save(practica);
    }

    // Traer todas las prácticas sin filtros
    public List<Practica> obtenerTodasLasPracticas() {
        return practicaRepository.findAll();
    }

    // Buscar una práctica específica por su ID
    public Optional<Practica> obtenerPracticaPorId(String id) {
        return practicaRepository.findById(id);
    }

    // Filtrar prácticas por el nombre completo del estudiante
    public List<Practica> obtenerPracticasPorEstudiante(String nombreCompleto) {
        return practicaRepository.findByEstudianteNombreCompleto(nombreCompleto);
    }
    
    // Actualizar datos de una práctica que ya existe
    public Optional<Practica> actualizarPractica(String id, Practica practicaActualizada) {
        return practicaRepository.findById(id)
            .map(practica -> {
                practica.setEstudiante(practicaActualizada.getEstudiante());
                practica.setEmpresa(practicaActualizada.getEmpresa());
                practica.setJefeDirecto(practicaActualizada.getJefeDirecto());
                practica.setProfesorSupervisor(practicaActualizada.getProfesorSupervisor());
                practica.setFechaInicio(practicaActualizada.getFechaInicio());
                practica.setFechaTermino(practicaActualizada.getFechaTermino());
                practica.setDescripcion(practicaActualizada.getDescripcion());
                return practicaRepository.save(practica);
            });
    }
    
    // Eliminar práctica si existe, devuelve true si se borró - false si no se encontró xd
    public boolean eliminarPractica(String id) {
        if (practicaRepository.existsById(id)) {
            practicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
