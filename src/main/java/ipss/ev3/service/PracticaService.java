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

    // create
    public Practica crearPractica(Practica practica) {
        return practicaRepository.save(practica);
    }

    // get all
    public List<Practica> obtenerTodasLasPracticas() {
        return practicaRepository.findAll();
    }

    // get(id)
    public Optional<Practica> obtenerPracticaPorId(String id) {
        return practicaRepository.findById(id);
    }

    // get (estudiante nombre completo)
    public List<Practica> obtenerPracticasPorEstudiante(String nombreCompleto) {
        return practicaRepository.findByEstudianteNombreCompleto(nombreCompleto);
    }
    
    // update
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
    
    // delete
    public boolean eliminarPractica(String id) {
        if (practicaRepository.existsById(id)) {
            practicaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
