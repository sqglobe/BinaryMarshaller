/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

/**
 *
 * @author nick
 */
public interface SetMethodProxy extends MethodProxy{    
    void callSet(Object obj, ByteBuffer data) throws CallException;
}
