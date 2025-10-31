package co.edu.uniquindio.chaincomplejo.model;

/**
 * CLASE DE DATOS - OBLIGATORIO
 * Representa la solicitud que viaja por la cadena
 * Debe contener todos los datos necesarios para las validaciones
 */
public class RegistroUsuario {
    // Campos finales para inmutabilidad (BUENA PRÁCTICA)
    public final String nombre;
    public final String email;
    public final String password;

    public RegistroUsuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
}