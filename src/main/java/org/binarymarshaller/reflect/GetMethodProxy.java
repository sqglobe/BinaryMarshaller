package org.binarymarshaller.reflect;

import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

public interface GetMethodProxy extends MethodProxy{
    void callGet(Object obj, ByteBuffer data) throws CallException;
}
