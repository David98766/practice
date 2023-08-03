/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication7.UI_AND_AESTHETICS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author I586588
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormManager {
    private Stage stage;
    private Map<String, Scene> scenes = new HashMap<>();

    public FormManager(Stage stage) {
        this.stage = stage;

        // Example: Load and store the scenes for the forms
        loadForm("LoginForm", "/javafxapplication7/FXMLDocument.fxml");
        loadForm("DashboardForm", "/javafxapplication7/UI_AND_AESTHETICS/Dashboard.fxml");
        loadForm("EmployeeInterface", "/javafxapplication7/UI_AND_AESTHETICS/EmployeeInterface.fxml");

        // Example: Show the initial form
        //switchToForm("LoginForm");
    }

    public void loadForm(String formName, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane formContent = loader.load();
            Scene formScene = new Scene(formContent);

            scenes.put(formName, formScene);
        } catch (IOException e) {
            System.out.println("Error loading FXML for form: " + formName);
            e.printStackTrace();
        }
    }

    public void switchToForm(String formName) {
        Scene scene = scenes.get(formName);
        if (scene != null) {
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Form not found: " + formName);
        }
    }
}



