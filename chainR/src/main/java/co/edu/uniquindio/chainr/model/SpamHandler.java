package co.edu.uniquindio.chainr.model;

//filtro que valida si el mensaje que llega, contiene caracteres propios de SPAM

public class SpamHandler extends BaseHandler {
    @Override
    public boolean handle(String message) {
        if (message.toLowerCase().contains("spam") || message.contains("$$$")) {
            System.out.println("Bloqueado por posible SPAM");
            return false;
        }
        return handleNext(message);//abierto a seguir concatenando filtros a futuro
    }
}

