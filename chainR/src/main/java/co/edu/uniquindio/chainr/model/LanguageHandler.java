package co.edu.uniquindio.chainr.model;

//filtro que valida si el mensaje contiene letras o palabras no permitidas

public class LanguageHandler extends BaseHandler {
    @Override
    public boolean handle(String message) {
        // Ejemplo tonto: solo deja pasar si contiene letras latinas básicas
        if (!message.matches("[\\p{ASCII}áéíóúÁÉÍÓÚñÑ¡!¿?\\s\\w.,:;'-]+")) {
            System.out.println("Bloqueado por lenguaje no permitido");
            return false;
        }
        return handleNext(message);//da paso al siguiente filtro en la cadena
    }
}
