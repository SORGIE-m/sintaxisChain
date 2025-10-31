package co.edu.uniquindio.chaincomplejo.model;

/**
 * CLASE BASE ABSTRACTA - OPCIONAL PERO ALTAMENTE RECOMENDADA
 * Implementa la lógica común de encadenamiento
 * Evita duplicar código en todos los validadores concretos
 */
public abstract class BaseValidator implements RegistroValidator {
    // OBLIGATORIO: Referencia al siguiente manejador en la cadena
    private RegistroValidator next;

    // OBLIGATORIO: Implementación del encadenamiento.
    @Override
    public RegistroValidator setNext(RegistroValidator next) {
        this.next = next;
        return next; // Permite encadenamiento fluido
    }

    /**
     * OBLIGATORIO: Método para pasar al siguiente manejador
     * Si no hay siguiente, retorna éxito (fin de cadena)
     */
    protected Resultado validateNext(RegistroUsuario cmd) {
        return (next == null) ? Resultado.ok() : next.validate(cmd);
    }
}