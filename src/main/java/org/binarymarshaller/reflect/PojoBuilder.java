/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.List;
import org.binarymarshaller.reflect.exception.CallException;
import org.binarymarshaller.reflect.exception.MakeException;

/**
 *
 * @author nikolay
 * @param <T>
 */
public class PojoBuilder<T> {
    
    private final Class<T> mGenerateClass;
    private final List<SetMethodProxy> mSetProxy;
    private final List<GetMethodProxy> mGetProxy;
    private  Constructor mConstractor = null;
    private final int mSize;   
    
    
    public PojoBuilder(Class<T> cls, List<SetMethodProxy> setProxy, List<GetMethodProxy> getProxy, int size){
        mGenerateClass = cls;
        mSetProxy = setProxy;
        mGetProxy = getProxy;
        for(Constructor cnst : mGenerateClass.getDeclaredConstructors()){
            if(cnst.getGenericParameterTypes().length == 0){
                mConstractor = cnst;
                break;
            }
        }
        
        mSize = size;
    }
    
    public T make(ByteBuffer data) throws MakeException, CallException{
        if(mSize != data.capacity()){
            throw new MakeException(String.format("Get message with capaciity(%d) not equal to defined for class (%d)", data.capacity(), mSize));
        }
        
        try {
            
            T res = (T) mConstractor.newInstance();
            for(SetMethodProxy wrapper: mSetProxy){
                wrapper.callSet(res, data);
            }
            return res;
            
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new MakeException(ex);
        }
    }
    
    public ByteBuffer pack(T obj)  throws MakeException, CallException{
        ByteBuffer buff = ByteBuffer.allocate(mSize);
        try {   
            for(GetMethodProxy wrapper: mGetProxy){
                wrapper.callGet(obj, buff);
            }
            return buff;
        } catch (SecurityException | IllegalArgumentException ex) {
            throw new MakeException(ex);
        }
    }
}
