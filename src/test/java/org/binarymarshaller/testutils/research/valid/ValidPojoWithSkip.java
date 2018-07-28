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
@BinaryParams(type = 0x0001, size = 10, skip = 7)
public class ValidPojoWithSkip {
    @BinaryParam(begin = 0, length = 4)
    private int type;
    
    @BinaryParam(begin = 4, length = 1)
    private byte isok;
    
    @BinaryParam(begin = 5, length = 2)
    private short length;

    /**
     * @return the type
     */
    
    
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    
    
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the isok
     */
    public byte getIsok() {
        return isok;
    }

    /**
     * @param isok the isok to set
     */
    
    public void setIsok(byte isok) {
        this.isok = isok;
    }

    /**
     * @return the length
     */
    public short getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    
    public void setLength(short length) {
        this.length = length;
    } 
}
