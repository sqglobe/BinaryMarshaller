package org.binarymarshaller.reflect.exception;

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
