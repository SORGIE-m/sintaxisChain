module co.edu.uniquindio.chaincomplejo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens co.edu.uniquindio.chaincomplejo to javafx.fxml;
    exports co.edu.uniquindio.chaincomplejo.main;
    opens co.edu.uniquindio.chaincomplejo.main to javafx.fxml;
}