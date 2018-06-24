package org.binarymarshaller.reflect.invokers;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

public interface Invoker {

    void invokeSet(Object obj, Method set, ByteBuffer arr, int start, int size) throws CallException;

    void invokeGet(Object obj, Method get, ByteBuffer arr, int start, int size) throws CallException;

    boolean isYour(Type cls);

    boolean isOk(int length);
}
