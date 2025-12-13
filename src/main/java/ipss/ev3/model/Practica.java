package ipss.ev3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "practicas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Practica {
    
    @Id
    private String id;
    
    @NotNull(message = "Los datos del estudiante son obligatorios")
    @Valid
    private Estudiante estudiante;
    
    @NotNull(message = "Los datos de la empresa son obligatorios")
    @Valid
    private Empresa empresa;
    
    @NotNull(message = "Los datos del jefe directo son obligatorios")
    @Valid
    private JefeDirecto jefeDirecto;
    
    @NotNull(message = "Los datos del profesor supervisor son obligatorios")
    @Valid
    private ProfesorSupervisor profesorSupervisor;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;
    
    @NotNull(message = "La fecha de término es obligatoria")
    private LocalDate fechaTermino;
    
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}
