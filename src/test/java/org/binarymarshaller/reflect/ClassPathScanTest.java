/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.reflect;

import org.binarymarshaller.reflect.exception.InitException;
import org.junit.Test;

/**
 *
 * @author nick
 */
public class ClassPathScanTest {
    
    @Test(expected = InitException.class)
    public void test_scan_path_with_pojo_that_has_gaps_Expected_exception() throws InitException{
        (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.invalid.gaps");
    }
    
    @Test(expected = InitException.class)
    public void test_scan_path_with_pojo_that_hasnt_get_and_set_methods_Expected_excpeton() throws InitException{
        (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.invalid.missingmethods");
    }
    
    
    
    @Test(expected = InitException.class)
    public void test_scan_path_with_class_that_contains_private_get_methods() throws InitException{
        (new ResolverInitialisator()).init("org.binarymarshaller.testutils.research.invalid.privatemethods");
    }
}
