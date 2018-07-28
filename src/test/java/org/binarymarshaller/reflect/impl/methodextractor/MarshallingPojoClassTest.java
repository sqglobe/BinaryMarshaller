/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect.impl.methodextractor;

import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.ClassResolver;
import org.binarymarshaller.reflect.PojoBuilder;
import org.binarymarshaller.reflect.ResolverInitialisator;
import org.binarymarshaller.reflect.exception.CallException;
import org.binarymarshaller.reflect.exception.InitException;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.testutils.research.valid.ValidPojoClass;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author nick
 */
public class MarshallingPojoClassTest {
    
    @Test
    public void test_valid_pojo_class_marshalling_Expected_no_error_on_extract() throws InitException, MakeException, CallException{
        
        byte isok = (byte)0xff;
        short length = (short)0x12a1;
        int type = 0x015f1a12;
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoClass> wrapper = resolver.getWrapper(0x0000);
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        ValidPojoClass res = wrapper.make(buffer);
        
        Assert.assertEquals(res.getIsok(), isok );
        Assert.assertEquals(res.getLength(), length );
        Assert.assertEquals(res.getType(), type);
    }
}
