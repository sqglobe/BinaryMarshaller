/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import org.binarymarshaller.reflect.exception.MakeException;

/**
 *
 * @author nick
 */
public interface PojoBuilderFactory {
    PojoBuilder make(Class cls, int size, int skip) throws MakeException;
}
