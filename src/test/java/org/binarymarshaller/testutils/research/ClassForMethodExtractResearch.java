/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.binarymarshaller.testutils.research;

/**
 *
 * @author nick
 */
public class ClassForMethodExtractResearch {

    public short getShortField() {
        return 0;
    }

    public int getIntegerField() {
        return 0;
    }

    public byte getByteField() {
        return 0;
    }
    
    public void setShortField(short f){}
    
    public void setIntegerField(int i) {}

    public void setByteField(byte b) {}
    
    public void setBooleanField(boolean b){}
    public boolean getBooleanField(){return false;}
    
    public void testNonSetGetMethod(){}
    
    public Boolean testNonInvokerMethod(){
        return false;
    }
    
    private void setPrivateByteMethod(byte b){}
}
