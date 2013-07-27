package com.googlecode.kevinarpe.papaya.swing;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PJComponentTextParserTest {
    
    @DataProvider
    private static final Object[][] _ctor_Pass_Data() {
        return new Object[][] {
                {
                    "La&bel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Label",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "L&ÅBEL",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "LÅBEL",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'Å',  // mnemonicKeyChar
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "L&åbel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Låbel",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'å',  // mnemonicKeyChar
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "Sticker && La&bel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Sticker & Label",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    12,  // mnemonicIndex
                },
                {
                    "La&bel && Sticker",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Label & Sticker",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "Label && Sticker",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Label & Sticker",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Label",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Label",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "   ",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "   ",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "&&La&bel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "&Label",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "L&&a&bel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "L&abel",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&&&bel",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "La&bel",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&b&&el",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Lab&el",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&be&&l",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Labe&l",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&bel&&",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "Label&",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "&&La&bel&&",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "&Label&",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "&&La&&&b&&el&&",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "&La&b&el&",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    4,  // mnemonicIndex
                },
                {
                    "<html><b>S</b>ample",  // labelBeforeParse
                    true,  // isHTMLStringBeforeParse
                    "<html><b>S</b>ample",  // labelAfterParse
                    true,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "<html>Sample<br>And<br>Example",  // labelAfterParse
                    true,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample",  // labelAfterParse
                    true,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'E',  // mnemonicKeyChar
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    null,  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    null,  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "   ",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "   ",  // labelAfterParse
                    false,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample<tag>",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "<html>Sample<br>And<br>Example&lt;tag>",  // labelAfterParse
                    true,  // isHTMLStringAfterParse
                    false,  // hasMnemonic
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PJComponentTextParser.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example<tag>",  // labelBeforeParse
                    false,  // isHTMLStringBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample&lt;tag>",  // labelAfterParse
                    true,  // isHTMLStringAfterParse
                    true,  // hasMnemonic
                    'E',  // mnemonicKeyChar
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJComponentTextParser.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
        };
    }

    @Test(dataProvider = "_ctor_Pass_Data")
    public void ctor_Pass(
            String labelBeforeParse,
            boolean isHTMLStringBeforeParse,
            String labelAfterParse,
            boolean isHTMLStringAfterParse,
            boolean hasMnemonic,
            char mnemonicKeyChar,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJComponentTextParser x = new PJComponentTextParser(labelBeforeParse);
        Assert.assertEquals(x.textBeforeParse, labelBeforeParse);
        Assert.assertEquals(x.isHTMLStringBeforeParse, isHTMLStringBeforeParse);
        Assert.assertEquals(x.textAfterParse, labelAfterParse);
        Assert.assertEquals(x.isHTMLStringAfterParse, isHTMLStringAfterParse);
        Assert.assertEquals(x.hasMnemonic, hasMnemonic);
        Assert.assertEquals(x.mnemonicKeyChar, mnemonicKeyChar);
        Assert.assertEquals(x.mnemonicKeyCode, mnemonicKeyCode);
        Assert.assertEquals(x.mnemonicIndex, mnemonicIndex);
    }
    
    @DataProvider
    private static final Object[][] _ctor_FailWithException_Data() {
        return new Object[][] {
                { "&" },  // trailing marker
                { "Label&" },  // trailing marker
                { "&La&bel" },  // double marker
                { "&La&be&l" },  // treble marker
                { "&南京" },  // unknown (Chinese) mnemonic char
                { "南&京" },  // unknown (Chinese) mnemonic char
                { "&九州" },  // unknown (Japanese) mnemonic char
                { "九&州" },  // unknown (Japanese) mnemonic char
                { "&　" },  // unknown (Japanese wide space) mnemonic char
        };
    }
    
    @Test(dataProvider = "_ctor_FailWithException_Data",
            expectedExceptions = IllegalArgumentException.class)
    public void ctor_FailWithException(String label) {
        try {
            new PJComponentTextParser(label);
        }
        catch (RuntimeException e) {
            // Use this to spot check the exception messages.
//            e.printStackTrace();
            throw e;
        }
    }
}