package org.binarymarshaller.reflect.exception;


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