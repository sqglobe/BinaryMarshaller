/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.impl.methodextractor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.GetMethodProxy;
import org.binarymarshaller.reflect.SetMethodProxy;
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
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author nick
 */
public class MethodExtractorNotSuccessExtractCases {
    private static final ByteInvoker byteInvoker = new ByteInvoker();
    private static final IntegerInvoker integerInvoker = new IntegerInvoker();
    private static final ShortInvoker shortInvoker = new ShortInvoker();
    
    private MethodExtractorImpl testThis;
    private MethodProxyFactoryMoc mocFactory;
    
    @Before
    public void setup(){
        
        List<Invoker> invokers = new ArrayList<>();
        invokers.add(byteInvoker);
        invokers.add(integerInvoker);
        invokers.add(shortInvoker);

        mocFactory = new MethodProxyFactoryMoc();

        testThis = new MethodExtractorImpl(mocFactory, invokers);
        
    }
    
    @Test(expected = MakeException.class)
    public void testSetUpBadLethValueForExtractGetMethodExpectException() throws MakeException{
        
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        testThis.getGetProxy(cls, "integerField", param);

    }
    
    @Test
    public void testSetupBadFieldNameForExtractGetMethodExpectedNullResult() throws MakeException{
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        GetMethodProxy proxy = testThis.getGetProxy(cls, "testNonSetGetMethod", param);  
        
        Assert.assertNull(proxy);
    }
    
    @Test(expected = MakeException.class)
    public void testSetUpBadLethValueForExtractSetMethodExpectException() throws MakeException{
        
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        testThis.getGetProxy(cls, "shortField", param);

    }
    
    @Test
    public void testSetupBadFieldNameForExtractSrtMethodExpectedNullResult() throws MakeException{
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        SetMethodProxy proxy = testThis.getSetProxy(cls, "testNonSetGetMethod", param);  
        
        Assert.assertNull(proxy);
    }
    
    @Test(expected = MakeException.class)
    public void testTryExtractGetMethodWithBooleanReturnValueExpectException() throws MakeException{
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        testThis.getGetProxy(cls, "booleanField", param);
    }
    
    @Test(expected = MakeException.class)
    public void testTryExtractSetMethodWithBooleanParameterValueExpectException() throws MakeException{
        Class cls = ClassForMethodExtractResearch.class;
        BinaryParam param = BinaryParamFactory.bild(0, 10);

        testThis.getSetProxy(cls, "booleanField", param);
    }
    
    
}
