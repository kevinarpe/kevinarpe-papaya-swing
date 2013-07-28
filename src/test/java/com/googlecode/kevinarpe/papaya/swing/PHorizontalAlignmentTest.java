package com.googlecode.kevinarpe.papaya.swing;

import org.junit.Assert;
import org.testng.annotations.Test;

public class PHorizontalAlignmentTest {

    ///////////////////////////////////////////////////////////////////////////
    // PHorizontalAlignment.valueOf(int)
    //
    
    @Test
    public void valueOf_Pass() {
        for (PHorizontalAlignment e: PHorizontalAlignment.values()) {
            PHorizontalAlignment e2 = PHorizontalAlignment.valueOf(e.value);
            Assert.assertTrue(e == e2);
        }
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void valueOf_Fail() {
        for (PHorizontalAlignment e: PHorizontalAlignment.values()) {
            if (0 != e.value) {
                PHorizontalAlignment.valueOf(31 * e.value);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PHorizontalAlignment.toString()
    //
    
    @Test
    public void toString_Pass() {
        for (PHorizontalAlignment e: PHorizontalAlignment.values()) {
            String x = e.toString();
            Assert.assertNotNull(x);
        }
    }
}
