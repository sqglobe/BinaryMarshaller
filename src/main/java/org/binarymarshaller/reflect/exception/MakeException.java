/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.exception;

/**
 *
 * @author nikolay
 */
public class MakeException extends Exception{
    public MakeException(Throwable th){
        super(th);
    }
    
    public MakeException(String m){
        super(m);
    }
    
    public MakeException(String m, Throwable th){
        super(m, th);
    }
}
