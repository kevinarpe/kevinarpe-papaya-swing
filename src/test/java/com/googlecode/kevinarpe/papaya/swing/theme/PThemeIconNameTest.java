package com.googlecode.kevinarpe.papaya.swing.theme;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.test.TestAssertUtils;

public class PThemeIconNameTest {

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.context
    //
    
    @Test
    public void context_Pass() {
        for (PThemeIconName e: PThemeIconName.values()) {
            Assert.assertNotNull(e.context);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.context
    //
    
    @Test
    public void baseFileName_Pass() {
        for (PThemeIconName e: PThemeIconName.values()) {
            Assert.assertNotNull(e.baseFileName);
            Assert.assertTrue(!e.baseFileName.isEmpty(), "!isEmpty");
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PThemeIconName.toString()
    //
    
    @Test
    public void toString_Pass() {
        TestAssertUtils.assertToStringMethodValid(PThemeIconName.values());
    }
}
