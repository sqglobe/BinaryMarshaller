/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.reflect.invokers.ByteInvoker;
import org.binarymarshaller.reflect.invokers.IntegerInvoker;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.invokers.ShortInvoker;
import org.binarymarshaller.testutils.BinaryParamFactory;
import org.binarymarshaller.testutils.moc.MethodProxyFactoryMoc;
import org.binarymarshaller.testutils.research.ClassForMethodExtractResearch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author nick
 */
public class MethodExtractorImplTest {

    private MethodExtractorImpl testThis;
    private MethodProxyFactoryMoc mocFactory;
    private ByteInvoker byteInvoker = new ByteInvoker();
    private IntegerInvoker integerInvoker = new IntegerInvoker();
    private ShortInvoker shortInvoker = new ShortInvoker();

    

    public MethodExtractorImplTest() {

    }

    @Before
    public void initTest() {
        List<Invoker> invokers = new ArrayList<>();
        invokers.add(byteInvoker);
        invokers.add(integerInvoker);
        invokers.add(shortInvoker);

        mocFactory = new MethodProxyFactoryMoc();

        testThis = new MethodExtractorImpl(mocFactory, invokers);
       
    }
    

    @Test
    public void test_get_GetProxy_on_byte_method_Success() throws MakeException, NoSuchMethodException {
        
        final int BEGIN = 5;
        final int LENGTH = 1;
        Class cls = ClassForMethodExtractResearch.class;
        String methodName = "byteField";
        Method m = cls.getDeclaredMethod("getByteField");
        BinaryParam param = BinaryParamFactory.bild(BEGIN, LENGTH);

        testThis.getGetProxy(cls, methodName, param);
        Assert.assertEquals(byteInvoker, mocFactory.invoker);
        Assert.assertEquals("getMethodGet", mocFactory.calledMethod);
        Assert.assertEquals(BEGIN, mocFactory.start);
        Assert.assertEquals(LENGTH, mocFactory.length);
        Assert.assertEquals(m, mocFactory.method);
        
    }

}
