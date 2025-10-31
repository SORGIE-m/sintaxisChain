package co.edu.uniquindio.chainr.main;

import co.edu.uniquindio.chainr.model.Handler;
import co.edu.uniquindio.chainr.model.LanguageHandler;
import co.edu.uniquindio.chainr.model.LengthHandler;
import co.edu.uniquindio.chainr.model.SpamHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Esta clase ahora puede:
 * 1️⃣ Ejecutar la prueba por consola del patrón.
 * 2️⃣ Iniciar la interfaz JavaFX.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        var url = App.class.getResource("/co/edu/uniquindio/chainr/View/ChainView.fxml");
        if (url == null) {
            throw new IllegalStateException("No se encontró ChainView.fxml en /co/edu/uniquindio/chainr/View/");
        }
        FXMLLoader loader = new FXMLLoader(url);
        stage.setTitle("Cadena de Responsabilidad — Demo (MVC)");
        stage.setScene(new Scene(loader.load(), 980, 720));
        stage.show();
    }


    public static void main(String[] args) {
        // 🔹 1. Demostración por consola
        Handler chain = new LengthHandler(50);
        chain.setNext(new LanguageHandler())
                .setNext(new SpamHandler());

        String ok = "Hola, este mensaje es corto y limpio.";
        String largo = "x".repeat(60);
        String spam = "Oferta $$$ imperdible, compra ahora SPAM!";

        System.out.println("---- DEMO CONSOLA ----");
        System.out.println(chain.handle(ok));     // true
        System.out.println(chain.handle(largo));  // false
        System.out.println(chain.handle(spam));   // false

        // 🔹 2. Arranque de la interfaz JavaFX
        // (lanza la ventana después de las pruebas de consola)
        launch(args);
    }
}
