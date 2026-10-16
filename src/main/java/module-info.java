module org.openjfx.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.openjfx.calculator to javafx.fxml;
    exports org.openjfx.calculator;
}