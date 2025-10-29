package co.edu.uniquindio.chainr.model;

public interface Handler {
    Handler setNext(Handler next);
    boolean handle(String mensaje); // true= procesado con exito. False= bloqueado
}
