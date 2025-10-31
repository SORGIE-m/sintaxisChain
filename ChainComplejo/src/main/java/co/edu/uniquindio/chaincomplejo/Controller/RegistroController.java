package co.edu.uniquindio.chaincomplejo.Controller;

import co.edu.uniquindio.chaincomplejo.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;

public class RegistroController {

    // Form
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;

    // Toggles de validadores
    @FXML private CheckBox cbRequired;
    @FXML private CheckBox cbEmail;
    @FXML private CheckBox cbPassword;
    @FXML private CheckBox cbUnique;

    // Acciones y salida
    @FXML private Button btnValidar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnDemoOk;
    @FXML private Button btnDemoError;

    @FXML private Label lblResultado;
    @FXML private TextArea txtLog;

    // Dependencia simulada (tu repo en memoria)
    private final UsuarioRepository usuarioRepo = new InMemoryUsuarioRepo();

    @FXML
    private void initialize() {
        // Datos iniciales
        txtNombre.setText("");
        txtEmail.setText("");
        txtPassword.setText("");

        // Botones
        btnValidar.setOnAction(e -> validar());
        btnLimpiar.setOnAction(e -> limpiar());

        btnDemoOk.setOnAction(e -> {
            txtNombre.setText("Ana");
            txtEmail.setText("ana@example.com");
            txtPassword.setText("Passw0rd");
        });

        btnDemoError.setOnAction(e -> {
            txtNombre.setText("Sara");
            txtEmail.setText("ya@existe.com"); // duplicado en repo
            txtPassword.setText("Passw0rd");
        });

        log("UI lista.");
    }

    private void validar() {
        lblResultado.setText("");
        txtLog.clear();

        RegistroUsuario cmd = new RegistroUsuario(
                txtNombre.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );

        // Construir cadena según toggles (en este orden)
        RegistroValidator head = null, tail = null;

        if (cbRequired.isSelected()) {
            var v = new RequiredFieldsValidator();
            head = attach(head, tail, v);
            tail = v;
            log("+ RequiredFieldsValidator");
        }

        if (cbEmail.isSelected()) {
            var v = new EmailFormatValidator();
            head = (head == null) ? v : head;
            if (tail != null) tail.setNext(v);
            tail = v;
            log("+ EmailFormatValidator");
        }

        if (cbPassword.isSelected()) {
            var v = new PasswordStrengthValidator();
            head = (head == null) ? v : head;
            if (tail != null) tail.setNext(v);
            tail = v;
            log("+ PasswordStrengthValidator");
        }

        if (cbUnique.isSelected()) {
            var v = new UniqueEmailValidator(usuarioRepo);
            head = (head == null) ? v : head;
            if (tail != null) tail.setNext(v);
            tail = v;
            log("+ UniqueEmailValidator");
        }

        if (head == null) {
            lblResultado.setText("⚠ No hay validadores activos. (La cadena estaría vacía)");
            log("Cadena vacía → nada que validar.");
            return;
        }

        // Ejecutar la cadena
        log("— Ejecutando cadena —");
        Resultado r = head.validate(cmd);

        if (r.ok) {
            lblResultado.setStyle("-fx-text-fill:#22c55e;");
            lblResultado.setText("OK");
            log("Resultado: OK");
        } else {
            lblResultado.setStyle("-fx-text-fill:#ef4444;");
            lblResultado.setText("ERROR: " + r.mensaje);
            log("Resultado: ERROR → " + r.mensaje);
        }
    }

    private RegistroValidator attach(RegistroValidator head, RegistroValidator tail, RegistroValidator v) {
        if (head == null) return v;
        if (tail != null) tail.setNext(v);
        return head;
    }

    private void limpiar() {
        txtNombre.clear();
        txtEmail.clear();
        txtPassword.clear();
        lblResultado.setText("");
        txtLog.clear();
        log("Formulario limpio.");
    }

    private void log(String s) {
        txtLog.appendText(LocalTime.now() + "  " + s + "\n");
    }
}
