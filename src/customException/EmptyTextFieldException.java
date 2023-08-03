/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customException;

/**
 *
 * @author I586662
 */
public class EmptyTextFieldException extends Exception {
    private String errorMessage = "All Fields must be filled in";
    
    public EmptyTextFieldException(String errorMessage){
        super(errorMessage);
    }
    
       public String getErrorMessage(){
        return this.errorMessage;
    }
         public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    
 
    
    
}
