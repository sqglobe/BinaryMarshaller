package org.binarymarshaller.reflect;

import java.lang.reflect.Method;
import org.binarymarshaller.reflect.invokers.Invoker;

public interface MethodProxy {

    void setInvoker(Invoker invoker);

    void setMethod(Method method);

    void setBoundary(int start, int length);
}
