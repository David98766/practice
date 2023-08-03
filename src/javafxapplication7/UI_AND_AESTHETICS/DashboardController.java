/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication7.UI_AND_AESTHETICS;

import DataAccess1.DataAccessMethods;

import Logic.Employee;
import customException.EmptyTextFieldException;
import customException.InvalidPhoneNumberException;
import customException.RecordNotFoundException;


import errorHandlingUtils.ValidationUtil;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxapplication7.CHARTS.LineCharts;
import restrictionsForObjects.textFieldRestrictions;
import restrictionsForObjects.NumberFieldRestriction;

        
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private AnchorPane anchAccounts;

    @FXML
    private AnchorPane anchDashboard;

    @FXML
    private AnchorPane anchManageUsers;

    @FXML
    private AnchorPane anchStaff;

    @FXML
    private AnchorPane anchStatistics;

    @FXML
    private AnchorPane anchTimetable;
    
    @FXML
    private AnchorPane DashboardAnchorPane;
    
    @FXML
    private Button btnAccounts;

    @FXML
    private Button btnDashboard;


    @FXML
    private Button btnManageUsers;

    @FXML
    private Button btnStaff;

    @FXML
    private Button btnStatistics;

    @FXML
    private Button btnTimetable;
    
    @FXML
    private Label lblTitle;
    
    @FXML
    private Label lblUser;
    
    //The String and Number within the angle brackets (<>) indicate the types of the x-axis and y-axis values of the line chart respectively. In this case, the x-axis values are of type String, and the y-axis values are of type Number.
    @FXML
    private LineChart<String, Number> chartDashboardLineChart;
      
   @FXML
    private TableView<Employee> tblDataView;
    @FXML
    private AnchorPane anchSearch;
    @FXML
    private AnchorPane anchUpdate;

    @FXML
    private AnchorPane anchDelete;
    @FXML
    private AnchorPane anchCreate;

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnCreateExecute;
    
    @FXML
    private TextField txtPasswordCreate;
        @FXML
    private TextField txtFnameCreate;

    @FXML
    private TextField txtLnameCreate;

    @FXML
    private TextField txtPhoneNumberCreate;

    @FXML
    private TextField txtHourlyRateCreate;

    @FXML
    private ChoiceBox<String> chxAdminPriviledge;
    @FXML
    private TextField txtEmployeeIDDelete;

    @FXML
    private TextField txtLNameDelete;

    @FXML
    private TextField txtFNameDelete;
    @FXML
    private Button btnExecuteDelete;
    @FXML
    private TextField txtUpdateFname;

    @FXML
    private TextField txtUpdateLname;

    @FXML
    private TextField txtUpdatePhoneNumber;

    @FXML
    private TextField txtUpdateHourlyRate;

     @FXML
    private ChoiceBox<String> chxUpdatePosition;

    @FXML
    private TextField txtUpdatePassword;

    @FXML
    private Button btnExecuteUpdate;

    @FXML
    private AnchorPane anchUpdateSearch;

    @FXML
    private TextField txtSearchUpdate;
    @FXML
    private AnchorPane anchUpdateFields;
    @FXML
    private Button btnUpdateFind;

    
    String[] names = DataAccessMethods.whoLoggedIn();
    
    Employee employee = new Employee();
  
    textFieldRestrictions TextFieldRestritions = new textFieldRestrictions();
    NumberFieldRestriction numberFieldRestriction = new NumberFieldRestriction();
    
    // Create an instance of LineCharts class
    LineCharts LC = new LineCharts();
    PopupsClass PC = new PopupsClass();
    
    Stage stage;
    FormManager formManager;
    
    String errorMessage = "";
    LocalDate today = LocalDate.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        // Enable draggable behavior on the form
        //TableColumn<Item, String> nameColumn = new TableColumn<>("Employee ID");
        //nameColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TextFieldRestritions.onlyLetters(txtFnameCreate, txtLnameCreate, txtPasswordCreate, txtUpdateFname, txtUpdateLname, txtUpdatePhoneNumber, txtUpdatePassword, txtLNameDelete, txtFNameDelete);
        numberFieldRestriction.onlyNumbersPhoneNumber("0123456789\\[\\]()", txtPhoneNumberCreate, txtUpdatePhoneNumber);
        numberFieldRestriction.onlyNumbers(txtHourlyRateCreate, txtUpdateHourlyRate);
        DataAccessMethods.populateTableViewFromDatabase(tblDataView);
        lblUser.setText("Welcome: " + names[0] + " " +  names[1]);
        DataAccessMethods.counterMemory();
       


        anchStatistics.setVisible(false);
        anchTimetable.setVisible(false);
        anchStaff.setVisible(false);
        anchManageUsers.setVisible(false);
        anchAccounts.setVisible(false);
        anchDashboard.setVisible(true);
        anchUpdateFields.setVisible(false);
        
        chxAdminPriviledge.getItems().add("Manager");
        chxAdminPriviledge.getItems().add("Employee");
        
        chxUpdatePosition.getItems().add("Manager");
        chxUpdatePosition.getItems().add("Employee");
        Platform.runLater(() -> {
            FormUtils.enableDraggable(getStage(), DashboardAnchorPane);
        });
        
        // Call the initializeLineChart method and pass the line chart as an argument
        LC.generateDashboardLineChart(chartDashboardLineChart);
    }    
        private Stage getStage() {
        return (Stage) DashboardAnchorPane.getScene().getWindow();
    }

        public void exit(ActionEvent event){
        DataAccessMethods.logOut(names[0], names[1]);
        
        // Get the current stage from any node in the scene
        stage = (Stage) DashboardAnchorPane.getScene().getWindow();

        // Initialize the FormManager after obtaining the stage
        formManager = new FormManager(stage);
        formManager.switchToForm("LoginForm");
    }
      
                 public void switchScene(ActionEvent event){
      
          if (event.getSource() == btnDashboard){
              anchDashboard.setVisible(true);
              anchAccounts.setVisible(false);
              anchManageUsers.setVisible(false);
              anchStaff.setVisible(false);
              anchStatistics.setVisible(false);
              anchTimetable.setVisible(false);
              lblTitle.setText("Dashboard");
              
              
          } 
          else if (event.getSource() == btnTimetable) {
              anchDashboard.setVisible(false);
              anchAccounts.setVisible(false);
              anchManageUsers.setVisible(false);
              anchStaff.setVisible(false);
              anchStatistics.setVisible(false);
              anchTimetable.setVisible(true);
              lblTitle.setText("Timetable");
          }
          else if (event.getSource() == btnStaff){
              anchDashboard.setVisible(false);
              anchAccounts.setVisible(false);
              anchManageUsers.setVisible(false);
              anchStaff.setVisible(true);
              anchStatistics.setVisible(false);
              anchTimetable.setVisible(false);
              lblTitle.setText("Staff");
              
          }
          else if (event.getSource() == btnManageUsers){
              anchDashboard.setVisible(false);
              anchAccounts.setVisible(false);
              anchManageUsers.setVisible(true);
              anchStaff.setVisible(false);
              anchStatistics.setVisible(false);
              anchTimetable.setVisible(false);
              lblTitle.setText("Manage Users");
          }
          else if (event.getSource() == btnStatistics){
              anchDashboard.setVisible(false);
              anchAccounts.setVisible(false);
              anchManageUsers.setVisible(false);
              anchStaff.setVisible(false);
              anchStatistics.setVisible(true);
              anchTimetable.setVisible(false);
              lblTitle.setText("Statistics");
          }
        else if (event.getSource() == btnAccounts){
              anchDashboard.setVisible(false);
              anchAccounts.setVisible(true);
              anchManageUsers.setVisible(false);
              anchStaff.setVisible(false);
              anchStatistics.setVisible(false);
              anchTimetable.setVisible(false);
              lblTitle.setText("Accounts");
          }
                 }
          public void switchButton(ActionEvent event){
                        
          if (event.getSource() == btnCreate){
              anchCreate.setVisible(true);
              anchDelete.setVisible(false);
              anchSearch.setVisible(false);
              anchUpdate.setVisible(false); 
              anchUpdateSearch.setVisible(false);
              DataAccessMethods.populateTableViewFromDatabase(tblDataView);
              
          } 
          else if (event.getSource() == btnSearch) {
              anchCreate.setVisible(false);
              anchDelete.setVisible(false);
              anchSearch.setVisible(true);
              anchUpdate.setVisible(false); 
              anchUpdateSearch.setVisible(false);
              DataAccessMethods.populateTableViewFromDatabase(tblDataView);
              
          }
          else if (event.getSource() == btnUpdate){
              anchCreate.setVisible(false);
              anchDelete.setVisible(false);
              anchSearch.setVisible(false);
              anchUpdate.setVisible(true); 
              anchUpdateSearch.setVisible(true);
              anchUpdateFields.setVisible(false);
              DataAccessMethods.populateTableViewFromDatabase(tblDataView);
                 
          }
          else if (event.getSource() == btnDelete){
              anchCreate.setVisible(false);
              anchDelete.setVisible(true);
              anchSearch.setVisible(false);
              anchUpdate.setVisible(false);
              anchUpdateSearch.setVisible(false);
              DataAccessMethods.populateTableViewFromDatabase(tblDataView);
          }
          }
             public void ConfirmCreation(ActionEvent event){
                try {
                    boolean isAdmin = ValidationUtil.adminOrEmployee(chxAdminPriviledge.getValue());
                   if (!ValidationUtil.checkTextFieldsNotEmpty(anchCreate)){
                       errorMessage = "All fields must be filled in.";
                       throw new EmptyTextFieldException(errorMessage);
                   }
                   else if (!ValidationUtil.isValidPhoneNumber(txtPhoneNumberCreate.getText())){
                     errorMessage = "Phone Number is not valid."; 
                     throw new InvalidPhoneNumberException(errorMessage);
                  }
                   else {
                       Employee employeetoCreate = new Employee(DataAccessMethods.counter,
                               txtFnameCreate.getText(), txtLnameCreate.getText(),
                               txtPhoneNumberCreate.getText(),
                               Double.parseDouble(txtHourlyRateCreate.getText()), 
                               false, 
                               isAdmin, 
                               sqlDate, 
                               txtPasswordCreate.getText(), 
                               false);
                       DataAccessMethods.Create(employeetoCreate);
                       DataAccessMethods.populateTableViewFromDatabase(tblDataView);
                   }
                } catch (EmptyTextFieldException e){
                    PC.generalAlert("Text Field Error:", errorMessage);
                }
               catch (InvalidPhoneNumberException e) {
           PC.generalAlert("Phone Number Error:", errorMessage);
        }
                catch (Exception e){
                    PC.generalAlert("Unknown Error:", "Oops somethiing went wrong");
                }
                
             }
              public void ConfirmDelete(ActionEvent event){
                  try {
                  if (!ValidationUtil.checkTextFieldsNotEmpty(anchDelete)){
                      errorMessage = "All fields must be filled in";
                      throw new EmptyTextFieldException(errorMessage);
                  }
                  else {
                      DataAccessMethods.Delete(Integer.parseInt(txtEmployeeIDDelete.getText()), txtFNameDelete.getText(), txtLNameDelete.getText());
                      DataAccessMethods.populateTableViewFromDatabase(tblDataView);
                  }
                      
                  } catch (EmptyTextFieldException e){
                      PC.generalAlert("Empty Field", e.getMessage());
                  }
                       catch (RecordNotFoundException e){
                       PC.generalAlert("Empty Field", DataAccessMethods.errorMessage1);
                  }
      
              }
                 public void SearchUpdate(ActionEvent event){
                     try {
                  
                     if (!ValidationUtil.checkTextFieldsNotEmpty(anchUpdateSearch)){
                      errorMessage = "All fields must be filled in";
                      throw new EmptyTextFieldException(errorMessage);
                  }
                     
                     else {
                      DataAccessMethods.SearchEmployee(Integer.parseInt(txtSearchUpdate.getText()), tblDataView);
                      anchUpdateFields.setVisible(true);
                 }}
                     catch (EmptyTextFieldException e){
                         PC.generalAlert("Empty Field", errorMessage);
                     }
                         catch (RecordNotFoundException e){
                       PC.generalAlert("Empty Field", DataAccessMethods.errorMessage1);
                  }
                     
                 }
                 public void UpdateEmployee() {
                     try {
                     if (txtUpdateFname.getText().isEmpty() && txtUpdateLname.getText().isEmpty() && txtUpdatePhoneNumber.getText().isEmpty() && txtUpdateHourlyRate.getText().isEmpty() && chxUpdatePosition.getValue() == null && txtUpdatePassword.getText().isEmpty()){
                         errorMessage = "At least one field must be filled in.";
                         throw new EmptyTextFieldException(errorMessage);
                     }
                     else {
                         DataAccessMethods.UpdateEmployee(Integer.parseInt(txtSearchUpdate.getText()),
                                 txtUpdateFname.getText(),
                                 txtUpdateLname.getText(),
                                 txtUpdatePhoneNumber.getText(),
                                 DataAccessMethods.parseDoubleOrNull(txtUpdateHourlyRate.getText()),
                                 chxUpdatePosition.getValue(),
                                 txtUpdatePassword.getText());
                         DataAccessMethods.populateTableViewFromDatabase(tblDataView);
                     }
                     } catch (EmptyTextFieldException e){
                         PC.generalAlert("Details Error", errorMessage);
                     }
                 }

}

