package org.binarymarshaller.reflect;

import java.lang.reflect.Method;
import org.binarymarshaller.reflect.invokers.Invoker;

public interface MethodProxyFactory {

    SetMethodProxy getMethodSet(Invoker invoker, Method method, int start, int length);

    GetMethodProxy getMethodGet(Invoker invoker, Method method, int start, int length);
}
