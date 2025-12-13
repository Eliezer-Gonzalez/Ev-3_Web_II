package ipss.ev3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    
    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;
    
    @NotBlank(message = "La carrera es obligatoria")
    private String carrera;
    
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
}
