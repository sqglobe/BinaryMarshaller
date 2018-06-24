package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.MethodExtractor;
import org.binarymarshaller.reflect.PojoBuilder;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.reflect.SetMethodProxy;
import org.binarymarshaller.reflect.PojoBuilderFactory;

public class PojoBuilderFactoryImpl implements PojoBuilderFactory {

    private final MethodExtractor mExtractor;

    public PojoBuilderFactoryImpl(MethodExtractor extractor) {
        mExtractor = extractor;
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
                SetMethodProxy set = mExtractor.getSetProxy(cls, name, p);
                GetMethodProxy get = mExtractor.getGetProxy(cls, name, p);
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

        return build(cls, setMethods, getMethods, size);
    }

    protected PojoBuilder build(Class<?> cls, List<SetMethodProxy> setProxy, List<GetMethodProxy> getProxy, int size) {
        return new PojoBuilder(cls, setProxy, getProxy, size);
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
