package ipss.ev3.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorSupervisor {
    
    @NotBlank(message = "El nombre del profesor supervisor es obligatorio")
    private String nombre;
}
