/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.impl;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.SetMethodProxy;

/**
 *
 * @author nick
 */
public class SetMethodProxyImpl implements SetMethodProxy{

    
    private Invoker mInvoker;
    private Method mMethod;
    private int mStart;
    private int mLength;
    
    @Override
    public void setInvoker(Invoker invoker) {
        mInvoker = invoker;
    }

    @Override
    public void setMethod(Method method) {
        mMethod = method;
    }

    @Override
    public void setBoundary(int start, int length) {
        mStart = start;
        mLength = length;
    }

    @Override
    public void callSet(Object obj, ByteBuffer data) throws CallException {
        mInvoker.invokeSet(obj, mMethod, data, mStart, mLength);
    }
    
}
