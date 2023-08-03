
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxapplication7.UI_AND_AESTHETICS.PopupsClass;
import javafxapplication7.UI_AND_AESTHETICS.FormManager;
import DataAccess1.DataAccessMethods;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafxapplication7.UI_AND_AESTHETICS.FormUtils;


/**
 *
 * @author I586588
 */
public class FXMLDocumentController implements Initializable {
  
  
    
    @FXML
    private Label label;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane MYANCHORPANE;
    @FXML
    private Hyperlink hypFYP;
    @FXML
    private ImageView ivBarberPole;     
    @FXML
    private Button btnExitLogin;
    @FXML
    private AnchorPane anchSignUp;
    @FXML
    private BorderPane bpLogin;
    

    PopupsClass PC = new PopupsClass();
   
//    FormManager formManager = new FormManager(stage);
    
    Stage stage;
    FormManager formManager;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image imgBarberPole = new Image("/JavaFXApplication/image/barberpole-unscreen.gif");
        ivBarberPole.setImage(imgBarberPole);
        
        //Enabling draggable
        Platform.runLater(() -> {
            FormUtils.enableDraggable(getStage(), bpLogin);
        });
       
    } 
    
    @FXML
    private Stage getStage() {
        return (Stage) bpLogin.getScene().getWindow();
    }
    
    @FXML
    public void exit(ActionEvent event){
        stage = (Stage) MYANCHORPANE.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void activatehypFYP(ActionEvent event){
        PC.forgotYourPassword();
    }
    
    @FXML
    private void handlebtnLogin(ActionEvent event )throws IOException{
        
        String strEmployeeID = txtUsername.getText();
        String strPassword = txtPassword.getText();
        boolean admin = false;
        boolean allowedLogin = false;
        
        try {
   
        allowedLogin = DataAccessMethods.DoLogin(Integer.parseInt(strEmployeeID), strPassword);
        admin = DataAccessMethods.checkColumnValue(Integer.parseInt(strEmployeeID));
        }
        catch (NumberFormatException e){
           PC.employeedIDHasToBeNumber();
        }
      
      
      //DataAccessMethods.DoLogin(username, password)
        
        // Get the current stage from any node in the scene
        stage = (Stage) MYANCHORPANE.getScene().getWindow();

        // Initialize the FormManager after obtaining the stage
        formManager = new FormManager(stage);
       if (allowedLogin && admin){
           
        formManager.switchToForm("DashboardForm");
       
        }
        else if (allowedLogin == true && admin == false){
             
         formManager.switchToForm("EmployeeInterface");
        }
        else {
              PC.incorrectDetails();
        }


         }
}
