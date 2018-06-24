/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.invokers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

/**
 *
 * @author nikolay
 */
public class ByteInvoker implements Invoker {

    @Override
    public void invokeSet(Object obj, Method set, ByteBuffer arr, int start, int size) throws CallException {
        try {
            byte p = arr.get(start);
            set.invoke(obj, p);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

    @Override
    public boolean isYour(Type cls) {
        return cls.equals(Byte.TYPE);
    }

    @Override
    public boolean isOk(int length) {
        return length == 1;
    }

    @Override
    public void invokeGet(Object obj, Method get, ByteBuffer arr, int start, int size) throws CallException {
        try {
            byte res = (Byte)get.invoke(obj);
            arr.put(start, res);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new CallException(ex);
        }
    }

}
