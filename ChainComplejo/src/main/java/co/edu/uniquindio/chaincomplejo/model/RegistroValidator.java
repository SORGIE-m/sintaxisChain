package co.edu.uniquindio.chaincomplejo.model;

/**
 * INTERFAZ PRINCIPAL DEL PATRÓN - OBLIGATORIO
 * Define el contrato que todos los manejadores deben seguir
 * Es el corazón del patrón Chain of Responsibility
 */
public interface RegistroValidator {
    // OBLIGATORIO: Método para encadenar manejadores
    RegistroValidator setNext(RegistroValidator next);

    // OBLIGATORIO: Método para procesar la solicitud
    Resultado validate(RegistroUsuario cmd);
}