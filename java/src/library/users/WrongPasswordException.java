/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.users;

/**
 *
 * @author Master
 */
public class WrongPasswordException extends Exception {
    WrongPasswordException(){}
    
    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
