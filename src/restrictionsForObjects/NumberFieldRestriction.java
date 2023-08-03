/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restrictionsForObjects;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.util.converter.DoubleStringConverter;

/**
 *
 * @author I586662
 */
public class NumberFieldRestriction {
    public void onlyNumbersPhoneNumber(String allowedCharacters, TextField... textFields) {
        Tooltip tooltip = new Tooltip("Only numbers (0-9) are allowed.");
        
        for (TextField textField : textFields) {
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("[" + allowedCharacters + "]*")) {
                    textField.setText(oldValue);
                }
            });
            textField.setTooltip(tooltip);
        }
    }

public void onlyNumbers(TextField... text) {
    Tooltip tooltip = new Tooltip("Only numbers (0-9) and decimals are allowed.");
    for (TextField textField : text) {
        textField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter(), null, change ->
                change.getControlNewText().matches("[\\d.]*") ? change : null
        ));
        textField.setTooltip(tooltip);
    }
}
}
