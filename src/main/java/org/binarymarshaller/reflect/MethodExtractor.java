/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.reflect.exception.MakeException;

/**
 *
 * @author nick
 */
public interface MethodExtractor {
     GetMethodProxy getGetProxy(Class cls, String name, BinaryParam p) throws MakeException;
     SetMethodProxy getSetProxy(Class cls, String name, BinaryParam p) throws MakeException;
}
