package co.edu.uniquindio.chainr.main;
import co.edu.uniquindio.chainr.model.Handler;
import co.edu.uniquindio.chainr.model.LanguageHandler;
import co.edu.uniquindio.chainr.model.LengthHandler;
import co.edu.uniquindio.chainr.model.SpamHandler;

public class App {
    public static void main(String[] args) {
        Handler chain = new LengthHandler(50);
        chain.setNext(new LanguageHandler())
                .setNext(new SpamHandler());

        String ok = "Hola, este mensaje es corto y limpio.";
        String largo = "x".repeat(60);//permite repetir esa cadena de texto las veces que se especifique
        String spam = "Oferta $$$ imperdible, compra ahora SPAM!";

        System.out.println(chain.handle(ok));     // true -> pasa todos los filtros
        System.out.println(chain.handle(largo));  // false -> bloqueado por longitud
        System.out.println(chain.handle(spam));   // false -> bloqueado por SPAM

    }
}