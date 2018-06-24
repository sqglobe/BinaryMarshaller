package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Method;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.MethodProxy;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.MethodProxyFactory;
import org.binarymarshaller.reflect.SetMethodProxy;

public class MethodProxyFactoryImpl implements MethodProxyFactory{
    
    private void init(MethodProxy proxy, Invoker invoker, Method method, int start, int length){
       proxy.setInvoker(invoker);
       proxy.setBoundary(start, length);
       proxy.setMethod(method);
    }

    @Override
    public SetMethodProxy getMethodSet(Invoker invoker, Method method, int start, int length) {
        SetMethodProxy wrapper = new SetMethodProxyImpl();
        init(wrapper, invoker, method, start, length);
        return wrapper;
    }

    @Override
    public GetMethodProxy getMethodGet(Invoker invoker, Method method, int start, int length) {
        GetMethodProxy wrapper = new GetMethodProxyImpl();
        init(wrapper, invoker, method, start, length);
        return wrapper;
    }
    
}
