package co.edu.uniquindio.chainr.model;
//punto de partida, tiene que ser abstracto para poder definir la validacion de cada filtro
public abstract class BaseHandler implements Handler {
    private Handler next;

    @Override
    public Handler setNext(Handler next) {
        this.next = next;
        return next; // permite encadenar con estilo fluido
    }

    protected boolean handleNext(String message) {
        return (next == null) || next.handle(message);// permite que los hijos que validen el mensaje que viene por ingreso
    }
}

