package co.edu.sena.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Datos que el cliente envia en el registro y en el login
 * Las anotaciones validan que no lleguen vacios (Responde error 400)
 */

public class CredencialesDTO {

    //@NotBlank rechaza null, "" y "  ". El maximo coincide con la columna de la base de datos
    @NotBlank(message = "El usuario es obligatorio")
    @Size(max = 50, message = "El usuario no puede superar los 40 caracteres")
    private String user;

    // BCrypt solo procesa min 8 bytes, limite actual
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 8, message = "la contraseña no puede ser menor de 8 caracteres")
    private String password;

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
