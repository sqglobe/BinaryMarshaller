package org.binarymarshaller.reflect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.binarymarshaller.annotations.BinaryParams;
import org.binarymarshaller.reflect.exception.InitException;
import org.binarymarshaller.reflect.exception.MakeException;
import org.reflections.Reflections;

public class ClassResolver {

    private final Map<Integer, PojoBuilder> mWrappers = new HashMap<>();

    public ClassResolver(String pack, PojoBuilderFactory factory) throws InitException {
        Reflections reflections = new Reflections(pack);
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(BinaryParams.class);
        fill(annotated, factory);
    }

    public PojoBuilder getWrapper(Integer type) {
        return mWrappers.get(type);
    }

    private void fill(Set<Class<?>> annotated, PojoBuilderFactory factory) throws InitException {
        for (Class<?> cls : annotated) {
            BinaryParams param = cls.getAnnotation(BinaryParams.class);
            try {
                if(mWrappers.containsKey(param.type())){
                    throw new InitException(String.format("Duplicated with key: %d", param.type()));
                }
                mWrappers.put(param.type(), factory.make(cls, param.size(), param.skip()));
            } catch (MakeException | SecurityException ex) {
                throw new InitException(ex);
            }
        }
    }
}
