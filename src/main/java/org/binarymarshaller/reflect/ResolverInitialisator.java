/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import java.util.ArrayList;
import java.util.List;
import org.binarymarshaller.reflect.exception.InitException;
import org.binarymarshaller.reflect.impl.MethodProxyFactoryImpl;
import org.binarymarshaller.reflect.impl.PojoBuilderFactoryImpl;
import org.binarymarshaller.reflect.invokers.ByteInvoker;
import org.binarymarshaller.reflect.invokers.IntegerInvoker;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.invokers.ShortInvoker;

/**
 *
 * @author nick
 */
public class ResolverInitialisator {
    
    private final PojoBuilderFactory wrapperFactory;
    
    
    public ResolverInitialisator(){
        List<Invoker> invokers = new ArrayList<>();
        invokers.add(new ByteInvoker());
        invokers.add(new IntegerInvoker());
        invokers.add(new ShortInvoker());
        
        wrapperFactory = new PojoBuilderFactoryImpl(new MethodProxyFactoryImpl(), invokers);
    }
    
    public ClassResolver init(String name) throws InitException{
        return new ClassResolver(name, wrapperFactory);
    }
}
