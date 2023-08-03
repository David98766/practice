/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess1;

import Logic.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxapplication7.UI_AND_AESTHETICS.PopupsClass;
import static javafxapplication7.UI_AND_AESTHETICS.PopupsClass.generalAlert;
import customException.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author I586662
 */
public class DataAccessMethods {
     
         public static String errorMessage1;
         private static String dbURL = "jdbc:derby://localhost:1527/BarberShop;create=true;user=Barber;password=lemon";
         public static int counter;
         
        public static boolean DoLogin(int employeeID, String password){
           
    try (Connection conn = JDBConnection.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TBLEMPLOYEE WHERE EMPLOYEEID = ? AND EPASSWORD = ?");
        stmt.setInt(1, employeeID);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("EMPLOYEEID");
          

            PreparedStatement stmt2 = conn.prepareStatement("UPDATE TBLEMPLOYEE SET LOGINSTATUS = ? WHERE EMPLOYEEID = ?");
            stmt2.setBoolean(1, true);
            stmt2.setInt(2, id);
            int rowsAffected = stmt2.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        }

        return false;
    }catch (SQLException e) {
           
            PopupsClass.noDatabaseConnection();
            return false;
    }
    }
    public static boolean checkColumnValue(int employeeID) {
    try (Connection conn = JDBConnection.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement("SELECT EMPLOYEEADMIN FROM TBLEMPLOYEE WHERE EMPLOYEEID = ?");
        stmt.setInt(1, employeeID);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            boolean columnValue = rs.getBoolean("EMPLOYEEADMIN");
            return columnValue;
        }
          
          

        return false;
    } catch (SQLException e) {
        PopupsClass.noDatabaseConnection();
        return false;
    }
}
  public static String[] whoLoggedIn(){
      String[] names = new String[2];
      
      try(Connection conn = JDBConnection.getConnection()){
          PreparedStatement stmt = conn.prepareStatement("SELECT FNAME, LNAME FROM TBLEMPLOYEE WHERE LOGINSTATUS = true");
          ResultSet rs = stmt.executeQuery();
          
          if (rs.next()) {
              names[0] = rs.getString("FNAME");
              names[1] = rs.getString("LNAME");
              
          }
          
      } catch (SQLException e) {
          PopupsClass.noDatabaseConnection();
      }
      return names;
  }
  
  
  public static void logOut(String Fname, String Lname){
 
      
  try (Connection conn = JDBConnection.getConnection();
     PreparedStatement stmt = conn.prepareStatement("UPDATE TBLEMPLOYEE SET LOGINSTATUS = ? WHERE FNAME = ? AND LNAME = ?")) {

    stmt.setBoolean(1, false);
    stmt.setString(2, Fname);
    stmt.setString(3, Lname);

    stmt.executeUpdate();

} catch (SQLException e) {
    PopupsClass.noDatabaseConnection();
}
        
  }
  public static void counterMemory(){
      try{
          Connection conn =JDBConnection.getConnection();
         Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(EMPLOYEEID) FROM TBLEMPLOYEE");
            if (resultSet.next()) {
                int maxID = resultSet.getInt(1);
                counter = maxID + 1; // Set the counter to the next available ID
            } else {
                counter = 1; // If the table is empty, start the counter from 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          
      
    }
  
  public static Boolean Create(Employee employee){
  
  try {

        Connection conn = JDBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO TBLEMPLOYEE (EMPLOYEEID, FNAME, LNAME, EPHONENUMBER, HOURLYRATE, LOGINSTATUS, EMPLOYEEADMIN, STARTDATE, EPASSWORD, CLOCKEDIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, employee.getID());
        stmt.setString(2, employee.getFirstName());
        stmt.setString(3, employee.getLastName());
        stmt.setString(4, employee.getPhoneNumber());
        stmt.setDouble(5, employee.getHourlyRate());
        stmt.setBoolean(6, employee.getLoginStatus());
        stmt.setBoolean(7, employee.getEmployeeAdmin());
        stmt.setDate(8, employee.getStartDate());
        stmt.setString(9, employee.getPassword());
        stmt.setBoolean(10, employee.getClockIN());
   
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return true;
    } catch (SQLException sqlExcept) {
        sqlExcept.printStackTrace();
        return false;
    }
  }
 public static void Delete(int ID, String Fname, String Lname) throws RecordNotFoundException {
    String errorMessage1 = "Record with ID " + ID + ", First Name " + Fname + ", and Last Name " + Lname + " not found.";
    try {
        java.sql.Connection conn = JDBConnection.getConnection();

        String sql = "DELETE FROM TBLEMPLOYEE WHERE EMPLOYEEID = ? AND FNAME = ? AND LNAME = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, ID);
        preparedStatement.setString(2, Fname);
        preparedStatement.setString(3, Lname);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected == 0) {
            throw new RecordNotFoundException(errorMessage1);
        }

        conn.close();
    } catch (SQLException e) {
        generalAlert("Delete Error:", e.toString());
    }
    catch (RecordNotFoundException e){
        generalAlert("Not Found", errorMessage1);
    }
  
}
 
public static void UpdateEmployee(int ID, String Fname, String Lname, String phoneNumber, Double hourlyRate, String employeeAdmin, String password) {
    boolean isUpdateFname = !Fname.isEmpty();
    boolean isUpdateLname = !Lname.isEmpty();
    boolean isUpdatePhoneNumber = !phoneNumber.isEmpty();
    boolean isUpdateHourlyRate = hourlyRate != null;
    boolean isUpdateemployeeAdmin = employeeAdmin != null;
    boolean isUpdatePassword = !password.isEmpty();

    StringBuilder sql = new StringBuilder("UPDATE TBLEMPLOYEE SET ");
    if (isUpdateFname) {
        sql.append("FNAME = ? ");
    }
    if (isUpdateLname) {
        if (isUpdateFname) {
            sql.append(",");
        }
        sql.append("LNAME = ? ");
    }
    if (isUpdatePhoneNumber) {
        if (isUpdateFname || isUpdateLname) {
            sql.append(",");
        }
        sql.append("EPHONENUMBER = ? ");
    }
    if (isUpdateHourlyRate) {
        if (isUpdatePhoneNumber || isUpdateFname || isUpdateLname) {
            sql.append(",");
        }
        sql.append("HOURLYRATE = ? ");
    }
    if (isUpdateemployeeAdmin) {
        if (isUpdateHourlyRate || isUpdatePhoneNumber || isUpdateFname || isUpdateLname) {
            sql.append(",");
        }
        sql.append("EMPLOYEEADMIN = ? ");
    }
    if (isUpdatePassword) {
        if (isUpdateemployeeAdmin || isUpdateHourlyRate || isUpdatePhoneNumber || isUpdateFname || isUpdateLname) {
            sql.append(",");
        }
        sql.append("EPASSWORD = ? ");
    }

    // Add the WHERE clause after all the columns to be updated
    sql.append("WHERE EMPLOYEEID = ?");

    try {
        Connection conn = JDBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());

        int parameterIndex = 1;
        if (isUpdateFname) {
            preparedStatement.setString(parameterIndex++, Fname);
        }
        if (isUpdateLname) {
            preparedStatement.setString(parameterIndex++, Lname);
        }
        if (isUpdatePhoneNumber) {
            preparedStatement.setString(parameterIndex++, phoneNumber);
        }
        if (isUpdateHourlyRate) {
            preparedStatement.setDouble(parameterIndex++, hourlyRate);
        }
        if (isUpdateemployeeAdmin) {
            preparedStatement.setString(parameterIndex++, employeeAdmin);
        }
        if (isUpdatePassword) {
            preparedStatement.setString(parameterIndex++, password);
        }

        preparedStatement.setInt(parameterIndex, ID);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Successfully updated " + rowsAffected + " rows.");
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
  
public static void populateTableViewFromDatabase(TableView<Employee> tableView) {
    // Clear existing columns
    tableView.getColumns().clear();

    try {
        // Connect to the Derby database
        Connection connection = DriverManager.getConnection(dbURL);

        // Retrieve data from the database
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM TBLEMPLOYEE");

        // Get ResultSetMetaData to dynamically create columns
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Create columns dynamically based on column names
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String columnLabel = metaData.getColumnLabel(i); // Get the column label (name)

            TableColumn<Employee, Object> column = new TableColumn<>(columnLabel);

            // Assign the PropertyValueFactory based on the corresponding property name
            if (columnName.equalsIgnoreCase("EMPLOYEEID")) {
                column.setCellValueFactory(new PropertyValueFactory<>("ID"));
            } else if (columnName.equalsIgnoreCase("FNAME")) {
                column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            } else if (columnName.equalsIgnoreCase("LNAME")) {
                column.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            } else if (columnName.equalsIgnoreCase("EPHONENUMBER")) {
                column.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            } else if (columnName.equalsIgnoreCase("HOURLYRATE")) {
                column.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));
            } else if (columnName.equalsIgnoreCase("LOGINSTATUS")) {
                column.setCellValueFactory(new PropertyValueFactory<>("loginStatus"));
            } else if (columnName.equalsIgnoreCase("EMPLOYEEADMIN")) {
                column.setCellValueFactory(new PropertyValueFactory<>("employeeAdmin"));
            }else if (columnName.equalsIgnoreCase("STARTDATE")) {
    column.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            }
            else if (columnName.equalsIgnoreCase("CLOCKEDIN")){
                column.setCellValueFactory(new PropertyValueFactory<>("clockIN"));
            }


 else if (columnName.equalsIgnoreCase("EPASSWORD")) {
                column.setCellValueFactory(new PropertyValueFactory<>("password"));
            }

            tableView.getColumns().add(column);
        }

        // Create an ObservableList to hold the Employee objects
        ObservableList<Employee> items = FXCollections.observableArrayList();

        // Populate the ObservableList with Employee objects retrieved from the database
        while (resultSet.next()) {
          Employee employee = new Employee();

            employee.setID(resultSet.getInt("employeeID"));
            employee.setFirstName(resultSet.getString("fName"));
            employee.setLastName(resultSet.getString("lName"));
            employee.setPhoneNumber(resultSet.getString("ePhoneNumber"));
            employee.setHourlyRate(resultSet.getDouble("hourlyRate"));
            employee.setLoginStatus(resultSet.getBoolean("loginStatus"));
            employee.setEmployeeAdmin(resultSet.getBoolean("employeeAdmin"));
            employee.setClockIN(resultSet.getBoolean("clockedIN"));
            employee.setStartDate(resultSet.getDate("StartDate"));
            employee.setPassword(resultSet.getString("ePassword"));

            items.add(employee);
        }

        // Set the items to the TableView
        tableView.setItems(items);

        // Close the database resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        // Handle any exceptions here
        e.printStackTrace();
    }
}


    
public static void SearchEmployee(int ID, TableView<Employee> tableView) throws RecordNotFoundException {
    
   errorMessage1 = "The Reocrd with ID " + ID + " Is not found";
    
    try (Connection conn = JDBConnection.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM TBLEMPLOYEE WHERE EMPLOYEEID = ?");
        stmt.setInt(1, ID);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            
      
            Employee employeeToFind = new Employee();
            employeeToFind.setID(rs.getInt("EMPLOYEEID"));
            employeeToFind.setFirstName(rs.getString("FNAME"));
            employeeToFind.setLastName(rs.getString("LNAME"));
            employeeToFind.setPhoneNumber(rs.getString("EPHONENUMBER"));
            employeeToFind.setHourlyRate(rs.getDouble("HOURLYRATE"));
            employeeToFind.setLoginStatus(rs.getBoolean("LOGINSTATUS"));
            employeeToFind.setEmployeeAdmin(rs.getBoolean("EMPLOYEEADMIN"));
            employeeToFind.setStartDate(rs.getDate("STARTDATE"));
            employeeToFind.setPassword(rs.getString("EPASSWORD"));
            employeeToFind.setClockIN(rs.getBoolean("CLOCKEDIN"));

            // Clear existing columns and items in the TableView
            tableView.getColumns().clear();
            tableView.getItems().clear();

            // Create TableColumn instances and associate them with the Employee properties
            TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Employee, String> phoneNumberColumn = new TableColumn<>("Phone Number");
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

            TableColumn<Employee, Double> hourlyRateColumn = new TableColumn<>("Hourly Rate");
            hourlyRateColumn.setCellValueFactory(new PropertyValueFactory<>("hourlyRate"));

            TableColumn<Employee, Boolean> loginStatusColumn = new TableColumn<>("Login Status");
            loginStatusColumn.setCellValueFactory(new PropertyValueFactory<>("loginStatus"));

            TableColumn<Employee, Boolean> isAdminColumn = new TableColumn<>("Is Admin");
            isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("employeeAdmin"));

            TableColumn<Employee, Date> startDateColumn = new TableColumn<>("Start Date");
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

            TableColumn<Employee, String> passwordColumn = new TableColumn<>("Password");
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

            TableColumn<Employee, Boolean> clockedInColumn = new TableColumn<>("Clocked In");
            clockedInColumn.setCellValueFactory(new PropertyValueFactory<>("clockedIn"));

            // Add the columns to the TableView
            tableView.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn,
                    phoneNumberColumn, hourlyRateColumn, loginStatusColumn, isAdminColumn,
                    startDateColumn, passwordColumn, clockedInColumn);

            // Set the found employee to the TableView
            tableView.getItems().add(employeeToFind);
            
        }
        else {
            throw new RecordNotFoundException(errorMessage1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
     /*  catch (RecordNotFoundException e){
        generalAlert("Not Found", errorMessage1);
    }*/

}

    public static Double parseDoubleOrNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        } else {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
        
}
