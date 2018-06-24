/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import java.lang.reflect.Method;
import org.binarymarshaller.reflect.invokers.Invoker;

/**
 *
 * @author nick
 */
public interface MethodProxyFactory {
    SetMethodProxy getMethodSet(Invoker invoker, Method method, int start, int length);
    GetMethodProxy getMethodGet(Invoker invoker, Method method, int start, int length);
}
