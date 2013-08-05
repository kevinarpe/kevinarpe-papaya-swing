package com.googlecode.kevinarpe.papaya.swing.widget;

/*
 * #%L
 * This file is part of Papaya Swing.
 * %%
 * Copyright (C) 2013 Kevin Connor ARPE (kevinarpe@gmail.com)
 * %%
 * Papaya Swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GPL Classpath Exception:
 * This project is subject to the "Classpath" exception as provided in
 * the LICENSE file that accompanied this code.
 * 
 * Papaya Swing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Papaya Swing.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.awt.event.KeyEvent;

import javax.swing.Icon;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;

/**
 * Helpers for testing AbstractButton subclasses.
 * 
 * @author Kevin Connor ARPE (kevinarpe@gmail.com)
 */
public class PTextLabelTest {

    @DataProvider
    public static final Object[][] setText_Pass_Data() {
        return new Object[][] {
                {
                    null,  // textBeforeParse
                    null,  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
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
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "Label",  // textBeforeParse
                    "Label",  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
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
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample",  // textBeforeParse
                    "<html>Sample<br>And<br>Example",  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example",  // textBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample",  // textAfterParse
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    null,  // textBeforeParse
                    null,  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "",  // textBeforeParse
                    "",  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "   ",  // textBeforeParse
                    "   ",  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\nExample<tag>",  // textBeforeParse
                    "<html>Sample<br>And<br>Example&lt;tag>",  // textAfterParse
                    PJLabel.DEFAULTS.displayedMnemonicKeyCode,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
                {
                    "Sample\nAnd\n&Example<tag>",  // textBeforeParse
                    "<html>Sample<br>And<br><u>E</u>xample&lt;tag>",  // textAfterParse
                    KeyEvent.VK_E,  // mnemonicKeyCode
                    PJLabel.DEFAULTS.displayedMnemonicIndex,  // mnemonicIndex
                },
        };
    }

    public static void coreSetText_Pass(
            PTextLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        Assert.assertEquals(x.getOriginalText(), textBeforeParse, "textBeforeParse");
        Assert.assertEquals(x.getText(), textAfterParse, "textAfterParse");
        Assert.assertEquals(x.getMnemonic(), mnemonicKeyCode, "mnemonicKeyCode");
        Assert.assertEquals(x.getDisplayedMnemonicIndex(), mnemonicIndex, "mnemonicIndex");
    }

    public static void checkBasicAttrs(
            PTextLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex,
            Icon image,
            PHorizontalAlignment halign,
            PVerticalAlignment valign,
            PHorizontalAlignment htextPos,
            PVerticalAlignment vtextPos) {
        coreSetText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
        
        Assert.assertEquals(x.getHorizontalAlignmentAsEnum(), halign, "halign");
        Assert.assertEquals(x.getHorizontalAlignment(), halign.value, "halign as int");
        
        Assert.assertEquals(x.getVerticalAlignmentAsEnum(), valign, "valign");
        Assert.assertEquals(x.getVerticalAlignment(), valign.value, "valign as int");
        
        Assert.assertEquals(x.getHorizontalTextPositionAsEnum(), htextPos, "htextPos");
        Assert.assertEquals(x.getHorizontalTextPosition(), htextPos.value, "htextPos as int");
        Assert.assertEquals(x.getVerticalTextPositionAsEnum(), vtextPos, "vtextPos");
        Assert.assertEquals(x.getVerticalTextPosition(), vtextPos.value, "vtextPos as int");
    }
}
