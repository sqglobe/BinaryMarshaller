/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;
import org.binarymarshaller.reflect.exception.InitException;
import org.binarymarshaller.reflect.exception.MakeException;
import org.binarymarshaller.testutils.research.invalid.invaliparams.InvalidPojoBadParameter;
import org.binarymarshaller.testutils.research.valid.ValidPojoClass;
import org.binarymarshaller.testutils.research.valid.ValidPojoWithGetMethods;
import org.binarymarshaller.testutils.research.valid.ValidPojoWithSetMethods;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author nick
 */
public class InvalidMarshallingUnmarshallingTest {
    
    private final byte isok = (byte)0xff;
    private final short length = (short)0x12a1;
    private final int type = 0x015f1a12;
    
    @Test(expected = MakeException.class)
    public void test_marshalling_very_long_buffer_into_object_Exception_expected() throws InitException, MakeException, CallException{
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoClass> wrapper = resolver.getWrapper(0x0000);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        wrapper.make(buffer);
    }
    
    
    @Test(expected = MakeException.class)
    public void test_unmarshalling_into_object_without_set_methods_Exception_expected() throws InitException, MakeException, CallException{
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithGetMethods> wrapper = resolver.getWrapper(0x0002);
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        wrapper.make(buffer);
    }

    @Test(expected = InitException.class)
    public void test_unmarshalling_into_object_with_invalid_paramter_of_set_method_Exception_expected() throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.invalid.invaliparams");
        PojoBuilder<InvalidPojoBadParameter> wrapper = resolver.getWrapper(0x0001);
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        wrapper.make(buffer);
    }
    
    @Test(expected = MakeException.class)
    public void test_mashalling_object_that_has_only_set_methods_Exception_expected() throws MakeException, CallException, InitException{
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithSetMethods> wrapper = resolver.getWrapper(0x0003);
        ValidPojoWithSetMethods obj = new ValidPojoWithSetMethods();
        obj.setIsok(isok);
        obj.setLength(length);
        obj.setType(type);
        
        wrapper.pack(obj);
    }
}
