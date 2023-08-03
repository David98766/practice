/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package javafxapplication7.UI_AND_AESTHETICS;

import DataAccess1.DataAccessMethods;
import Logic.TransactionTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import DataAccess1.DataAccessMethods;
import Logic.TransactionTimer;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployeeInterfaceController implements Initializable {

@FXML
private AnchorPane anchEmployeeInterface; 
@FXML
private AnchorPane anchorpaneEmployeeInterfaceMiddle;
@FXML
private HBox hboxEmployeeInterfaceTop;
@FXML
private HBox hboxEmployeeInterfaceBottom;
@FXML
private Label lblEmployeeName;
@FXML
private Label lblRunningTimer;
@FXML
private Label lblTransactionType;
@FXML
private Label lblCustomerType;
@FXML
private Label lblPrice;
@FXML
private Label lblConfirmPayment;
@FXML
private TextField txtPrice;
@FXML 
private ChoiceBox chxTransactionType;
@FXML
private ChoiceBox chxCustomerType;
@FXML
private ChoiceBox<String> chxConfirmPayment;
@FXML
private Button btnNewTransaction;
@FXML
private Button btnCancelTransaction;
@FXML
private Button btnFinishTransaction;
@FXML
private Button btnBegin;
@FXML
private Button btnClockIn;
@FXML
private Button btnClockOut;
@FXML
private Button btnLogOut;
@FXML
private Button btnViewMyStats;

        
    String[] names = DataAccessMethods.whoLoggedIn();
    
    Stage stage;
    FormManager formManager;
    private TransactionTimer transactiontimer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(() -> {
            FormUtils.enableDraggable(getStage(), anchEmployeeInterface);
        });
        
        lblEmployeeName.setText(names[0] + " " + names[1]);
        
        //TransactionTimer Code
        
        transactiontimer = TransactionTimer.getInstance(lblRunningTimer); // Get the singleton instance of TransactionTimer
        
        
        /*Disabling appropriate objects for form initialisation*/
        
        //Disabling Middle area
        for(Node middleNode : anchorpaneEmployeeInterfaceMiddle.getChildren()){
            middleNode.setDisable(true);
            btnNewTransaction.setDisable(false);
            btnViewMyStats.setDisable(false);
            lblRunningTimer.setDisable(false);
        }
        
        //Disabling Bottom Area
        for(Node bottomNode : hboxEmployeeInterfaceBottom.getChildren()){
            bottomNode.setDisable(true);
        }
        
        //Populating chxConfirmPayment
        chxConfirmPayment.getItems().addAll("Yes", "No");

        
        //Setting the value to no so Employee has to actively select yes to confirm full payment
        chxConfirmPayment.setValue("No");

        //Setting the actions for different buttons
       
        btnNewTransaction.setOnAction(event ->{
            for(Node middleNode : anchorpaneEmployeeInterfaceMiddle.getChildren()){
                middleNode.setDisable(false);
                btnNewTransaction.setDisable(true);
                btnViewMyStats.setDisable(true);
            }
            
        }); 
        
        btnCancelTransaction.setOnAction(event ->{
            for(Node middleNode : anchorpaneEmployeeInterfaceMiddle.getChildren()){
                middleNode.setDisable(true);
                btnNewTransaction.setDisable(false);
                btnViewMyStats.setDisable(false);
            }  
            
            for(Node bottomNode : hboxEmployeeInterfaceBottom.getChildren()){
                bottomNode.setDisable(true);
            }
            transactiontimer.stop(); // Stop the stopwatch
        });
        
        btnBegin.setOnAction(event ->{
            for(Node middleNode : anchorpaneEmployeeInterfaceMiddle.getChildren()){
                middleNode.setDisable(true);
                btnCancelTransaction.setDisable(false);
                lblRunningTimer.setDisable(false);
                
            }
            
            chxConfirmPayment.setDisable(false);
           
            // Set the action for the "Begin" button to start the stopwatch
            transactiontimer.start();
        });
        
        btnFinishTransaction.setOnAction(event ->{
            for(Node bottomNode : hboxEmployeeInterfaceBottom.getChildren()){
                bottomNode.setDisable(true);
                btnCancelTransaction.setDisable(true);
                btnNewTransaction.setDisable(false);
                btnViewMyStats.setDisable(false);
            }
            //Setting the value to no so Employee has to actively select yes to confirm full payment
            chxConfirmPayment.setValue("No");
            
            transactiontimer.stop(); // Stop the stopwatch
            System.out.println(lblRunningTimer.getText()); // Print the stopwatch value to the console
        });
        
    // Add a listener to chxConfirmPayment to enable/disable btnFinishTransaction
    chxConfirmPayment.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null && newValue.equals("Yes")) {
            btnFinishTransaction.setDisable(false);
        } else {
            btnFinishTransaction.setDisable(true);
        }
    });
    
    }
        /*------------------------End of Initializaable-----------------------------*/
    
    private Stage getStage() {
        return (Stage) anchEmployeeInterface.getScene().getWindow();
    }
    
    public void EmployeeInterfaceLogOut(ActionEvent event){
        DataAccessMethods.logOut(names[0], names[1]);
        
        // Get the current stage from any node in the scene
        stage = (Stage) anchEmployeeInterface.getScene().getWindow();
        
        // Initialize the FormManager after obtaining the stage
        formManager = new FormManager(stage);
        formManager.switchToForm("LoginForm");
    }
    

}



