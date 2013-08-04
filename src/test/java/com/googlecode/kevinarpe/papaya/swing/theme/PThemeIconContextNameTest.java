package com.googlecode.kevinarpe.papaya.swing.theme;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.test.TestAssertUtils;

public class PThemeIconContextNameTest {

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconContextName.dirName
    //
    
    @Test
    public void dirName_Pass() {
        for (PThemeIconContextName e: PThemeIconContextName.values()) {
            Assert.assertNotNull(e.dirName);
            Assert.assertTrue(!e.dirName.isEmpty(), "!isEmpty");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconContextName.toString()
    //
    
    @Test
    public void toString_Pass() {
        TestAssertUtils.assertToStringMethodValid(PThemeIconContextName.values());
    }
}
