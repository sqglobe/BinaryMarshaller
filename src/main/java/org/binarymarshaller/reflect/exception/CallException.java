package org.binarymarshaller.reflect.exception;


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
