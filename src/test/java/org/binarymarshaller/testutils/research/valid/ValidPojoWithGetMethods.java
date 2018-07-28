/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils.research.valid;

import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.annotations.BinaryParams;

/**
 *
 * @author nick
 */

@BinaryParams(type = 0x0002, size = 7)
public class ValidPojoWithGetMethods {
    @BinaryParam(begin = 0, length = 4)
    private int type;
    
    @BinaryParam(begin = 4, length = 1)
    private byte isok;
    
    @BinaryParam(begin = 5, length = 2)
    private short length;

    public int getType() {
        return type;
    }

    public byte getIsok() {
        return isok;
    }

    public short getLength() {
        return length;
    }

    
}
