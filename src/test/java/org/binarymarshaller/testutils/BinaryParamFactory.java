/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils;

import java.lang.annotation.Annotation;
import org.binarymarshaller.annotations.BinaryParam;

/**
 *
 * @author nick
 */
public class BinaryParamFactory {
    public static BinaryParam bild(final int begin, final int length){
        return new BinaryParam() {
            @Override
            public int begin() {
                return begin;
            }

            @Override
            public int length() {
                return length;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
}
