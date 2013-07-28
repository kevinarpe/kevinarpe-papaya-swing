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
import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.googlecode.kevinarpe.papaya.swing.PHorizontalAlignment;
import com.googlecode.kevinarpe.papaya.swing.PVerticalAlignment;
import com.googlecode.kevinarpe.papaya.swing.test.DummyIconImpl;

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
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(<empty>)
    //

    @Test
    public void ctorEmpty_Pass() {
        PJLabel x = new PJLabel();
        _checkBasicAttrs2(
            x,
            PJLabel.DEFAULTS.text,
            PJLabel.DEFAULTS.text,
            PJLabel.DEFAULTS.displayedMnemonicKeyCode,
            PJLabel.DEFAULTS.displayedMnemonicIndex,
            PJLabel.DEFAULTS.icon,
            PJLabel.DEFAULTS.horizontalAlignment,
            PJLabel.DEFAULTS.verticalAlignment,
            PJLabel.DEFAULTS.horizontalTextPosition,
            PJLabel.DEFAULTS.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon)
    //

    @Test
    public void ctorIcon_Pass() {
        PJLabel x = new PJLabel(DummyIconImpl.INSTANCE);
        _checkBasicAttrs2(
            x,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
            DummyIconImpl.INSTANCE,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalAlignment,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
            PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon, int)
    //

    @Test
    public void ctorIconInt_Pass() {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(DummyIconImpl.INSTANCE, halign.value);
            _checkBasicAttrs2(
                x,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                DummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(Icon, PHorizontalAlignment)
    //

    @Test
    public void ctorIconPHorizontalAlignment_Pass() {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(DummyIconImpl.INSTANCE, halign);
            _checkBasicAttrs2(
                x,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.text,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicKeyCode,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.displayedMnemonicIndex,
                DummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalAlignment,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.horizontalTextPosition,
                PJLabel.DEFAULTS_FOR_ICON_ONLY.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorString_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        PJLabel x = new PJLabel(textBeforeParse);
        _checkBasicAttrs2(
            x,
            textBeforeParse,
            textAfterParse,
            mnemonicKeyCode,
            mnemonicIndex,
            PJLabel.DEFAULTS.icon,
            PJLabel.DEFAULTS.horizontalAlignment,
            PJLabel.DEFAULTS.verticalAlignment,
            PJLabel.DEFAULTS.horizontalTextPosition,
            PJLabel.DEFAULTS.verticalTextPosition);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, int)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringInt_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, halign.value);
            _checkBasicAttrs2(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                PJLabel.DEFAULTS.icon,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, PHorizontalAlignment)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringPHorizontalAlignment_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, halign);
            _checkBasicAttrs2(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                PJLabel.DEFAULTS.icon,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, Icon, int)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconInt_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, DummyIconImpl.INSTANCE, halign.value);
            _checkBasicAttrs2(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                DummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.ctor(String, Icon, PHorizontalAlignment)
    //
    
    @Test(dataProvider = "_setText_Pass_Data")
    public void ctorStringIconPHorizontalAlignment_Pass(
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            PJLabel x = new PJLabel(textBeforeParse, DummyIconImpl.INSTANCE, halign);
            _checkBasicAttrs2(
                x,
                textBeforeParse,
                textAfterParse,
                mnemonicKeyCode,
                mnemonicIndex,
                DummyIconImpl.INSTANCE,
                halign,
                PJLabel.DEFAULTS.verticalAlignment,
                PJLabel.DEFAULTS.horizontalTextPosition,
                PJLabel.DEFAULTS.verticalTextPosition);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setText(String)
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
        Assert.assertEquals("textBeforeParse", textBeforeParse, x.getOriginalText());
        Assert.assertEquals("textAfterParse", textAfterParse, x.getText());
        Assert.assertEquals("mnemonicKeyCode", mnemonicKeyCode, x.getDisplayedMnemonic());
        Assert.assertEquals("mnemonicIndex", mnemonicIndex, x.getDisplayedMnemonicIndex());
    }
    
    private void _checkBasicAttrs(
            PJLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex,
            Icon image,
            PHorizontalAlignment halign) {
        _setText_Pass(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex);
        
        Assert.assertEquals("halign", halign, x.getHorizontalAlignmentAsEnum());
        Assert.assertEquals("halign as int", halign.value, x.getHorizontalAlignment());
    }
    
    private void _checkBasicAttrs2(
            PJLabel x,
            String textBeforeParse,
            String textAfterParse,
            int mnemonicKeyCode,
            int mnemonicIndex,
            Icon image,
            PHorizontalAlignment halign,
            PVerticalAlignment valign,
            PHorizontalAlignment htextPos,
            PVerticalAlignment vtextPos) {
        _checkBasicAttrs(x, textBeforeParse, textAfterParse, mnemonicKeyCode, mnemonicIndex, image, halign);
        
        Assert.assertEquals("valign", valign, x.getVerticalAlignmentAsEnum());
        Assert.assertEquals("valign as int", valign.value, x.getVerticalAlignment());
        
        Assert.assertEquals("htextPos", htextPos, x.getHorizontalTextPositionAsEnum());
        Assert.assertEquals("htextPos as int", htextPos.value, x.getHorizontalTextPosition());
        Assert.assertEquals("vtextPos", vtextPos, x.getVerticalTextPositionAsEnum());
        Assert.assertEquals("vtextPos as int", vtextPos.value, x.getVerticalTextPosition());
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setHorizontalAlignment(PHorizontalAlignment)/.getHorizontalAlignmentAsEnum()
    //
    
    @Test
    public void setHorizontalAlignment_Pass() {
        PJLabel x = new PJLabel();
        _setHorizontalAlignment_Pass(x);
        
        PJLabel y = new PJLabel(DummyIconImpl.INSTANCE);
        _setHorizontalAlignment_Pass(y);
    }
    
    public void _setHorizontalAlignment_Pass(PJLabel x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalAlignment(halign);
            Assert.assertEquals(halign.value, x.getHorizontalAlignment());
            Assert.assertEquals(halign, x.getHorizontalAlignmentAsEnum());
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalAlignment_FailWithNull2() {
        PJLabel x = new PJLabel(DummyIconImpl.INSTANCE);
        x.setHorizontalAlignment((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setVerticalAlignment(PVerticalAlignment)/.getVerticalAlignmentAsEnum()
    //
    
    @Test
    public void setVerticalAlignment_Pass() {
        PJLabel x = new PJLabel();
        _setVerticalAlignment_Pass(x);
        
        PJLabel y = new PJLabel(DummyIconImpl.INSTANCE);
        _setVerticalAlignment_Pass(y);
    }
    
    public void _setVerticalAlignment_Pass(PJLabel x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalAlignment(halign);
            Assert.assertEquals(halign.value, x.getVerticalAlignment());
            Assert.assertEquals(halign, x.getVerticalAlignmentAsEnum());
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalAlignment_FailWithNull2() {
        PJLabel x = new PJLabel(DummyIconImpl.INSTANCE);
        x.setVerticalAlignment((PVerticalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setHorizontalTextPosition(PHorizontalAlignment)/.getHorizontalTextPositionAsEnum()
    //
    
    @Test
    public void setHorizontalTextPosition_Pass() {
        PJLabel x = new PJLabel();
        _setHorizontalTextPosition_Pass(x);
        
        PJLabel y = new PJLabel(DummyIconImpl.INSTANCE);
        _setHorizontalTextPosition_Pass(y);
    }
    
    public void _setHorizontalTextPosition_Pass(PJLabel x) {
        for (PHorizontalAlignment halign: PHorizontalAlignment.values()) {
            x.setHorizontalTextPosition(halign);
            Assert.assertEquals(halign.value, x.getHorizontalTextPosition());
            Assert.assertEquals(halign, x.getHorizontalTextPositionAsEnum());
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setHorizontalTextPosition_FailWithNull2() {
        PJLabel x = new PJLabel(DummyIconImpl.INSTANCE);
        x.setHorizontalTextPosition((PHorizontalAlignment) null);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // PJLabel.setVerticalTextPosition(PVerticalAlignment)/.getVerticalTextPositionAsEnum()
    //
    
    @Test
    public void setVerticalTextPosition_Pass() {
        PJLabel x = new PJLabel();
        _setVerticalTextPosition_Pass(x);
        
        PJLabel y = new PJLabel(DummyIconImpl.INSTANCE);
        _setVerticalTextPosition_Pass(y);
    }
    
    public void _setVerticalTextPosition_Pass(PJLabel x) {
        for (PVerticalAlignment halign: PVerticalAlignment.values()) {
            x.setVerticalTextPosition(halign);
            Assert.assertEquals(halign.value, x.getVerticalTextPosition());
            Assert.assertEquals(halign, x.getVerticalTextPositionAsEnum());
        }
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull() {
        PJLabel x = new PJLabel();
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setVerticalTextPosition_FailWithNull2() {
        PJLabel x = new PJLabel(DummyIconImpl.INSTANCE);
        x.setVerticalTextPosition((PVerticalAlignment) null);
    }
}
