/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restrictionsForObjects;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.util.converter.CharacterStringConverter;

/**
 *
 * @author I586662
 */
public class textFieldRestrictions {
  public void onlyLetters(TextField... textFields) {
        Tooltip tooltip = new Tooltip("Only letters (A-Z or a-z) are allowed.");

        for (TextField textField : textFields) {
            textField.setOnKeyTyped(event -> {
                String newText = textField.getText() + event.getCharacter();
                if (!newText.matches("[a-zA-Z]*")) {
                    event.consume();
                    Tooltip.install(textField, tooltip);
                } else {
                    Tooltip.uninstall(textField, tooltip);
                }
            });
        }
    }
}

