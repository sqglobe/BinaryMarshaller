package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.MethodExtractor;
import org.binarymarshaller.reflect.MethodProxy;
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
        BitSet getPositions = new BitSet(size), setPositions = new BitSet(size);

        for (Field field : fields) {
            if (field.isAnnotationPresent(BinaryParam.class)) {
                String name = field.getName();
                BinaryParam p = field.getAnnotation(BinaryParam.class);
                SetMethodProxy set = mExtractor.getSetProxy(cls, name, p);
                GetMethodProxy get = mExtractor.getGetProxy(cls, name, p);
                if (null == get && null == set) {
                    throw new MakeException("Dnt defined nor set or get method");
                }
                
                addMethod(getMethods, get, getPositions, p, size);
                addMethod(setMethods, set, setPositions, p, size);
                
            }
        }
        checkFilling(getPositions, setPositions, size, skip, cls.getName());
        
        return new PojoBuilder(cls, setMethods, getMethods, size);
    }
    
    private <T extends MethodProxy> void addMethod(List<T> methods, T method,  BitSet positions, BinaryParam p, int size) throws MakeException{
         if (null != method) {
            methods.add(method);
            fillUserPositions(positions, p.begin(), p.length(), size);
         }
    }


    private void fillUserPositions(BitSet positions, int begin, int length, int size) throws MakeException {
        if (begin < 0 || length < 0 || (begin + length) > size || positions.cardinality() != begin) {
            throw new MakeException(String.format("Invalid begin/length parameters. Begin: %d, lenth: %d, byte array length: %d", begin, length, size));
        }
        
        positions.set(begin, begin + length);
    }

    private boolean isPositionFilled(BitSet positions, int size, int skip) {

        int watershed = skip < 0 ? size : skip;
        return positions.length() == 0 || ( positions.length() == watershed && positions.cardinality() == watershed);
        
    }
    
    private void checkFilling(BitSet getPositions, BitSet setPositions, int size, int skip, String name) throws MakeException{
        if (!isPositionFilled(getPositions, size, skip)) {
            throw new MakeException("Get methods for class " + name + " not cover full byte array");
        }
        
        if (!isPositionFilled(setPositions, size, skip)) {
            throw new MakeException("Set methods for class " + name + " not cover full byte array");
        }
        
    }

}
