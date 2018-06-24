package org.binarymarshaller.reflect.invokers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

public class ShortInvoker implements Invoker {

    @Override
    public void invokeSet(Object obj, Method set, ByteBuffer arr, int start, int size) throws CallException {
        try {
            short p = arr.getShort(start);
            set.invoke(obj, p);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

    @Override
    public boolean isYour(Type cls) {
        return cls.equals(Short.TYPE);
    }

    @Override
    public boolean isOk(int length) {
        return length == 2;
    }

    @Override
    public void invokeGet(Object obj, Method get, ByteBuffer arr, int start, int size) throws CallException {
        try {
            short res = (Short) get.invoke(obj);
            arr.putShort(start, res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

}
