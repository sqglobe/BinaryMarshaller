/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.exception;

/**
 *
 * @author nick
 */
public class InitException extends Exception{
    public InitException(Throwable th){
        super(th);
    }
    
    public InitException(String m){
        super(m);
    }
    
    public InitException(String m, Throwable th){
        super(m, th);
    }
}