package co.edu.sena.auth.service;

import co.edu.sena.auth.dto.CredencialesDTO;
import co.edu.sena.auth.model.User;
import co.edu.sena.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Logica de negocio (registro y inicio de sesion)
 * Validacion , encriptación de contraseña y comparación
 */

@Service
public class AuthService {

    private final UserRepository userRepository;

    // Encriptador BCrypt: Convierte la contraseña en un hash irreversible
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Spring entrega el repositorio automaticamente (inyeccion de dependencias)
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registra a un usuario nuevo con la contraseña encriptada
     *
     * @return true si se registro, false si el usuario ya existe
     */
    public boolean register(CredencialesDTO credencial) {
        //Si el usuario ya existe, no se registra (el controller responde 409)
        if (userRepository.existsByUser(credencial.getUser())) {
            return false;
        }

        User newUser = new User();
        newUser.setUser(credencial.getUser());
        //Se guarda el HASH, nunca la contraseña en texto plano
        newUser.setPassword(passwordEncoder.encode(credencial.getPassword()));

        userRepository.save(newUser);
        return true;
    }

    /**
     * Verifica usuario y contraseña.
     *
     * @return true si las credenciales son correctas, false en cualquier otro caso
     */
    public boolean authenticate(CredencialesDTO credencial) {
        return userRepository.findByUser(credencial.getUser())
        // Si el usuario existe, compara la contraseña enviada contra el hash guardado
                .map(user -> passwordEncoder.matches(
                        credencial.getPassword(), user.getPassword()))
                // Si el usuario no existe, la autentificacion falla
                .orElse(false);
    }
}
