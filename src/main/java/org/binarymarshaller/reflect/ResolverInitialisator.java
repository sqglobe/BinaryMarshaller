package org.binarymarshaller.reflect;

import java.util.ArrayList;
import java.util.List;
import org.binarymarshaller.reflect.exception.InitException;
import org.binarymarshaller.reflect.impl.MethodExtractorImpl;
import org.binarymarshaller.reflect.impl.MethodProxyFactoryImpl;
import org.binarymarshaller.reflect.impl.PojoBuilderFactoryImpl;
import org.binarymarshaller.reflect.invokers.ByteInvoker;
import org.binarymarshaller.reflect.invokers.IntegerInvoker;
import org.binarymarshaller.reflect.invokers.Invoker;
import org.binarymarshaller.reflect.invokers.ShortInvoker;

public class ResolverInitialisator {

    private final PojoBuilderFactory wrapperFactory;

    public ResolverInitialisator() {
        List<Invoker> invokers = new ArrayList<>();
        invokers.add(new ByteInvoker());
        invokers.add(new IntegerInvoker());
        invokers.add(new ShortInvoker());
        
        MethodExtractor extractor = new MethodExtractorImpl(new MethodProxyFactoryImpl(), invokers);

        wrapperFactory = new PojoBuilderFactoryImpl(extractor);
    }

    public ClassResolver init(String name) throws InitException {
        return new ClassResolver(name, wrapperFactory);
    }
}
