package co.edu.uniquindio.chainr.model;

//filtro concreto, verifica que el mensaje contenga una cantidad limitada de caracteres

public class LengthHandler extends BaseHandler {
    private final int max;

    public LengthHandler(int max) {
        this.max = max;
    }//constructor

    @Override
    public boolean handle(String message) {
        if (message.length() > max) {
            System.out.println("Bloqueado por longitud > " + max);
            return false;
        }
        return handleNext(message);//si no se bloquea, da paso al siguiente filtro
    }
}
