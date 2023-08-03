/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package errorHandlingUtils;


import customException.InvalidPhoneNumberException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafxapplication7.UI_AND_AESTHETICS.PopupsClass; 
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ValidationUtil {
    private static PopupsClass PC = new PopupsClass();
    private static boolean isValidName;
    private static boolean isValidPhoneNumber;
    private static String errorMessage;

   
public static boolean checkTextFieldsNotEmpty(AnchorPane anchorPane) {
    for (Node node : anchorPane.getChildren()) {
        if (node instanceof TextField) {
            TextField textField = (TextField) node;
            if (textField.getText().isEmpty()) {
                return false; // Found an empty TextField, return false immediately
            }
        } else if (node instanceof ChoiceBox) {
            ChoiceBox<String> choiceBox = (ChoiceBox<String>) node;
            if (choiceBox.getValue() == null || choiceBox.getValue().isEmpty()) {
                return false; // Found an empty ChoiceBox, return false immediately
            }
        }
    }
    return true; // All TextFields and ChoiceBoxes are non-empty, return true
}

 public static Boolean isValidPhoneNumber(String input) throws InvalidPhoneNumberException {
 String regex = "^(?:\\+353|0)(?:1\\s?[89]|(?:[2-9]|4[56789]|5[0-689])\\d)\\s?\\d{6,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
}
 public static boolean adminOrEmployee(String input){
     boolean isAdmin;
     if (input == "Manager"){
         isAdmin = true;
     }
     else {
         isAdmin = false;
     }
     return isAdmin;
 }
 }
 
