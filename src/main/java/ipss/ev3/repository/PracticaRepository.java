package ipss.ev3.repository;

import ipss.ev3.model.Practica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PracticaRepository extends MongoRepository<Practica, String> {
    
    List<Practica> findByEstudianteNombreCompleto(String nombreCompleto);
}
