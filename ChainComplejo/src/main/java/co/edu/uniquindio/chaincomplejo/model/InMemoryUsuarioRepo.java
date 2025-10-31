package co.edu.uniquindio.chaincomplejo.model;

/**
 * IMPLEMENTACIÓN DE REPOSITORIO - OPCIONAL
 * Simula una base de datos en memoria para demostración
 */
public class InMemoryUsuarioRepo implements UsuarioRepository {
    @Override
    public boolean existeEmail(String email) {
        // Simulación: el email "ya@existe.com" ya está registrado
        return "ya@existe.com".equalsIgnoreCase(email);
    }
}