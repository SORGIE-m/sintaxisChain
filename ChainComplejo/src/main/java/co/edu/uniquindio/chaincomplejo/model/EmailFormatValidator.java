package co.edu.uniquindio.chaincomplejo.model;

import java.util.regex.Pattern;

/**
 * VALIDADOR CONCRETO - OPCIONAL (ejemplo de especialización)
 * Valida el formato del email usando expresiones regulares
 */
public class EmailFormatValidator extends BaseValidator {
    // Patrón de validación (NO es parte del patrón, es lógica de negocio)
    private static final Pattern PATRON_EMAIL =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    public Resultado validate(RegistroUsuario cmd) {
        // Validación específica del formato de email
        if (!PATRON_EMAIL.matcher(cmd.email).matches())
            return Resultado.error("El email no tiene un formato válido.");

        // OBLIGATORIO: Continuar con el siguiente validador
        return validateNext(cmd);
    }
}