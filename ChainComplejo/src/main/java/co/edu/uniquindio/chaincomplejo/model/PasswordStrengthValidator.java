package co.edu.uniquindio.chaincomplejo.model;

/**
 * VALIDADOR CONCRETO - OPCIONAL
 * Valida la fortaleza de la contraseña según múltiples criterios
 */
public class PasswordStrengthValidator extends BaseValidator {

    @Override
    public Resultado validate(RegistroUsuario cmd) {
        String contraseña = cmd.password;

        // Múltiples criterios de validación (lógica de negocio)
        boolean largo = contraseña.length() >= 8;
        boolean mayusculas = contraseña.chars().anyMatch(Character::isUpperCase);
        boolean minusculas = contraseña.chars().anyMatch(Character::isLowerCase);
        boolean digitos = contraseña.chars().anyMatch(Character::isDigit);

        if (!(largo && mayusculas && minusculas && digitos))
            return Resultado.error("La contraseña debe tener ≥8 caracteres, mayúsculas, minúsculas y dígitos.");

        // OBLIGATORIO: Pasar al siguiente en la cadena
        return validateNext(cmd);
    }
}