package com.googlecode.kevinarpe.papaya.swing;

import org.junit.Assert;
import org.testng.annotations.Test;

public class PVerticalAlignmentTest {

    ///////////////////////////////////////////////////////////////////////////
    // PVerticalAlignment.valueOf(int)
    //
    
    @Test
    public void valueOf_Pass() {
        for (PVerticalAlignment e: PVerticalAlignment.values()) {
            PVerticalAlignment e2 = PVerticalAlignment.valueOf(e.value);
            Assert.assertTrue(e == e2);
        }
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void valueOf_Fail() {
        for (PVerticalAlignment e: PVerticalAlignment.values()) {
            if (0 != e.value) {
                PVerticalAlignment.valueOf(31 * e.value);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PVerticalAlignment.toString()
    //
    
    @Test
    public void toString_Pass() {
        for (PVerticalAlignment e: PVerticalAlignment.values()) {
            String x = e.toString();
            Assert.assertNotNull(x);
        }
    }
}
