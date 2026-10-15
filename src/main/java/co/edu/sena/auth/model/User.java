package co.edu.sena.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *  Entidad que representa un usuario registrado en el sistema
 * Hibernate la convierte en la tabla "Users" de auth_db.
 */

@Entity
@Table(name= "Users")
public class User {

    //LLave primaria - autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Nombre de usuario: obligatorio y unico (no se puede repetir)
    @Column(nullable = false, unique = true, length = 50)
    private String user;

    //Aqui se guarda el HASH de BCrypt (No se guarda contraseña en texto plano)
    @Column(nullable = false, length = 100)
    private String password;

    //Setters y Getters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
