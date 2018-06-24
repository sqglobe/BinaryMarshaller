package org.binarymarshaller.reflect;

import java.nio.ByteBuffer;
import org.binarymarshaller.reflect.exception.CallException;

public interface SetMethodProxy extends MethodProxy {

    void callSet(Object obj, ByteBuffer data) throws CallException;
}
