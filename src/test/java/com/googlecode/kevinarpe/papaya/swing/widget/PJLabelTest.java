package com.googlecode.kevinarpe.papaya.swing.widget;

import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;

import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PJLabelTest {

    @DataProvider
    private static final Object[][] _setText_Pass_Data() {
        return new Object[][] {
                {
                    "La&bel",  // textBeforeParse
                    "Label",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "L&ÅBEL",  // textBeforeParse
                    "LÅBEL",  // textAfterParse
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "L&åbel",  // textBeforeParse
                    "Låbel",  // textAfterParse
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "Sticker && La&bel",  // textBeforeParse
                    "Sticker & Label",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    12,  // mnemonicIndex
                },
                {
                    "La&bel && Sticker",  // textBeforeParse
                    "Label & Sticker",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "Label && Sticker",  // textBeforeParse
                    "Label & Sticker",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Label",  // textBeforeParse
                    "Label",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "",  // textBeforeParse
                    "",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "   ",  // textBeforeParse
                    "   ",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "&&La&bel",  // textBeforeParse
                    "&Label",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "L&&a&bel",  // textBeforeParse
                    "L&abel",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&&&bel",  // textBeforeParse
                    "La&bel",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&b&&el",  // textBeforeParse
                    "Lab&el",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&be&&l",  // textBeforeParse
                    "Labe&l",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&bel&&",  // textBeforeParse
                    "Label&",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "&&La&bel&&",  // textBeforeParse
                    "&Label&",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "&&La&&&b&&el&&",  // textBeforeParse
                    "&La&b&el&",  // textAfterParse
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    4,  // mnemonicIndex
                },
                {
                    "<html><b>S</b>ample",  // textBeforeParse
                    "<html><b>S</b>ample",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample",  // textBeforeParse
                    "<html>Sample<br>And<br>Example",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example",  // textBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample",  // textAfterParse
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    null,  // textBeforeParse
                    null,  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "",  // textBeforeParse
                    "",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "   ",  // textBeforeParse
                    "   ",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample<tag>",  // textBeforeParse
                    "<html>Sample<br>And<br>Example&lt;tag>",  // textAfterParse
                    PJLabel.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example<tag>",  // textBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample&lt;tag>",  // textAfterParse
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJLabel.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
        };
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(<empty>)
    //

    @Test
    public void ctor_Pass() {
        PJLabel x = new PJLabel();
        String textBeforeParse = PJLabel.DEFAULT_TEXT;
        String textAfterParse = PJLabel.DEFAULT_TEXT;
        int mnemonicKeyCode = PJLabel.DEFAULT_MNEMONIC_KEY_CODE;
        int mnemonicIndex = PJLabel.DEFAULT_MNEMONIC_INDEX;
        _setText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctor_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel(textBeforeParse);
        _setText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctor_Pass2(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel(textBeforeParse, SwingConstants.LEADING);
        _setText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setText
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void setText_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel();
        x.setText(textBeforeParse);
        _setText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
    }
    
    private void _setText_Pass(
            PJLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        Assert.assertEquals(
            "textBeforeParse",
            textBeforeParse,
            //(null == textBeforeParse ? "" : textBeforeParse),
            x.getOriginalText());
        Assert.assertEquals(
            "textAfterParse",
            textAfterParse,
            x.getText());
        Assert.assertEquals("mnemonicKeyCode", mnemonicKeyCode, x.getDisplayedMnemonic());
        Assert.assertEquals("mnemonicIndex", mnemonicIndex, x.getDisplayedMnemonicIndex());
    }
}
