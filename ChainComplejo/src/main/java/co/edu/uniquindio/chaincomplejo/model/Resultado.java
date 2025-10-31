package co.edu.uniquindio.chaincomplejo.model;

/**
 * CLASE DE RESULTADO - OBLIGATORIO
 * Representa la respuesta de cada validador
 * Formato estandarizado para toda la cadena
 */
public class Resultado {
    // OBLIGATORIO: Indicador de éxito/fallo
    public final boolean ok;

    // OBLIGATORIO: Mensaje descriptivo
    public final String mensaje;

    // Constructor privado para usar métodos factory
    private Resultado(boolean ok, String mensaje) {
        this.ok = ok;
        this.mensaje = mensaje;
    }

    // OBLIGATORIO: Métodos factory para crear resultados
    public static Resultado ok() {
        return new Resultado(true, "OK");
    }

    public static Resultado error(String msg) {
        return new Resultado(false, msg);
    }

    @Override
    public String toString() {
        return ok ? "OK" : "ERROR: " + mensaje;
    }
}