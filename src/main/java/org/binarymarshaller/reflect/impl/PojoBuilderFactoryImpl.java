package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.PojoBuilder;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.MethodProxyFactory;
import org.binarymarshaller.reflect.SetMethodProxy;
import org.binarymarshaller.reflect.PojoBuilderFactory;

public class PojoBuilderFactoryImpl implements PojoBuilderFactory {

    private final MethodProxyFactory mFactory;
    private final List<Invoker> mInvokers;

    public PojoBuilderFactoryImpl(MethodProxyFactory factory, List<Invoker> invokers) {
        mFactory = factory;
        mInvokers = invokers;
    }

    @Override
    public PojoBuilder make(Class cls, int size, int skip) throws MakeException {
        Field[] fields = cls.getDeclaredFields();
        List<SetMethodProxy> setMethods = new ArrayList<>();
        List<GetMethodProxy> getMethods = new ArrayList<>();
        boolean positions[] = new boolean[size];
        Arrays.fill(positions, false);
        for (Field field : fields) {
            if (field.isAnnotationPresent(BinaryParam.class)) {
                String name = field.getName();
                BinaryParam p = field.getAnnotation(BinaryParam.class);
                SetMethodProxy set = getSetProxy(cls, name, p);
                GetMethodProxy get = getGetProxy(cls, name, p);
                if (null != get) {
                    getMethods.add(get);
                }
                if (null != set) {
                    setMethods.add(set);
                }
                if (null == get && null == set) {
                    throw new MakeException("Dnt defined nor set or get method");
                }

                fillUserPositions(positions, p.begin(), p.length());

            }
        }

        if (!isPositionFilled(positions, skip)) {
            throw new MakeException("Params for class " + cls.getName() + " not cover full byte array");
        }

        return new PojoBuilder(cls, setMethods, getMethods, size);
    }

    private SetMethodProxy getSetProxy(Class cls, String name, BinaryParam p) throws MakeException {
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

    private GetMethodProxy getGetProxy(Class cls, String name, BinaryParam p) throws MakeException {
        Method m = getMethod(cls, name, "get", 0);
        if (null == m) {
            return null;
        }
        Invoker inv = getInvoker(m.getGenericReturnType());
        if (!inv.isOk(p.length())) {
            throw new MakeException(String.format("Bad length value for method %s:%s param %s, needed  size : %d", cls.getName(), m.getName(), m.getGenericParameterTypes()[0].getTypeName(), p.length()));
        }
        return mFactory.getMethodGet(inv, m, p.begin(), p.length());
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

    private void fillUserPositions(boolean[] positions, int begin, int length) throws MakeException {
        if (begin < 0 || length < 0 || (begin + length) > positions.length) {
            throw new MakeException(String.format("Invalid begin/length parameters. Begin: %d, lenth: %d, byte array length: %d", begin, length, positions.length));
        }
        for (int i = 0; i < length; i++) {
            positions[begin + i] = true;
        }
    }

    private boolean isPositionFilled(boolean positions[], int skip) {

        int watershed = skip < 0 ? positions.length : skip;
        for (int i = 0; i < positions.length; i++) {
            if ((i < watershed && !positions[i]) || (i >= watershed && positions[i])) {
                return false;
            }
        }
        return true;
    }

}
