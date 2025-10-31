package co.edu.uniquindio.chaincomplejo.main;

import co.edu.uniquindio.chaincomplejo.model.*;

/**
 * CLASE CLIENTE - OBLIGATORIO
 * Construye la cadena y la utiliza
 * Demuestra el patrón en acción
 */
public class App {
    public static void main(String[] args) {
        // Configuración de dependencias
        UsuarioRepository repositorio = new InMemoryUsuarioRepo();

        // OBLIGATORIO: Construcción de la cadena de responsabilidad
        // El ORDEN es importante y configurable
        RegistroValidator cadena = new RequiredFieldsValidator();
        cadena.setNext(new EmailFormatValidator())
                .setNext(new PasswordStrengthValidator())
                .setNext(new UniqueEmailValidator(repositorio));

        // Casos de prueba para demostrar el patrón
        RegistroUsuario caso1 = new RegistroUsuario("Ana", "ana@example.com", "Passw0rd");   // OK
        RegistroUsuario caso2 = new RegistroUsuario("", "mal", "123");                       // falla por nombre
        RegistroUsuario caso3 = new RegistroUsuario("Luis", "luis.example.com", "Passw0rd"); // email inválido
        RegistroUsuario caso4 = new RegistroUsuario("Sara", "ya@existe.com", "Passw0rd");    // email duplicado

        // OBLIGATORIO: Uso de la cadena
        System.out.println("Caso 1: " + cadena.validate(caso1)); // OK
        System.out.println("Caso 2: " + cadena.validate(caso2)); // ERROR nombre
        System.out.println("Caso 3: " + cadena.validate(caso3)); // ERROR email inválido
        System.out.println("Caso 4: " + cadena.validate(caso4)); // ERROR email ya registrado
    }
}