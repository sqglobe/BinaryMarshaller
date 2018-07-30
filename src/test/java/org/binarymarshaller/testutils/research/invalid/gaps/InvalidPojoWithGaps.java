/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils.research.invalid.gaps;

import org.binarymarshaller.annotations.BinaryParam;
import org.binarymarshaller.annotations.BinaryParams;

/**
 *
 * @author nick
 */
@BinaryParams(type = 0x0002, size = 8)
public class InvalidPojoWithGaps {
    @BinaryParam(begin = 0, length = 4)
    private int type;
    
    @BinaryParam(begin = 4, length = 1)
    private byte isok;
    
    @BinaryParam(begin = 6, length = 2)
    private short length;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte getIsok() {
        return isok;
    }

    public void setIsok(byte isok) {
        this.isok = isok;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }
    
}
