package ipss.ev3.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JefeDirecto {
    
    @NotBlank(message = "El nombre del jefe directo es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El contacto es obligatorio")
    private String contacto;
}
