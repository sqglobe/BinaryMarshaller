/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *
 * @author nikolay
 */
@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface BinaryParams {
    int type();
    int size();
    int skip() default -1;
}
