module co.edu.uniquindio.chainr {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.chainr to javafx.fxml;
    exports co.edu.uniquindio.chainr;
}