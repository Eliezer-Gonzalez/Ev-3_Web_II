package ipss.ev3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Capturar errores cuando faltan campos obligatorios o tienen formato incorrecto
    // Esto se activa cuando las validaciones del @Valid fallan
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        
        // Recorro todos los errores y armo un mapa campo-error
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });

        // Junto todos los campos con error en un solo string
        String camposConError = errores.keySet().stream()
            .collect(Collectors.joining(", "));
        
        String mensajeGeneral = "Hay campos vacíos o inválidos: " + camposConError;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", mensajeGeneral);
        respuesta.put("errores", errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    // Capturar cualquier otro error que no manejé antes
    // Esto es como un catch-all para errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Error interno del servidor");
        respuesta.put("detalle", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
}
