/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.impl.methodextractor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.reflect.impl.MethodExtractorImpl;
import org.binarymarshaller.reflect.invokers.ByteInvoker;
import org.binarymarshaller.reflect.invokers.IntegerInvoker;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.invokers.ShortInvoker;
import org.binarymarshaller.testutils.BinaryParamFactory;
import org.binarymarshaller.testutils.moc.MethodProxyFactoryMoc;
import org.binarymarshaller.testutils.research.ClassForMethodExtractResearch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author nick
 */

@RunWith(Parameterized.class)
public class MethodExtractorSuccessExtractGetMethodTest {

   
    private static final ByteInvoker byteInvoker = new ByteInvoker();
    private static final IntegerInvoker integerInvoker = new IntegerInvoker();
    private static final ShortInvoker shortInvoker = new ShortInvoker();
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
             {"shortField", "getShortField", 0, 2, shortInvoker},
             {"integerField", "getIntegerField", 1, 4, integerInvoker},
             {"byteField", "getByteField", 5, 1, byteInvoker},
           });
    }
    
    private String fieldName;
    private String methodName;
    private int begin;
    private int length;
    private Invoker invoker;
    
    private MethodExtractorImpl testThis;
    private MethodProxyFactoryMoc mocFactory;
  
   
    public MethodExtractorSuccessExtractGetMethodTest(String fieldName, String methodName, int begin, int length, Invoker invoker) {
        this.fieldName = fieldName;
        this.methodName = methodName;
        this.begin = begin;
        this.length = length;
        this.invoker = invoker;
        
        List<Invoker> invokers = new ArrayList<>();
        invokers.add(byteInvoker);
        invokers.add(integerInvoker);
        invokers.add(shortInvoker);

        mocFactory = new MethodProxyFactoryMoc();

        testThis = new MethodExtractorImpl(mocFactory, invokers);
    }
    
    @Test
    public void test() throws NoSuchMethodException, MakeException{
        Class cls = ClassForMethodExtractResearch.class;
        Method m = cls.getDeclaredMethod(methodName);
        BinaryParam param = BinaryParamFactory.bild(begin, length);

        testThis.getGetProxy(cls, fieldName, param);
        
        Assert.assertEquals(invoker, mocFactory.invoker);
        Assert.assertEquals("getMethodGet", mocFactory.calledMethod);
        Assert.assertEquals(begin, mocFactory.start);
        Assert.assertEquals(length, mocFactory.length);
        Assert.assertEquals(m, mocFactory.method);
    }
     
     
}
