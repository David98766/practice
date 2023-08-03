/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication7.UI_AND_AESTHETICS;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author I586588
 */
public class PopupsClass {
    
    public static void forgotYourPassword(){
        Alert alertForgotYourPassword = new Alert(Alert.AlertType.CONFIRMATION);
        alertForgotYourPassword.setTitle("Password Recovery");
        alertForgotYourPassword.setContentText("What was the name of your first dog? "
                + "If you cannot remember your password, contact Adam or David.");
        alertForgotYourPassword.show();
    }
      public static void employeedIDHasToBeNumber(){
        Alert employeeIDException = new Alert(Alert.AlertType.ERROR);
        employeeIDException.setTitle("Employee ID Error");
        employeeIDException.setContentText("Employee ID must be a number");
        employeeIDException.show();
    }
          public static void incorrectDetails(){
        Alert employeeIDException = new Alert(Alert.AlertType.ERROR);
        employeeIDException.setTitle("Details Error");
        employeeIDException.setContentText("Employee ID or Password are wrong");
        employeeIDException.show();
    }
          public static void noDatabaseConnection(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Database Error");
        alert.setHeaderText("Unable to connect to Database");
        alert.setContentText("Error Message: Unable to connect to Database");
        alert.showAndWait();
          }
        
        public static void generalAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     
}

