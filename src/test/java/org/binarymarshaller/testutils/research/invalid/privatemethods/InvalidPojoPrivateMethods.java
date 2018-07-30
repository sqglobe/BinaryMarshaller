/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils.research.invalid.privatemethods;

import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.annotations.BinaryParams;

/**
 *
 * @author nick
 */
@BinaryParams(type = 0x0002, size = 7)
public class InvalidPojoPrivateMethods {
    @BinaryParam(begin = 0, length = 4)
    public int type;
    
    @BinaryParam(begin = 4, length = 1)
    public byte isok;
    
    @BinaryParam(begin = 5, length = 2)
    public short length;

    private void setType(int type) {
        this.type = type;
    }

    public void setIsok(byte isok) {
        this.isok = isok;
    }

    public void setLength(short length) {
        this.length = length;
    }
    
    
}
