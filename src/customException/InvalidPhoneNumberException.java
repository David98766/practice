/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customException;

/**
 *
 * @author I586662
 */
public class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String Message){
        super(Message);
    }
 
}

