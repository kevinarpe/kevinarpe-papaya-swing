package com.googlecode.kevinarpe.papaya.swing;

import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PMnemonicHelperTest {
    
    @DataProvider
    private static final Object[][] _ctor_Pass_Data() {
        return new Object[][] {
                {
                    "La&bel",  // label
                    "Label",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "L&ÅBEL",  // label
                    "LÅBEL",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'Å',  // mnemonicKeyChar
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "L&åbel",  // label
                    "Låbel",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'å',  // mnemonicKeyChar
                    KeyEvent.VK_A,  // mnemonicKeyCode
                    1,  // mnemonicIndex
                },
                {
                    "Sticker && La&bel",  // label
                    "Sticker & Label",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    12,  // mnemonicIndex
                },
                {
                    "La&bel && Sticker",  // label
                    "Label & Sticker",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "Label && Sticker",  // label
                    "Label & Sticker",  // labelWithoutMarkup
                    false,  // hasMnemonic
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PMnemonicHelper.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "Label",  // label
                    "Label",  // labelWithoutMarkup
                    false,  // hasMnemonic
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PMnemonicHelper.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "",  // label
                    "",  // labelWithoutMarkup
                    false,  // hasMnemonic
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PMnemonicHelper.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "   ",  // label
                    "   ",  // labelWithoutMarkup
                    false,  // hasMnemonic
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CHAR,  // mnemonicKeyChar
                    PMnemonicHelper.DEFAULT_MNEMONIC_KEY_CODE,  // mnemonicKeyCode
                    PMnemonicHelper.DEFAULT_MNEMONIC_INDEX,  // mnemonicIndex
                },
                {
                    "&&La&bel",  // label
                    "&Label",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "L&&a&bel",  // label
                    "L&abel",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&&&bel",  // label
                    "La&bel",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "La&b&&el",  // label
                    "Lab&el",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&be&&l",  // label
                    "Labe&l",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "La&bel&&",  // label
                    "Label&",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    2,  // mnemonicIndex
                },
                {
                    "&&La&bel&&",  // label
                    "&Label&",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    3,  // mnemonicIndex
                },
                {
                    "&&La&&&b&&el&&",  // label
                    "&La&b&el&",  // labelWithoutMarkup
                    true,  // hasMnemonic
                    'b',  // mnemonicKeyChar
                    KeyEvent.VK_B,  // mnemonicKeyCode
                    4,  // mnemonicIndex
                },
        };
    }

    @Test(dataProvider = "_ctor_Pass_Data")
    public void ctor_Pass(
            String label,
            String labelWithoutMarkup,
            boolean hasMnemonic,
            char mnemonicKeyChar,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PMnemonicHelper x = new PMnemonicHelper(label);
        Assert.assertEquals(x.label, label);
        Assert.assertEquals(x.labelWithoutMarkers, labelWithoutMarkup);
        Assert.assertEquals(x.hasMnemonic, hasMnemonic);
        Assert.assertEquals(x.mnemonicKeyChar, mnemonicKeyChar);
        Assert.assertEquals(x.mnemonicKeyCode, mnemonicKeyCode);
        Assert.assertEquals(x.mnemonicIndex, mnemonicIndex);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ctor_FailWithNull() {
        new PMnemonicHelper(null);
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
            new PMnemonicHelper(label);
        }
        catch (RuntimeException e) {
            // Use this to spot check the exception messages.
//            e.printStackTrace();
            throw e;
        }
    }
}
