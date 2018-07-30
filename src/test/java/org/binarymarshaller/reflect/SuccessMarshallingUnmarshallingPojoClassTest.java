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
import org.binarymarshaller.testutils.research.valid.ValidPojoClass;
import org.binarymarshaller.testutils.research.valid.ValidPojoWithGetMethods;
import org.binarymarshaller.testutils.research.valid.ValidPojoWithSetMethods;
import org.binarymarshaller.testutils.research.valid.ValidPojoWithSkip;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author nick
 */
public class SuccessMarshallingUnmarshallingPojoClassTest {
    
    private final byte isok = (byte)0xff;
    private final short length = (short)0x12a1;
    private final int type = 0x015f1a12;
    
    @Test
    public void test_unmarshallling_valid_pojo_class_marshalling_Expected_no_error_on_marshalling() throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoClass> wrapper = resolver.getWrapper(0x0000);
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        ValidPojoClass res = wrapper.make(buffer);
        
        Assert.assertEquals(isok, res.getIsok() );
        Assert.assertEquals(length, res.getLength() );
        Assert.assertEquals(type, res.getType());
    }
    
    @Test
    public void test_unmarshallling_valid_pojo_with_only_set_methods_Expected_no_erroros() throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithSetMethods> wrapper = resolver.getWrapper(0x0003);
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        ValidPojoWithSetMethods res = wrapper.make(buffer);
        
        Assert.assertEquals(isok, res.isok );
        Assert.assertEquals(length, res.length );
        Assert.assertEquals(type, res.type); 
    }
    
    @Test
    public void test_unmarshallling_valid_pojo_with_skip_parameter_Expected_no_errors() throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithSkip> wrapper = resolver.getWrapper(0x0001);
        ByteBuffer buffer = ByteBuffer.allocate(11);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        buffer.putInt(0xffffffff);
        ValidPojoWithSkip res = wrapper.make(buffer);
        
        Assert.assertEquals(isok, res.getIsok() );
        Assert.assertEquals(length, res.getLength() );
        Assert.assertEquals(type, res.getType());
    }
    
    @Test
    public void test_mashalling_valid_pojo_Expected_no_errors()  throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoClass> wrapper = resolver.getWrapper(0x0000);
        ValidPojoClass cls = new ValidPojoClass();
        cls.setIsok(isok);
        cls.setLength(length);
        cls.setType(type);
        
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        
        ByteBuffer res =  wrapper.pack(cls);
        
        Assert.assertArrayEquals(buffer.array(), res.array());
        
    }
    
    @Test
    public void test_marshalling_valid_pojo_with_get_methods_Expected_no_errors()  throws InitException, MakeException, CallException{
        
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithGetMethods> wrapper = resolver.getWrapper(0x0002);
        ValidPojoWithGetMethods cls = new ValidPojoWithGetMethods(type, isok, length);
        
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        
        ByteBuffer res =  wrapper.pack(cls);
        
        Assert.assertArrayEquals(buffer.array(), res.array());
    }
    
    public void test_marshalling_valid_pojo_with_skip_Expected_no_errors()  throws InitException, MakeException, CallException{
        ClassResolver resolver = (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.valid");
        PojoBuilder<ValidPojoWithSkip> wrapper = resolver.getWrapper(0x0001);
        ValidPojoWithSkip cls = new ValidPojoWithSkip();
        cls.setIsok(isok);
        cls.setLength(length);
        cls.setType(type);
        
        ByteBuffer buffer = ByteBuffer.allocate(11);
        buffer.putInt(type);
        buffer.put(isok);
        buffer.putShort(length);
        buffer.putInt(0x00000000);
        
        ByteBuffer res =  wrapper.pack(cls);
        
        Assert.assertArrayEquals(buffer.array(), res.array());
    }
    
    
    
    
}
