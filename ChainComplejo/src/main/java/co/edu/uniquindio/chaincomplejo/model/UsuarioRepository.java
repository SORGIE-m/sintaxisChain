package co.edu.uniquindio.chaincomplejo.model;

/**
 * INTERFAZ DE REPOSITORIO - OPCIONAL (depende del caso de uso)
 * No es parte del patrón, es una dependencia externa
 */
public interface UsuarioRepository {
    boolean existeEmail(String email);
}