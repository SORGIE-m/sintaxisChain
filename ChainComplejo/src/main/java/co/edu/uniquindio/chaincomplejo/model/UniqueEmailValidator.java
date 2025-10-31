package co.edu.uniquindio.chaincomplejo.model;

/**
 * VALIDADOR CONCRETO - OPCIONAL
 * Valida la unicidad del email usando una dependencia externa
 * EJEMPLO de validador con dependencias
 */
public class UniqueEmailValidator extends BaseValidator {
    // Dependencia externa (NO es parte del patrón)
    private final UsuarioRepository repositorio;

    // Constructor con inyección de dependencias
    public UniqueEmailValidator(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Resultado validate(RegistroUsuario cmd) {
        // Validación que requiere acceso externo
        if (repositorio.existeEmail(cmd.email))
            return Resultado.error("El email ya está registrado.");

        // OBLIGATORIO: Continuar la cadena
        return validateNext(cmd);
    }
}
