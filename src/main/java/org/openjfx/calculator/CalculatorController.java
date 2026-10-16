package org.openjfx.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
    @FXML
    private Label display;
    private double firstOperand;
    private String operator;
    private boolean startNewNumber = true;

    @FXML
    private void onDigitClick(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String digit = button.getText();

        if (startNewNumber) {
            display.setText(digit);
            startNewNumber = false;
        } else if (display.getText().equals("0")) {
            display.setText(digit);
        } else {
            display.setText(display.getText() + digit);
        }
    }

    @FXML
    private void onClearClick(javafx.event.ActionEvent event) {
        display.setText("0");
    }

    @FXML
    private void onDecimalClick(javafx.event.ActionEvent event) {
        String currentValue = display.getText();
        display.setText(currentValue + ",");
    }

    @FXML
    private void onSignChangeClick(javafx.event.ActionEvent event) {
        String currentValue = display.getText();

        if (currentValue.equals("0") || currentValue.equals("0,")){
            return;
        }

        if (currentValue.startsWith("-")){
            display.setText(currentValue.substring(1));
        } else {
            display.setText("-" + currentValue);
        }
    }

    @FXML
    private void onOperatorClick(javafx.event.ActionEvent event){
        Button button = (Button) event.getSource();

        firstOperand = Double.parseDouble(
                display.getText().replace(',', '.')
        );

        operator = button.getText();
        startNewNumber = true;
    }

    @FXML
    private void onEqualsClick() {
        if (operator == null) {
            return;
        }

        double secondOperand = Double.parseDouble(
                display.getText().replace(',', '.')
        );

        double result;

        switch (operator) {
            case "+" -> result = firstOperand + secondOperand;
            case "-" -> result = firstOperand - secondOperand;
            case "x" -> result = firstOperand * secondOperand;
            case "/" -> {
                if (secondOperand == 0) {
                    display.setText("Error");
                    operator = null;
                    startNewNumber = true;
                    return;
                }
                result = firstOperand / secondOperand;
            }
            default -> {
                return;
            }
        }

        display.setText(
                String.valueOf(result).replace('.', ',')
        );

        operator = null;
        startNewNumber = true;
    }

}
