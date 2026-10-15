package co.edu.sena.auth.repository;

import co.edu.sena.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Acceso a la tabla Users. Spring Data genera la implementación sola
 */


public interface UserRepository extends JpaRepository<User, Long>{

    // Busca un usuario por su nombre - Vacio si no existe (Usado por el login)
    Optional<User> findByUser(String user);

    // Indica si ya existe ese nombre de usuario
    boolean existsByUser(String user);
}
