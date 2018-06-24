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
public class CallException extends Exception{

    public CallException(Throwable th) {
        super(th);
    }

    public CallException(String m) {
        super(m);
    }

    public CallException(String m, Throwable th) {
        super(m, th);
    }
}
