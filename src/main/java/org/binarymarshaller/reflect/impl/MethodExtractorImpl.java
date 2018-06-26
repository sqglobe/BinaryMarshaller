package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.MethodExtractor;
import org.binarymarshaller.reflect.MethodProxyFactory;
import org.binarymarshaller.reflect.SetMethodProxy;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.reflect.invokers.Invoker;


public class MethodExtractorImpl implements MethodExtractor {

    private final MethodProxyFactory mFactory;
    private final List<Invoker> mInvokers;

    public MethodExtractorImpl(MethodProxyFactory factory, List<Invoker> invokers) {
        mFactory = factory;
        mInvokers = invokers;
    }

    @Override
    public GetMethodProxy getGetProxy(Class cls, String name, BinaryParam p) throws MakeException {
        Method m = getMethod(cls, name, "get", 0);
        if (null == m) {
            return null;
        }
        Invoker inv = getInvoker(m.getGenericReturnType());
        if (!inv.isOk(p.length())) {
            throw new MakeException(String.format("Bad length value for method %s:%s param %s, needed  size : %d", cls.getName(), m.getName(), m.getGenericReturnType().getTypeName(), p.length()));
        }
        return mFactory.getMethodGet(inv, m, p.begin(), p.length());
    }

    @Override
    public SetMethodProxy getSetProxy(Class cls, String name, BinaryParam p) throws MakeException {

        Method m = getMethod(cls, name, "set", 1);
        if (null == m) {
            return null;
        }
        Invoker inv = getInvoker(m.getGenericParameterTypes()[0]);
        if (!inv.isOk(p.length())) {
            throw new MakeException(String.format("Bad length value for method %s:%s param %s, needed  size : %d", cls.getName(), m.getName(), m.getGenericParameterTypes()[0].getTypeName(), p.length()));
        }
        return mFactory.getMethodSet(inv, m, p.begin(), p.length());
    }

    private Method getMethod(Class cls, String fieldName, String prefix, int paramCount) throws MakeException {
        String search = prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        for (Method method : cls.getMethods()) {
            if (search.equals(method.getName()) && method.getGenericParameterTypes().length == paramCount) {
                return method;
            }
        }
        return null;
    }

    private Invoker getInvoker(Type cls) throws MakeException {
        for (Invoker invoker : mInvokers) {
            if (invoker.isYour(cls)) {
                return invoker;
            }
        }

        throw new MakeException("Not found invoker for type " + cls.getTypeName());

    }

}
