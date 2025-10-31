package co.edu.uniquindio.chaincomplejo.model;

/**
 * VALIDADOR CONCRETO - OBLIGATORIO (al menos uno)
 * Valida que los campos obligatorios no estén vacíos
 * EJEMPLO de implementación específica del patrón
 */
public class RequiredFieldsValidator extends BaseValidator {

    @Override
    public Resultado validate(RegistroUsuario cmd) {
        // Validación específica de este manejador
        if (cmd.nombre == null || cmd.nombre.isBlank())
            return Resultado.error("El nombre es obligatorio.");
        if (cmd.email == null || cmd.email.isBlank())
            return Resultado.error("El email es obligatorio.");
        if (cmd.password == null || cmd.password.isBlank())
            return Resultado.error("La contraseña es obligatoria.");

        // OBLIGATORIO: Pasar al siguiente manejador si la validación es exitosa
        return validateNext(cmd);
    }
}