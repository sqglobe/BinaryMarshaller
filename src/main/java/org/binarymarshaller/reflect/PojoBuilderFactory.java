package org.binarymarshaller.reflect;

import org.binarymarshaller.reflect.exception.MakeException;

public interface PojoBuilderFactory {
    PojoBuilder make(Class cls, int size, int skip) throws MakeException;
}
