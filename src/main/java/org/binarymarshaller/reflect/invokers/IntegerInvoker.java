package org.binarymarshaller.reflect.invokers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

public class IntegerInvoker implements Invoker {

    @Override
    public void invokeSet(Object obj, Method set, ByteBuffer arr, int start, int size) throws CallException {
        try {
            int p = arr.getInt(start);
            set.invoke(obj, p);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

    @Override
    public boolean isYour(Type cls) {
        return cls.equals(Integer.TYPE);
    }

    @Override
    public boolean isOk(int length) {
        return length == 4;
    }

    @Override
    public void invokeGet(Object obj, Method get, ByteBuffer arr, int start, int size) throws CallException {
        try {
            int res = (Integer) get.invoke(obj);
            arr.putInt(start, res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

}
