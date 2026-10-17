package co.edu.sena.auth.controller;

import co.edu.sena.auth.dto.CredencialesDTO;
import co.edu.sena.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Expone los endpoints del servicio web de autenticación.
 * Recibe la petición HTTP, llama al service y responde con el código adecuado.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    // Spring entrega el service automáticamente (inyección de dependencias)
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * POST /api/auth/registro
     * 201 si se registró, 409 si el usuario ya existe, 400 si faltan datos.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registrar(
            @Valid @RequestBody CredencialesDTO credenciales, BindingResult resultado) {

        // Si el usuario o la contraseña llegaron vacíos, se responde 400
        if (resultado.hasErrors()) {
            return response(HttpStatus.BAD_REQUEST, "Bad Request: " + firstError(resultado));
        }

        // El service devuelve false cuando el nombre de usuario ya estaba registrado
        if (!authService.register(credenciales)) {
            return response(HttpStatus.CONFLICT, "El usuario ya existe");
        }

        return response(HttpStatus.CREATED, "Usuario registrado correctamente");
    }

    /**
     * POST /api/auth/login
     * 200 si las credenciales son correctas, 401 en caso contrario, 400 si faltan datos.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> logIn(
            @Valid @RequestBody CredencialesDTO credencial, BindingResult result) {

        if (result.hasErrors()) {
            return response(HttpStatus.BAD_REQUEST, "Bad Request: " + firstError(result));
        }

        // Usuario inexistente o clave incorrecta: la misma respuesta para ambos casos
        if (!authService.authenticate(credencial)) {
            return response(HttpStatus.UNAUTHORIZED, "Error en la autenticación");
        }

        return response(HttpStatus.OK, "Autenticación satisfactoria");
    }

    // Arma la respuesta JSON: {"mensaje": "..."} con el código HTTP indicado
    private ResponseEntity<Map<String, String>> response(HttpStatus state, String message) {
        return ResponseEntity.status(state).body(Map.of("mensaje", message));
    }

    // Toma el mensaje del primer campo que falló la validación
    private String firstError(BindingResult result) {
        return result.getFieldErrors().get(0).getDefaultMessage();
    }
}