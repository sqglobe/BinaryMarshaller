/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils.moc;

import java.lang.reflect.Method;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.MethodProxyFactory;
import org.binarymarshaller.reflect.SetMethodProxy;
import org.binarymarshaller.reflect.invokers.Invoker;

/**
 *
 * @author nick
 */
public class MethodProxyFactoryMoc implements MethodProxyFactory{

    
    public String calledMethod;
    public Invoker invoker;
    public Method method;
    public int start;
    public int length;
    
    private void set(String metodName, Invoker invoker, Method method, int start, int length){
        calledMethod = metodName;
        this.invoker = invoker;
        this.method = method;
        this.start = start;
        this.length = length;   
    }
    
    @Override
    public SetMethodProxy getMethodSet(Invoker invoker, Method method, int start, int length) {
        
        set("getMethodSet", invoker, method, start, length);
        
        return null;
    }

    @Override
    public GetMethodProxy getMethodGet(Invoker invoker, Method method, int start, int length) {
        set("getMethodGet", invoker, method, start, length);
        return null;
    }
    
}
