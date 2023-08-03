/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.sql.Date;

/**
 *
 * @author I586662
 */
public class Employee {
    
    private int employeeID;
    private String fName;
    private String lName;
    private String ePhoneNumber;
    private double hourlyRate;
    private boolean loginStatus;
    private boolean employeeAdmin;
    private Date startDate;
    private String ePassword;
    private boolean clockedIN;

    
      public Employee(){
          
      }
    
      public Employee(int employeeID, String fName, String lName, String ePhoneNumber, double hourlyRate, boolean loginStatus, boolean employeeAdmin, Date startDate, String ePassword, boolean clockedIN) {
        this.employeeID = employeeID;
        this.fName = fName;
        this.lName = lName;
        this.ePhoneNumber = ePhoneNumber;
        this.hourlyRate = hourlyRate;
        this.loginStatus = loginStatus;
        this.employeeAdmin = employeeAdmin;
        this.startDate = startDate;
        this.ePassword = ePassword;
        this.clockedIN = clockedIN;
    }
     

    public int getID(){
        return this.employeeID;
    }
    public void setID(int emloyeeID){
        this.employeeID = emloyeeID;
    }
       public String getFirstName(){
        return this.fName;
    }
    public void setFirstName(String fName){
        this.fName = fName;
    }
    
    public String getLastName(){
        return this.lName;
    }
    public void setLastName(String lName){
        this.lName = lName;
    }
    
    public String getPhoneNumber(){
        return this.ePhoneNumber;
    }
    public void setPhoneNumber(String ePhoneNumber){
        this.ePhoneNumber = ePhoneNumber;
    }
     public double getHourlyRate(){
        return this.hourlyRate;
    }
    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }
       public boolean getLoginStatus(){
        return this.loginStatus;
    }
    public void setLoginStatus(boolean loginStatus){
        this.loginStatus = loginStatus;
    }
          public boolean getEmployeeAdmin(){
        return this.employeeAdmin;
    }
    public void setEmployeeAdmin(boolean employeeAdmin){
        this.employeeAdmin = employeeAdmin;
    }
      public Date getStartDate(){
        return this.startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    
       public String getPassword(){
        return this.ePassword;
    }
    public void setPassword(String ePassword){
        this.ePassword = ePassword;
    }
    public boolean getClockIN(){
        return this.clockedIN;
    }
    public void setClockIN(boolean clockedIN){
        this.clockedIN = clockedIN;
    }
    
}